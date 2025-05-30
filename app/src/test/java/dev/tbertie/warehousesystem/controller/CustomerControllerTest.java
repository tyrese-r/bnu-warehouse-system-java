package dev.tbertie.warehousesystem.controller;

import dev.tbertie.warehousesystem.model.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

class CustomerControllerTest {

    @Test
    void searchCustomerByNameReturnsCorrectCustomer() {
        CustomerController controller = new CustomerController();
        controller.createCustomer("John Doe", "123 Street");
        Customer result = controller.searchCustomerByName("John Doe");
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void searchCustomerByNameReturnsNullForNonExistentCustomer() {
        CustomerController controller = new CustomerController();
        controller.createCustomer("Jane Doe", "45 Oak Street");
        Customer result = controller.searchCustomerByName("Non Existent");
        assertNull(result);
    }

    @Test
    void searchCustomerByNameIsCaseInsensitive() {
        CustomerController controller = new CustomerController();
        controller.createCustomer("Alice Smith", "678 No Road");
        Customer result = controller.searchCustomerByName("alice smith");
        assertNotNull(result);
        assertEquals("Alice Smith", result.getName());
    }

    @Test
    void getCustomerByUuidReturnsCorrectCustomer() {
        CustomerController controller = new CustomerController();
        controller.createCustomer("Bob Brown", "321 Maple Street");
        Customer createdCustomer = controller.searchCustomerByName("Bob Brown");
        Customer result = controller.getCustomerByUuid(createdCustomer.getUuid());
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
        controller.createCustomer("Original Name", "123 Street");
        Customer customer = controller.searchCustomerByName("Original Name");
        controller.updateName(customer, "Updated Name");
        assertEquals("Updated Name", customer.getName());
    }

    @Test
    void updateNameThrowsExceptionWhenNameIsNull() {
        CustomerController controller = new CustomerController();
        controller.createCustomer("Original Name", "123 Street");
        Customer customer = controller.searchCustomerByName("Original Name");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.updateName(customer, null);
        });
        assertEquals("Name must not be null", exception.getMessage());
    }
}
