package dev.tbertie.warehousesystem.controller;

import dev.tbertie.warehousesystem.model.Customer;
import dev.tbertie.warehousesystem.model.Item;
import dev.tbertie.warehousesystem.repository.CustomerRepository;
import dev.tbertie.warehousesystem.service.InventoryService;

import java.util.*;

//NOTE: Sort of mediator pattern
public class CustomerController {

    CustomerRepository customers = CustomerRepository.getInstance();
    public void createCustomer(String name, String address) {
        Customer customer = new Customer(name, address);
        customers.getInstance().addCustomer(customer);
    }

    public void updateName(Customer customer, String newName) {
        // Validation would be done here
        customer.setName(newName);
    }

    public void updateAddress(Customer customer, String newAddress) {
        customer.setAddress(newAddress);
    }

    public List<Customer> getAllCustomers() {
        return getAllCustomers();
    }

    public Customer getCustomerByUuid(UUID uuid) {
        return customers.getCustomerByUuid(uuid);
    }

    public Customer searchCustomerByName(String name) {
        for (Customer customer : customers.getAllCustomers()) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }

    public String displayCustomerDetails(Customer customer) {
        return String.format("Customer Details: Name: %s, Address: %s, UUID: %s",
                             customer.getName(), customer.getAddress(), customer.getUuid());
    }

}
