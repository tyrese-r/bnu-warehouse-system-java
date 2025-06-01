package dev.tbertie.warehousesystem.repository;

import dev.tbertie.warehousesystem.model.Inventory;
import dev.tbertie.warehousesystem.model.Item;

import java.util.*;
import java.util.UUID;

public class InventoryRepository {

    private InventoryRepository() {}
    private static InventoryRepository instance;

    public static InventoryRepository getInstance() {
        if(instance == null) {
            instance = new InventoryRepository();
        }
        return instance;
    }

    private Map<String, Item> items = new HashMap<>();
    private Inventory inventory = new Inventory(10); // Default low stock threshold of 10

    public void addItem(Item item) {
        items.put(item.getName(), item);
        inventory.addItem(item, item.getInStock());
    }

    public boolean removeItem(String itemName) {
        Item removed = items.remove(itemName);
        return removed != null;
    }

    public Item getItemByName(String name) {
        return items.get(name);
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }

    public List<Item> getItemsBySupplier(UUID supplierId) {
        return items.values().stream()
                .filter(item -> item.getSupplierId() != null && 
                               item.getSupplierId().equals(supplierId))
                .toList();
    }
    
    public List<Item> getItemsBySupplierName(String supplierName) {
        // This method requires looking up supplier by name first
        SupplierRepository supplierRepo = SupplierRepository.getInstance();
        return supplierRepo.getAllSuppliers().stream()
                .filter(supplier -> supplier.getName().equals(supplierName))
                .findFirst()
                .map(supplier -> getItemsBySupplier(supplier.getUuid()))
                .orElse(new ArrayList<>());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void updateItemStock(String itemName, int newStock) {
        Item item = items.get(itemName);
        if (item != null) {
            item.setInStock(newStock);
        }
    }
}