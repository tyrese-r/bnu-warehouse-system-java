package dev.tbertie.warehousesystem.ui.customer;

import dev.tbertie.warehousesystem.controller.CustomerController;
import dev.tbertie.warehousesystem.model.Customer;
import dev.tbertie.warehousesystem.ui.Menu;
import dev.tbertie.warehousesystem.ui.UIController;
import dev.tbertie.warehousesystem.ui.UIUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MenuCustomer extends Menu {

    CustomerController customerController;


    @Override
    public void render() {
        System.out.println(UIUtils.generateMenu("Customers", List.of("view all", "view one", "add", "search", "delete")));
    }

    @Override
    public void command(String[] command) {
        String action = command[0].toLowerCase();
        Scanner scanner = new Scanner(System.in);

        // Handle multi-word commands
        if (action.equals("view") && command.length > 1) {
            String subAction = command[1].toLowerCase();
            if (subAction.equals("all")) {
                displayAllCustomers();
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                return;
            } else if (subAction.equals("one")) {
                handleViewOne(command);
                return;
            }
        }

        switch (action) {
            case "1":
                displayAllCustomers();
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                break;
            case "2":
                handleViewOne(command);
                break;
            case "3":
            case "add":
                handleAdd(command);
                break;
            case "4":
            case "search":
                handleSearch(command);
                break;
            case "5":
            case "delete":
                handleDelete(command);
                break;
            default:
                System.out.println("Unknown command. Available commands: view all, view one [ID], add [NAME] [ADDRESS] [PHONE], search [NAME], delete [ID]");
                break;
        }
    }

    public MenuCustomer(String name, UIController uiController) {
        super(name, uiController);
        this.customerController = new CustomerController();
    }

    private void displayAllCustomers() {
        customerController.getAllCustomers().forEach(customer -> 
            System.out.println(customerController.displayCustomerDetails(customer))
        );
    }

    private void handleViewOne(String[] command) {
        if (command.length < 3) {
            System.out.println("Usage: view one [CUSTOMER_ID]");
            return;
        }

        try {
            UUID customerId = UUID.fromString(command[2]);
            Customer customer = customerController.getCustomerByUuid(customerId);
            if (customer != null) {
                System.out.println(customerController.displayCustomerDetails(customer));
            } else {
                System.out.println("Customer not found with ID: " + customerId);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid customer ID format: " + command[2]);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private void handleAdd(String[] command) {
        if (command.length < 4) {
            System.out.println("Usage: add [CUSTOMER_NAME] [CUSTOMER_ADDRESS] [CUSTOMER_PHONE]");
            return;
        }

        String name = command[1];
        String address = command[2];
        String phone = command[3];

        try {
            customerController.createCustomer(name, address, phone);
            System.out.println("Customer added successfully: " + name);
        } catch (Exception e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private void handleSearch(String[] command) {
        if (command.length < 2) {
            System.out.println("Usage: search [CUSTOMER_NAME]");
            return;
        }

        String name = command[1];
        List<Customer> customers = customerController.searchCustomerByName(name);

        if (customers.isEmpty()) {
            System.out.println("No customers found with name: " + name);
        } else {
            System.out.println("Found " + customers.size() + " customer(s):");
            customers.forEach(customer -> 
                System.out.println(customerController.displayCustomerDetails(customer))
            );
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private void handleDelete(String[] command) {
        if (command.length < 2) {
            System.out.println("Usage: delete [CUSTOMER_ID]");
            return;
        }

        try {
            UUID customerId = UUID.fromString(command[1]);
            Customer customer = customerController.getCustomerByUuid(customerId);
            if (customer != null) {
                customerController.deleteCustomer(customerId);
                System.out.println("Customer deleted successfully: " + customer.getName());
            } else {
                System.out.println("Customer not found with ID: " + customerId);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid customer ID format: " + command[1]);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }
}
