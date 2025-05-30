package dev.tbertie.warehousesystem.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Supplier {
    private UUID uuid;
    private String name;
    private String contactInfo;
    private List<String> purchases;
    private List<String> items;
    private List<String> deliveries;

    public Supplier(UUID uuid, String name, String contactInfo, List<String> purchases, List<String> items, List<String> deliveries) {
        this.uuid = uuid;
        this.name = name;
        this.contactInfo = contactInfo;
        this.purchases = purchases;
        this.items = items;
        this.deliveries = deliveries;
    }
}