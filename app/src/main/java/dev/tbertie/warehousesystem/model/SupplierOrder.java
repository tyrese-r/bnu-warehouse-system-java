package dev.tbertie.warehousesystem.model;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class SupplierOrder {
    private Supplier supplier;
    private Map<Item, Integer> itemList;
    private String orderNumber;
    private String status;
    
    public SupplierOrder(Supplier supplier, String orderNumber) {
        this.supplier = supplier;
        this.orderNumber = orderNumber;
        this.itemList = new HashMap<>();
        this.status = "Pending";
    }
    
    public void addItem(Item item, Integer quantity) {
        itemList.put(item, quantity);
    }
    
    public int getTotalOrderValue() {
        return itemList.entrySet().stream()
            .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
            .sum();
    }
}
