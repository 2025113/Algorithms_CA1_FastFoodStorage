/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fastfoodstorageapp; 

public interface StorageOperation {
    // This is our Enqueue, the way to add a FoodItem 
    boolean addItem(FoodItem item); 
    // This is our Dequeue, the way to remove the oldest FoodItem 
    FoodItem removeItem(); 
    // Method to view the oldest FoodItem without removing it, thats means a Peek
    FoodItem peekTop();
    // This line will display all items currently in storage
    void displayAll(); 
    // This is to check if the storage has reached maximum capacity, in our case 8 trays
    boolean isFull(); 
}
