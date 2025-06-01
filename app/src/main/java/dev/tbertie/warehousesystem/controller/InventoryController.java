package dev.tbertie.warehousesystem.controller;

import dev.tbertie.warehousesystem.model.Item;
import dev.tbertie.warehousesystem.model.Supplier;
import dev.tbertie.warehousesystem.repository.InventoryRepository;
import dev.tbertie.warehousesystem.repository.SupplierRepository;
import dev.tbertie.warehousesystem.service.InventoryService;

import java.util.List;
import java.util.UUID;

public class InventoryController {
    private final InventoryService inventoryService;
    private final InventoryRepository inventoryRepo = InventoryRepository.getInstance();
    private final SupplierRepository supplierRepo = SupplierRepository.getInstance();
    
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    
    public void addItemToInventory(Item item, int quantity) {
        inventoryRepo.addItem(item);
    }
    
    public boolean removeItemFromInventory(Item item, int quantity) {
        return inventoryRepo.getInventory().removeItem(item, quantity);
    }
    
    public String displayAllItems() {
        StringBuilder sb = new StringBuilder();
        List<Item> items = inventoryRepo.getAllItems();
        if (items.isEmpty()) {
            sb.append("No items in inventory.\n");
        } else {
            items.forEach(item -> sb.append(displayItemDetails(item)));
        }
        return sb.toString();
    }
    
    public String displayLowStockItems() {
        StringBuilder sb = new StringBuilder();
        List<Item> lowStockItems = inventoryRepo.getInventory().getLowStockItems();
        if (lowStockItems.isEmpty()) {
            sb.append("No low stock items.").append("\n");
        } else {
            sb.append("=== Low Stock Items ===").append("\n");
            lowStockItems.forEach(item -> sb.append(displayItemDetails(item)).append("\n"));
        }
        return sb.toString();
    }
    
    public Item createItem(String name, String supplierName, String description, Integer price, Integer inStock) {
        UUID supplierId = null;
        if (supplierName != null && !supplierName.trim().isEmpty()) {
            Supplier supplier = supplierRepo.getAllSuppliers().stream()
                    .filter(s -> s.getName().equals(supplierName))
                    .findFirst()
                    .orElse(null);
            if (supplier != null) {
                supplierId = supplier.getUuid();
            }
        }
        
        Item item = new Item(name, supplierId, description, price, inStock);
        inventoryRepo.addItem(item);
        return item;
    }
    
    public Item createItemWithSupplierId(String name, UUID supplierId, String description, Integer price, Integer inStock) {
        Item item = new Item(name, supplierId, description, price, inStock);
        inventoryRepo.addItem(item);
        return item;
    }
    
    public List<Item> getItemsBySupplier(String supplierName) {
        return inventoryRepo.getItemsBySupplierName(supplierName);
    }
    
    public List<Item> getItemsBySupplier(UUID supplierId) {
        return inventoryRepo.getItemsBySupplier(supplierId);
    }
    
    public List<Item> getAllItems() {
        return inventoryRepo.getAllItems();
    }
    
    public boolean removeItemFromInventory(String itemName) {
        return inventoryRepo.removeItem(itemName);
    }
    
    public String displayItemDetails(Item item) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Item Details ===\n");
        sb.append("Name: ").append(item.getName()).append("\n");
        sb.append("Description: ").append(item.getDescription()).append("\n");

        double decimalPrice = item.getPrice() / 100.0;

        sb.append(String.format("Price: £%.2f", decimalPrice)).append("\n");
        sb.append("In Stock: ").append(item.getInStock()).append("\n");
        if (item.getSupplierId() != null) {
            Supplier supplier = supplierRepo.getSupplierByUuid(item.getSupplierId());
            if (supplier != null) {
                sb.append("Supplier: ").append(supplier.getName()).append("\n");
            } else {
                sb.append("Supplier: Unknown (ID: ").append(item.getSupplierId()).append(")\n");
            }
        } else {
            sb.append("Supplier: Not assigned\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}