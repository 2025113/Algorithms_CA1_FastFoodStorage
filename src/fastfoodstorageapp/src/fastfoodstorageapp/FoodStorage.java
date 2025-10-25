/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fastfoodstorageapp; 

import java.util.LinkedList;
import java.util.Queue;

// This class implements the StorageOperation interface satisfying the OOP requirement
// This file contains the core algorithmic logic and houses the Queue data structure
public class FoodStorage implements StorageOperation {
    
    private final int MAX_CAPACITY = 8;
    // QUEUE implementation (FIFO)
    private Queue<FoodItem> storage; 

    public FoodStorage() {
        // LinkedList make a Queue, the best structure for FIFO
        this.storage = new LinkedList<>(); 
        System.out.println("Storage initialized: FIFO Queue (Front for ADD, Opposite for REMOVE).");
        System.out.println("Maximum capacity: " + MAX_CAPACITY + " trays.");
    }
    
    // Checks if the queue is full
    @Override
    public boolean isFull() {
        return storage.size() >= MAX_CAPACITY;
    }
    
    // Check if the queue is empty
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    // Add a item (Enqueue), complexity O(1)
    // Our time complexity O(1) guaranteed by the Queue implementation
    @Override
    public boolean addItem(FoodItem item) {
        if (isFull()) {
            // Here, we have a capacity check, this is crucial for fixed-size storage, in our case only 8 items 
            System.err.println("ERROR: Storage is full. Cannot add " + item.getName());
            return false;
        }
        // This part, the offer() will adds the item to the tail of the Queue, this is the confirmation of our FIFO method
        storage.offer(item); 
        System.out.println("SUCCESS: Added " + item.getName());
        return true;
    }

    // Remove item (Dequeue) 
    // This block will check the Time Complexity, O(1) Constant Time guaranteed by the Queue implementation
    @Override
    public FoodItem removeItem() {
        if (isEmpty()) {
            // Prevent removing from an empty queue, so, if the user try to remove any item from empty storage, system will display this message
            System.err.println("ERROR: Storage is empty. No food item to remove.");
            return null;
        }
        // Poll() retrieves and removes the head of this queue, this is the FIFO concept 
        return storage.poll(); 
    }

    // Check the first item (Peek) 
    @Override
    public FoodItem peekTop() {
        // We need to check if the storage is empty before peek 
        if (isEmpty()) {
            // We changed the error message, using system.err for better consistency in error reporting
            System.err.println("ERROR: The food storage is empty. Cannot peek.");
            return null;
        }
        
        FoodItem topItem = storage.peek();
        // Displaying the item that is about to be removed 
        System.out.println("\n--- Item on Front (Next to be removed - Oldest Item) ---");
        System.out.println(topItem);
        System.out.println("---------------------------------------------------------");
        
        return topItem;
    }
    
    // Show all items 
    // Here it is important to show the Time Complexity Analysis, in this case O(n), thats means must iterate through every element
    @Override
    public void displayAll() {
        if (isEmpty()) {
            System.err.println("ERROR: Storage is empty. Nothing to display.");
            return;
        }
        
        System.out.println("\n--- Current Food Storage Content (FIFO Order: Oldest -> Newest) ---");
        storage.forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------");
    }
}
