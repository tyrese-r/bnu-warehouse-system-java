package dev.tbertie.warehousesystem.model;

import lombok.Data;

import java.util.List;

@Data
public class Customer {
    private String name;
    private List<CustomerOrder> orderHistory;

    private String address;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
