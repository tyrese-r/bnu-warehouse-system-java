package dev.tbertie.warehousesystem.model;

import dev.tbertie.warehousesystem.App;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    Customer customer;
    final String customerName = "John Doe";
    final String customerAddress = "Fake Street, London SW1A 1AA";
    final String customerPhone = "+0 12345";

    @BeforeEach
    void setUp() {
        // Create customer
        customer = new Customer(customerName, customerAddress, customerPhone);
    }

    @Test
    void customerShouldBeCreatedWithCorrectFields() {
        assertEquals(customerName, customer.getName());
        assertEquals(customerAddress, customer.getAddress());
    }
}
