package dev.tbertie.warehousesystem.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class CustomerOrder {
    private String orderId;
    private Customer customer;
    private Map<Item, Integer> items;
    private String status;
    private LocalDateTime orderDate;
    private LocalDateTime fulfilledDate;
    
    public CustomerOrder(Customer customer) {
        this.orderId = UUID.randomUUID().toString();
        this.customer = customer;
        this.items = new HashMap<>();
        this.status = "New";
        this.orderDate = LocalDateTime.now();
    }
    
    public void addItem(Item item, int quantity) {

    }
    
    public void removeItem(Item item) {

    }
    
    public int getTotalValue() {
        return 0;
    }
    
    public void updateStatus(String newStatus) {

    }
    
    public boolean canFulfill(Inventory inventory) {
        return false;
    }
}
