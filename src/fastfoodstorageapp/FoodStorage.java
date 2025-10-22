/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fastfoodstorageapp; // <<== VERIFIQUE SE O NOME DO SEU PACOTE ESTÁ AQUI

import java.util.LinkedList;
import java.util.Queue;

public class FoodStorage implements StorageOperation {
    
    private final int MAX_CAPACITY = 8;
    // Implementação pura da Fila (FIFO)
    private Queue<FoodItem> storage; 

    public FoodStorage() {
        // LinkedList implementa Queue, que é a estrutura ideal para FIFO
        this.storage = new LinkedList<>(); 
        System.out.println("Storage initialized: FIFO Queue (Front for ADD, Opposite for REMOVE).");
        System.out.println("Maximum capacity: " + MAX_CAPACITY + " trays.");
    }
    
    // Verifica se a Fila está cheia
    @Override
    public boolean isFull() {
        return storage.size() >= MAX_CAPACITY;
    }
    
    // Verifica se a Fila está vazia
    public boolean isEmpty() {
        return storage.isEmpty();
    }

    // Adicionar Item (Enqueue) - Complexidade O(1)
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

    // Remover Item (Dequeue) - Complexidade O(1)
    @Override
    public FoodItem removeItem() {
        if (isEmpty()) {
            System.err.println("ERROR: Storage is empty. No food item to remove.");
            return null;
        }
        return storage.poll(); 
    }

    // Visualizar o Item da Frente (Peek) - Complexidade O(1)
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
    
    // Exibir todos os itens - Complexidade O(n)
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
