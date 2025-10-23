/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fastfoodstorageapp; 

import java.util.LinkedList;
import java.util.Queue;

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

    // Add a item Enqueue), complexity O(1)
    @Override
    public boolean addItem(FoodItem item) {
        if (isFull()) {
            System.err.println("ERROR: Storage is full. Cannot add " + item.getName());
            return false;
        }
        storage.offer(item); 
        System.out.println("SUCCESS: Added " + item.getName());
        return true;
    }

    // Remove item (Dequeue) 
    @Override
    public FoodItem removeItem() {
        if (isEmpty()) {
            System.err.println("ERROR: Storage is empty. No food item to remove.");
            return null;
        }
        return storage.poll(); 
    }

    // Check the first item (Peek) 
    @Override
    public FoodItem peekTop() {
        if (isEmpty()) {
            System.out.println("INFO: Storage is currently empty.");
            return null;
        }
        
        FoodItem topItem = storage.peek();
        System.out.println("\n--- Item on Front (Next to be removed - Oldest Item) ---");
        System.out.println(topItem);
        System.out.println("---------------------------------------------------------");
        
        return topItem;
    }
    
    // Show all items 
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
