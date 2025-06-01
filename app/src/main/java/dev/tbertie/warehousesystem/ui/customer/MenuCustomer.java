package dev.tbertie.warehousesystem.ui.customer;

import dev.tbertie.warehousesystem.controller.CustomerController;
import dev.tbertie.warehousesystem.ui.Menu;
import dev.tbertie.warehousesystem.ui.UIController;
import dev.tbertie.warehousesystem.ui.UIUtils;

import java.util.List;
import java.util.Scanner;

public class MenuCustomer extends Menu {

    CustomerController customerController;


    @Override
    public void render() {
        System.out.println(UIUtils.generateMenu("Customers", List.of("view all", "view one", "add", "delete")));
    }

    @Override
    public void command(String[] command) {
        String action = command[0].toLowerCase();


        switch (action) {
            case "1":
            case "view all":
                displayAllCustomers();
                Scanner scanner = new Scanner(System.in);
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                break;
            default:
                break;
        }
    }

    public MenuCustomer(String name, UIController uiController) {
        super(name, uiController);
        this.customerController = new CustomerController();
    }

    private void displayAllCustomers() {

        customerController.getAllCustomers().forEach(customer -> {
            System.out.println(customerController.displayCustomerDetails(customer));
        });
    }
}
