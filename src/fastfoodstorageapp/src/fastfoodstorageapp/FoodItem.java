/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fastfoodstorageapp; 

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FoodItem {
    private String name;
    private int weightGrams;
    private LocalDate bestBeforeDate;
    private LocalDateTime timePlaced;

    public FoodItem(String name, int weightGrams, LocalDate bestBeforeDate) {
        this.name = name;
        this.weightGrams = weightGrams;
        this.bestBeforeDate = bestBeforeDate;
        this.timePlaced = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }
    
    // Getters
    public LocalDate getBestBeforeDate() {
        return bestBeforeDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return String.format(
            "| %-10s | %-12dg | Expires: %s | Placed: %s |", 
            name, 
            weightGrams, 
            bestBeforeDate, 
            timePlaced.format(timeFormatter)
        );
    }
}
