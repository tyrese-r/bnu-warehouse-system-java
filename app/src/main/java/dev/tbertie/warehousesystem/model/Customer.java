package dev.tbertie.warehousesystem.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Customer {
    private UUID uuid;
    private String name;
    private String address;
    private String phone;
    private List<CustomerOrder> orderHistory;

    public Customer(String name, String address, String phone) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
