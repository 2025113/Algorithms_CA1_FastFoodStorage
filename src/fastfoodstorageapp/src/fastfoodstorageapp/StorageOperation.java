/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fastfoodstorageapp; 

public interface StorageOperation {
    boolean addItem(FoodItem item); 
    FoodItem removeItem(); 
    FoodItem peekTop(); 
    void displayAll(); 
    boolean isFull(); 
}
