/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fastfoodstorageapp; 

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;

public class FastFoodStorageApp {
    
    private static final String[] VALID_FOODS = {"BURGER", "PIZZA", "FRIES", "SANDWICH", "HOTDOG"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Fast-Food Storage Application (FIFO Queue Mode) ---");
        System.out.println("Mode Justification: FIFO is crucial for managing food items with best-before dates.");
        
        // Simple construct, only create the queue
        FoodStorage storage = new FoodStorage();

        while (true) {
            System.out.println("\n===== Storage Menu (Capacity: 8) =====");
            System.out.println("1. Add a Food Item");
            System.out.println("2. Remove Food Item");
            System.out.println("3. Display Front Item (Peek)");
            System.out.println("4. Display All Items");
            System.out.println("5. Terminate Application (Exit)");
            System.out.print("Enter your choice: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        addFoodItem(scanner, storage); // With validation
                        break;
                    case 2:
                        FoodItem removed = storage.removeItem();
                        if (removed != null) {
                            System.out.println("SUCCESS: Item removed: " + removed.getName());
                        }
                        break;
                    case 3:
                        storage.peekTop(); 
                        break;
                    case 4:
                        storage.displayAll();
                        break;
                    case 5:
                        System.out.println("Application terminated. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
        // Here we need to garantee the valid entry, int numbers
        System.err.println("\nERROR: Invalid input format detected. Please enter a valid menu number (1-5).");
        scanner.nextLine(); // Here we are clearing the buffer to prevent infinite loop
            }
        }
    }
    
    // Support method with validations
    private static void addFoodItem(Scanner scanner, FoodStorage storage) {
        String name = "";
        int weight = 0;
        LocalDate bestBeforeDate = null;
        boolean isValid = false;

        // 1. Validate food name
        do {
            System.out.print("Enter food name (" + Arrays.toString(VALID_FOODS) + "): ");
            name = scanner.nextLine().toUpperCase();
            if (Arrays.asList(VALID_FOODS).contains(name)) {
                isValid = true;
            } else {
                System.err.println("Validation Error: Invalid food name. Must be one of the allowed types.");
            }
        } while (!isValid);

        // 2. Validate weight, must be a positive number ( >0 )
        isValid = false;
        do {
            // In this part, we have to check the weight and that must be greater than zero, it will be better for inventory tracking
            System.out.print("Enter weight (grams, must be positive): ");
            try {
                weight = scanner.nextInt();
                scanner.nextLine();
                if (weight > 0) {
                    isValid = true;
                } else {
                    System.err.println("Validation Error: Weight must be a positive number.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Validation Error: Invalid input for weight. Please enter a number.");
                scanner.nextLine();
                weight = 0; 
            }
        } while (!isValid);

        // 3. Validate best-before date, must be until 2 weeks 
        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.plusWeeks(2);
        
        // Food items are limited to 14 days for keep in storage, this is the last rule of our business
        System.out.print("Enter best-before date (YYYY-MM-DD, Max: " + maxDate + "): ");
        String dateString = scanner.nextLine();
        
        if (dateString.isEmpty()) {
            bestBeforeDate = maxDate;
            System.out.println("Date not entered, using maximum best-before date: " + maxDate);
        } else {
            try {
                LocalDate inputDate = LocalDate.parse(dateString);
                long daysDifference = ChronoUnit.DAYS.between(today, inputDate);

                if (daysDifference > 14) {
                    System.err.println("Validation Warning: Date " + inputDate + " is more than 14 days away. Adjusting to max date: " + maxDate);
                    bestBeforeDate = maxDate; 
                } else if (inputDate.isBefore(today)) {
                    System.err.println("Validation Warning: Date " + inputDate + " is in the past. Adjusting to max date: " + maxDate);
                    bestBeforeDate = maxDate;
                } else {
                    bestBeforeDate = inputDate;
                }
            } catch (DateTimeParseException e) {
                System.err.println("Validation Error: Invalid date format. Using max date: " + maxDate);
                bestBeforeDate = maxDate;
            }
        }

        FoodItem newItem = new FoodItem(name, weight, bestBeforeDate);
        storage.addItem(newItem);
    }
}
