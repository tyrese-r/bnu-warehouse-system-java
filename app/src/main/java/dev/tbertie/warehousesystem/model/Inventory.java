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
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }
    
    public boolean removeItem(Item item, int quantity) {
        int currentQuantity = items.getOrDefault(item, 0);
        if (currentQuantity >= quantity) {
            items.put(item, currentQuantity - quantity);
            return true;
        }
        return false;
    }
    
    public int getQuantity(Item item) {
        return items.getOrDefault(item, 0);
    }
    
    public boolean isLowStock(Item item) {
        return getQuantity(item) <= lowStockThreshold;
    }
    
    public List<Item> getLowStockItems() {
        return items.keySet().stream()
                .filter(this::isLowStock)
                .toList();
    }
    
    public boolean canFulfillOrder(CustomerOrder order) {
        for (Map.Entry<Item, Integer> entry : order.getItems().entrySet()) {
            Item item = entry.getKey();
            int requiredQuantity = entry.getValue();
            if (getQuantity(item) < requiredQuantity) {
                return false;
            }
        }
        return true;
    }
    
    public boolean fulfillOrder(CustomerOrder order) {
        if (!canFulfillOrder(order)) {
            return false;
        }
        
        for (Map.Entry<Item, Integer> entry : order.getItems().entrySet()) {
            Item item = entry.getKey();
            int requiredQuantity = entry.getValue();
            removeItem(item, requiredQuantity);
        }
        return true;
    }
}