package dev.tbertie.warehousesystem.model;

import lombok.Data;
import java.util.UUID;

@Data
public class Item {
    private String name;
    private UUID supplierId;
    private String description;
    private Integer price;
    private Integer inStock;
    
    public Item(String name, UUID supplierId, String description, Integer price, Integer inStock) {
        this.name = name;
        this.supplierId = supplierId;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
    }
}
