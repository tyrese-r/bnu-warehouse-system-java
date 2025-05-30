package dev.tbertie.warehousesystem.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Customer {
    private UUID uuid;
    private String name;
    private List<CustomerOrder> orderHistory;

    private String address;

    public Customer(String name, String address) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.address = address;
    }
}
