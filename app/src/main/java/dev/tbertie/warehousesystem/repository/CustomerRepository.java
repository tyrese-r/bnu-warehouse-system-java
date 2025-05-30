package dev.tbertie.warehousesystem.repository;

import dev.tbertie.warehousesystem.model.Customer;

import java.util.*;

public class CustomerRepository {

    private CustomerRepository() {}
    private static CustomerRepository instance;

    public static CustomerRepository getInstance() {
        if(instance == null) {
            instance = new CustomerRepository();
        }
        return instance;
    }

    private Map<UUID, Customer> customers = new HashMap<>();

    public void addCustomer(Customer customer) {
        customers.put(customer.getUuid(), customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer.getUuid());
    }

    public Customer getCustomerByUuid(UUID uuid) {
        return customers.get(uuid);
    }

    public Customer getCustomerByUuid(String uuid) {
        return getCustomerByUuid(UUID.fromString(uuid));
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }
}
