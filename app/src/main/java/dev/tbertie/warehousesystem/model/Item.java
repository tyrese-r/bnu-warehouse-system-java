package dev.tbertie.warehousesystem.model;

import lombok.Data;

@Data
public class Item {
    private String name;
    private Supplier supplier;
    private String description;
    private Integer price;
    private Integer inStock;
    
    public Item(String name, Supplier supplier, String description, Integer price, Integer inStock) {
        this.name = name;
        this.supplier = supplier;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
    }
}
