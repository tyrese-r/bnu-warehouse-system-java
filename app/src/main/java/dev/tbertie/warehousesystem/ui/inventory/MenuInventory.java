package dev.tbertie.warehousesystem.ui.inventory;

import dev.tbertie.warehousesystem.controller.InventoryController;
import dev.tbertie.warehousesystem.controller.SupplierController;
import dev.tbertie.warehousesystem.model.Item;
import dev.tbertie.warehousesystem.model.Supplier;
import dev.tbertie.warehousesystem.ui.Menu;
import dev.tbertie.warehousesystem.ui.UIController;
import dev.tbertie.warehousesystem.ui.UIUtils;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MenuInventory extends Menu {

    InventoryController inventoryController;
    SupplierController supplierController;

    @Override
    public void render() {
        System.out.println(UIUtils.generateMenu("Inventory Management", List.of(
                "view all items", 
                "view low stock", 
                "add item", 
                "search by supplier", 
                "update stock",
                "remove item"
        )));
    }

    @Override
    public void command(String[] command) {
        String action = command[0].toLowerCase();
        Scanner scanner = new Scanner(System.in);

        // Handle multi-word commands
        if (action.equals("view") && command.length > 1) {
            String subAction = command[1].toLowerCase();
            if (subAction.equals("all")) {
                displayAllItems();
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                return;
            } else if (subAction.equals("low")) {
                displayLowStockItems();
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                return;
            }
        }

        if (action.equals("search") && command.length > 1) {
            String subAction = command[1].toLowerCase();
            if (subAction.equals("by") && command.length > 2 && command[2].equals("supplier")) {
                handleSearchBySupplier(command);
                return;
            }
        }

        if (action.equals("add") && command.length > 1) {
            String subAction = command[1].toLowerCase();
            if (subAction.equals("item")) {
                handleAddItem(command);
                return;
            }
        }

        if (action.equals("update") && command.length > 1) {
            String subAction = command[1].toLowerCase();
            if (subAction.equals("stock")) {
                handleUpdateStock(command);
                return;
            }
        }

        if (action.equals("remove") && command.length > 1) {
            String subAction = command[1].toLowerCase();
            if (subAction.equals("item")) {
                handleRemoveItem(command);
                return;
            }
        }

        switch (action) {
            case "1":
                displayAllItems();
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                break;
            case "2":
                displayLowStockItems();
                System.out.println("Press enter to continue...");
                scanner.nextLine();
                break;
            case "3":
                handleAddItem(command);
                break;
            case "4":
                handleSearchBySupplier(command);
                break;
            case "5":
                handleUpdateStock(command);
                break;
            case "6":
                handleRemoveItem(command);
                break;
            default:
                System.out.println("Unknown command. Available commands:");
                System.out.println("1. view all - View all items");
                System.out.println("2. view low - View low stock items");
                System.out.println("3. add item [NAME] [SUPPLIER_ID] [DESCRIPTION] [PRICE] [STOCK] - Add new item");
                System.out.println("4. search by supplier [SUPPLIER_NAME] - Search items by supplier");
                System.out.println("5. update stock [ITEM_NAME] [NEW_STOCK] - Update item stock");
                System.out.println("6. remove item [ITEM_NAME] - Remove item from inventory");
                break;
        }
    }

    public MenuInventory(String name, UIController uiController) {
        super(name, uiController);
        this.inventoryController = new InventoryController(null);
        this.supplierController = new SupplierController(null);
    }

    private void displayAllItems() {
        System.out.print(inventoryController.displayAllItems());
    }

    private void displayLowStockItems() {
        System.out.println(inventoryController.displayLowStockItems());
    }

    private void handleAddItem(String[] command) {
        if (command.length < 6) {
            System.out.println("Usage: add item [NAME] [SUPPLIER_ID] [DESCRIPTION] [PRICE] [STOCK]");
            System.out.println("Use 'null' for SUPPLIER_ID if no supplier assigned");
            listAvailableSuppliers();
            return;
        }

        try {
            String name = command[2];
            String supplierIdStr = command[3];
            String description = command[4];
            Integer price = Integer.parseInt(command[5]);
            Integer stock = Integer.parseInt(command[6]);

            UUID supplierId = null;
            if (!supplierIdStr.equals("null") && !supplierIdStr.trim().isEmpty()) {
                try {
                    supplierId = UUID.fromString(supplierIdStr);
                    Supplier supplier = supplierController.getSupplierByUuid(supplierId);
                    if (supplier == null) {
                        System.out.println("Supplier not found with ID: " + supplierId);
                        listAvailableSuppliers();
                        return;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid supplier ID format: " + supplierIdStr);
                    listAvailableSuppliers();
                    return;
                }
            }

            Item item = inventoryController.createItemWithSupplierId(name, supplierId, description, price, stock);
            System.out.println("Item added successfully:");
            System.out.print(inventoryController.displayItemDetails(item));
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for price or stock");
        } catch (Exception e) {
            System.out.println("Error adding item: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private void handleSearchBySupplier(String[] command) {
        if (command.length < 4) {
            System.out.println("Usage: search by supplier [SUPPLIER_NAME]");
            return;
        }

        String supplierName = command[3];
        List<Item> items = inventoryController.getItemsBySupplier(supplierName);

        if (items.isEmpty()) {
            System.out.println("No items found for supplier: " + supplierName);
        } else {
            System.out.println("Found " + items.size() + " item(s) for supplier: " + supplierName);
            items.forEach(item -> System.out.print(inventoryController.displayItemDetails(item)));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private void handleUpdateStock(String[] command) {
        if (command.length < 4) {
            System.out.println("Usage: update stock [ITEM_NAME] [NEW_STOCK]");
            return;
        }

        try {
            String itemName = command[2];
            Integer newStock = Integer.parseInt(command[3]);

            // Find item by name
            List<Item> allItems = inventoryController.getAllItems();
            Item item = allItems.stream()
                    .filter(i -> i.getName().equals(itemName))
                    .findFirst()
                    .orElse(null);

            if (item == null) {
                System.out.println("Item not found: " + itemName);
            } else {
                item.setInStock(newStock);
                System.out.println("Stock updated successfully for: " + itemName);
                System.out.print(inventoryController.displayItemDetails(item));
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid stock number: " + command[3]);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private void handleRemoveItem(String[] command) {
        if (command.length < 3) {
            System.out.println("Usage: remove item [ITEM_NAME]");
            return;
        }

        String itemName = command[2];
        
        // Find item first to show details
        List<Item> allItems = inventoryController.getAllItems();
        Item item = allItems.stream()
                .filter(i -> i.getName().equals(itemName))
                .findFirst()
                .orElse(null);

        if (item == null) {
            System.out.println("Item not found: " + itemName);
        } else {
            System.out.println("Item to be removed:");
            System.out.print(inventoryController.displayItemDetails(item));
            
            System.out.print("Are you sure you want to remove this item? (y/N): ");
            Scanner scanner = new Scanner(System.in);
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (confirmation.equals("y") || confirmation.equals("yes")) {
                boolean removed = inventoryController.removeItemFromInventory(itemName);
                if (removed) {
                    System.out.println("Item removed successfully: " + itemName);
                } else {
                    System.out.println("Failed to remove item: " + itemName);
                }
            } else {
                System.out.println("Item removal cancelled.");
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
    }

    private void listAvailableSuppliers() {
        List<Supplier> suppliers = supplierController.getAllSuppliers();
        if (suppliers.isEmpty()) {
            System.out.println("No suppliers available. Add suppliers first.");
        } else {
            System.out.println("\nAvailable suppliers:");
            suppliers.forEach(supplier -> 
                System.out.println("ID: " + supplier.getUuid() + " - Name: " + supplier.getName())
            );
        }
    }
}