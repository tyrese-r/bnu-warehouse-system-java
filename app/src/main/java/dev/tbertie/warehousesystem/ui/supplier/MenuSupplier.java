package dev.tbertie.warehousesystem.ui.supplier;

import dev.tbertie.warehousesystem.controller.SupplierController;
import dev.tbertie.warehousesystem.model.Supplier;
import dev.tbertie.warehousesystem.service.SupplierService;
import dev.tbertie.warehousesystem.ui.Menu;
import dev.tbertie.warehousesystem.ui.UIController;
import dev.tbertie.warehousesystem.ui.UIUtils;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MenuSupplier extends Menu {

    SupplierController supplierController;

    @Override
    public void render() {
        System.out.println(UIUtils.generateMenu("Suppliers", List.of("view all", "view one", "add", "edit", "search", "delete")));
    }

    @Override
    public void command(String[] command) {
        String action = command[0].toLowerCase();
        Scanner scanner = new Scanner(System.in);

        // Handle multi-word commands
        if (action.equals("view") && command.length > 1) {
            String subAction = command[1].toLowerCase();
            if (subAction.equals("all")) {
                displayAllSuppliers();
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
                displayAllSuppliers();
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
            case "edit":
                handleEdit(command);
                break;
            case "5":
            case "search":
                handleSearch(command);
                break;
            case "6":
            case "delete":
                handleDelete(command);
                break;
            default:
                System.out.println("Unknown command. Available commands: view all, view one [ID], add [NAME] [CONTACT], edit [ID], search [NAME], delete [ID]");
                break;
        }
    }

    public MenuSupplier(String name, UIController uiController) {
        super(name, uiController);
        this.supplierController = new SupplierController(null);
    }

    private void displayAllSuppliers() {
        List<Supplier> suppliers = supplierController.getAllSuppliers();
        if (suppliers.isEmpty()) {
            System.out.println("No suppliers found.");
        } else {
            suppliers.forEach(supplier -> 
                System.out.println(supplierController.displaySupplierDetails(supplier))
            );
        }
    }

    private void handleViewOne(String[] command) {
        if (command.length < 3) {
            System.out.println("Usage: view one [SUPPLIER_ID]");
            return;
        }

        try {
            UUID supplierId = UUID.fromString(command[2]);
            Supplier supplier = supplierController.getSupplierByUuid(supplierId);
            if (supplier != null) {
                System.out.println(supplierController.displaySupplierDetails(supplier));
            } else {
                System.out.println("Supplier not found with ID: " + supplierId);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid supplier ID format: " + command[2]);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private void handleAdd(String[] command) {
        if (command.length < 3) {
            System.out.println("Usage: add [SUPPLIER_NAME] [CONTACT_INFO]");
            return;
        }

        String name = command[1];
        String contactInfo = command[2];

        try {
            Supplier supplier = supplierController.createSupplier(name, contactInfo);
            System.out.println("Supplier added successfully: " + name + " (ID: " + supplier.getUuid() + ")");
        } catch (Exception e) {
            System.out.println("Error adding supplier: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private void handleSearch(String[] command) {
        if (command.length < 2) {
            System.out.println("Usage: search [SUPPLIER_NAME]");
            return;
        }

        String name = command[1];
        List<Supplier> suppliers = supplierController.searchSuppliersByName(name);

        if (suppliers.isEmpty()) {
            System.out.println("No suppliers found with name: " + name);
        } else {
            System.out.println("Found " + suppliers.size() + " supplier(s):");
            suppliers.forEach(supplier -> 
                System.out.println(supplierController.displaySupplierDetails(supplier))
            );
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private void handleDelete(String[] command) {
        if (command.length < 2) {
            System.out.println("Usage: delete [SUPPLIER_ID]");
            return;
        }

        try {
            UUID supplierId = UUID.fromString(command[1]);
            Supplier supplier = supplierController.getSupplierByUuid(supplierId);
            if (supplier != null) {
                boolean deleted = supplierController.deleteSupplier(supplierId);
                if (deleted) {
                    System.out.println("Supplier deleted successfully: " + supplier.getName());
                } else {
                    System.out.println("Failed to delete supplier.");
                }
            } else {
                System.out.println("Supplier not found with ID: " + supplierId);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid supplier ID format: " + command[1]);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private void handleEdit(String[] command) {
        if (command.length < 2) {
            System.out.println("Usage: edit [SUPPLIER_ID]");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press enter to continue...");
            scanner.nextLine();
            return;
        }

        try {
            UUID supplierId = UUID.fromString(command[1]);
            Supplier supplier = supplierController.getSupplierByUuid(supplierId);
            if (supplier != null) {
                uiController.pushMenuToStack(new MenuSupplierEdit(supplier.getName(), uiController, supplierId));
            } else {
                System.out.println("Supplier not found with ID: " + supplierId);
                Scanner scanner = new Scanner(System.in);
                System.out.println("Press enter to continue...");
                scanner.nextLine();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid supplier ID format: " + command[1]);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press enter to continue...");
            scanner.nextLine();
        }
    }

}