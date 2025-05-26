package dev.tbertie.warehousesystem.model;

import lombok.Data;

@Data
public class Supplier {
    private String name;
    private String contactInfo;
    
    public Supplier(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }
}
