package dev.tbertie.warehousesystem.model;

import lombok.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Inventory {
    private Map<Item, Integer> items;
    private int lowStockThreshold;
    
    public Inventory(int lowStockThreshold) {
        this.items = new HashMap<>();
        this.lowStockThreshold = lowStockThreshold;
    }
    
    public void addItem(Item item, int quantity) {

    }
    
    public boolean removeItem(Item item, int quantity) {

        return false;
    }
    
    public int getQuantity(Item item) {
        return 0;
    }
    
    public boolean isLowStock(Item item) {
        return false;
    }
    
    public List<Item> getLowStockItems() {
        return null;
    }
    
    public boolean canFulfillOrder(CustomerOrder order) {
        return false;
    }
    
    public boolean fulfillOrder(CustomerOrder order) {
        return false;
    }
}