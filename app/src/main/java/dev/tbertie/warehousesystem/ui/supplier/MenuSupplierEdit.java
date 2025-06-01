package dev.tbertie.warehousesystem.ui.supplier;

import dev.tbertie.warehousesystem.controller.SupplierController;
import dev.tbertie.warehousesystem.model.Item;
import dev.tbertie.warehousesystem.model.Supplier;
import dev.tbertie.warehousesystem.service.SupplierService;
import dev.tbertie.warehousesystem.ui.Menu;
import dev.tbertie.warehousesystem.ui.UIController;
import dev.tbertie.warehousesystem.ui.UIUtils;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MenuSupplierEdit extends Menu {

    private SupplierController supplierController;
    private UUID supplierId;
    private Supplier supplier;

    public MenuSupplierEdit(String name, UIController uiController, UUID supplierId) {
        super(name, uiController);
        this.supplierController = new SupplierController(null);
        this.supplierId = supplierId;
        this.supplier = supplierController.getSupplierByUuid(supplierId);
    }

    @Override
    public void render() {
        if (supplier == null) {
            System.out.println("Error: Supplier not found");
            return;
        }

        System.out.println(supplierController.displaySupplierDetails(supplier));

        System.out.println(UIUtils.generateMenu("Edit " + supplier.getName(), List.of(
                "edit name",
                "edit contact", 
                "view items",
                "refresh details"
        )));
    }

    @Override
    public void command(String[] command) {
        if (supplier == null) {
            System.out.println("Error: Supplier not found. Returning to previous menu.");
            uiController.navigateBack();
            return;
        }

        String action = command[0].toLowerCase();
        Scanner scanner = new Scanner(System.in);

        if (action.equals("edit") && command.length > 1) {
            String subAction = command[1].toLowerCase();
            if (subAction.equals("name")) {
                handleEditName(command);
                return;
            } else if (subAction.equals("contact")) {
                handleEditContact(command);
                return;
            }
        }


        if (action.equals("view") && command.length > 1) {
            String subAction = command[1].toLowerCase();
            if (subAction.equals("items")) {
                handleViewItems();
                return;
            }
        }

        if (action.equals("refresh") && command.length > 1) {
            String subAction = command[1].toLowerCase();
            if (subAction.equals("details")) {
                handleRefresh();
                return;
            }
        }

        switch (action) {
            case "1":
                handleEditName(command);
                break;
            case "2":
                handleEditContact(command);
                break;
            case "3":
                handleViewItems();
                break;
            case "4":
                handleRefresh();
                break;
            default:
                System.out.println("Unknown command");
                break;
        }
    }

    private void handleEditName(String[] command) {
        if (command.length < 3) {
            System.out.println("Usage: edit name [NEW_NAME]");
            return;
        }

        String newName = command[2];
        boolean success = supplierController.updateSupplierName(supplierId, newName);
        
        if (success) {
            System.out.println("Supplier name updated successfully: " + newName);
            supplier = supplierController.getSupplierByUuid(supplierId);
        } else {
            System.out.println("Failed to update supplier name. Please check the name is valid.");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private void handleEditContact(String[] command) {
        if (command.length < 3) {
            System.out.println("Usage: edit contact [NEW_CONTACT]");
            return;
        }

        String newContact = command[2];
        boolean success = supplierController.updateSupplierContact(supplierId, newContact);
        
        if (success) {
            System.out.println("Supplier contact updated successfully: " + newContact);
            supplier = supplierController.getSupplierByUuid(supplierId);
        } else {
            System.out.println("Failed to update supplier contact. Please check the contact is valid.");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }


    private void handleViewItems() {
        List<Item> supplierItems = supplierController.getSupplierItems(supplierId);
        
        if (supplierItems.isEmpty()) {
            System.out.println("No items found in inventory for this supplier.");
        } else {
            System.out.println("=== " + supplier.getName() + " Items in Inventory ===");
            for (int i = 0; i < supplierItems.size(); i++) {
                Item item = supplierItems.get(i);
                System.out.println((i + 1) + ". " + item.getName() + " - Stock: " + item.getInStock() + " - Price: £" + String.format("%.2f", item.getPrice() / 100.0));
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private void handleRefresh() {
        supplier = supplierController.getSupplierByUuid(supplierId);
        if (supplier != null) {
            System.out.println("Supplier details refreshed.");
        } else {
            System.out.println("Error: Supplier no longer exists.");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }
}