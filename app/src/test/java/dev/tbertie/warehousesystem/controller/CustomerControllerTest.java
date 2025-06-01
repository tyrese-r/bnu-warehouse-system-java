package dev.tbertie.warehousesystem.controller;

import dev.tbertie.warehousesystem.model.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.UUID;

class CustomerControllerTest {

    @Test
    void searchCustomerByNameReturnsCorrectCustomer() {
        CustomerController controller = new CustomerController();
        controller.createCustomer("John Doe", "123 Street", "0001");
        List<Customer> result = controller.searchCustomerByName("John Doe");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    void searchCustomerByNameReturnsEmptyListForNonExistentCustomer() {
        CustomerController controller = new CustomerController();
        controller.createCustomer("Jane Doe", "45 Oak Street", "1");
        List<Customer> result = controller.searchCustomerByName("Non Existent");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void searchCustomerByNameIsCaseInsensitive() {
        CustomerController controller = new CustomerController();
        controller.createCustomer("Alice Smith", "678 No Road", "5678");
        List<Customer> result = controller.searchCustomerByName("alice smith");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Alice Smith", result.get(0).getName());
    }

    @Test
    void getCustomerByUuidReturnsCorrectCustomer() {
        CustomerController controller = new CustomerController();
        controller.createCustomer("Bob Brown", "321 Maple Street", "1234");
        List<Customer> createdCustomers = controller.searchCustomerByName("Bob Brown");
        Customer result = controller.getCustomerByUuid(createdCustomers.get(0).getUuid());
        assertNotNull(result);
        assertEquals("Bob Brown", result.getName());
    }

    @Test
    void getCustomerByUuidReturnsNullForInvalidUuid() {
        CustomerController controller = new CustomerController();
        UUID invalidUuid = UUID.randomUUID();
        Customer result = controller.getCustomerByUuid(invalidUuid);
        assertNull(result);
    }

    @Test
    void updateNameChangesCustomerNameSuccessfully() throws Exception {
        CustomerController controller = new CustomerController();
        controller.createCustomer("Original Name", "123 Street", "1234");
        List<Customer> customers = controller.searchCustomerByName("Original Name");
        Customer customer = customers.get(0);
        controller.updateName(customer, "Updated Name");
        assertEquals("Updated Name", customer.getName());
    }

    @Test
    void updateNameThrowsExceptionWhenNameIsNull() {
        CustomerController controller = new CustomerController();
        controller.createCustomer("Original Name", "123 Street", "12345");
        List<Customer> customers = controller.searchCustomerByName("Original Name");
        Customer customer = customers.get(0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.updateName(customer, null);
        });
        assertEquals("Name must not be null", exception.getMessage());
    }
}
