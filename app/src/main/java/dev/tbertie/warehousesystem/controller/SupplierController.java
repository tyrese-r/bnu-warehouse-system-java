package dev.tbertie.warehousesystem.controller;

import dev.tbertie.warehousesystem.model.Item;
import dev.tbertie.warehousesystem.model.Supplier;
import dev.tbertie.warehousesystem.repository.InventoryRepository;
import dev.tbertie.warehousesystem.repository.SupplierRepository;
import dev.tbertie.warehousesystem.service.SupplierService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SupplierController {
    private final SupplierService supplierService;
    private final SupplierRepository supplierRepo = SupplierRepository.getInstance();
    private final InventoryRepository inventoryRepo = InventoryRepository.getInstance();

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // Create a new supplier
    public Supplier createSupplier(String name, String contactInfo, List<String> purchases, List<String> deliveries) {
        Supplier supplier = new Supplier(UUID.randomUUID(), name, contactInfo, purchases, deliveries);
        supplierRepo.addSupplier(supplier);
        return supplier;
    }
    
    // Create a simple supplier with minimal info
    public Supplier createSupplier(String name, String contactInfo) {
        return createSupplier(name, contactInfo, new ArrayList<>(), new ArrayList<>());
    }

    // Read all suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierRepo.getAllSuppliers();
    }

    // Read a supplier by UUID
    public Supplier getSupplierByUuid(UUID uuid) {
        return supplierRepo.getSupplierByUuid(uuid);
    }

    // Update supplier details
    public void updateSupplier(UUID uuid, String newName, String newContactInfo, List<String> newPurchases, List<String> newDeliveries) {
        Supplier supplier = supplierRepo.getSupplierByUuid(uuid);
        if (supplier != null) {
            supplier.setName(newName);
            supplier.setContactInfo(newContactInfo);
            supplier.setPurchases(newPurchases);
            supplier.setDeliveries(newDeliveries);
        } else {
            throw new IllegalArgumentException("Supplier not found");
        }
    }

    // Delete a supplier
    public void deleteSupplier(Supplier supplier) {
        supplierRepo.removeSupplier(supplier);
    }
    
    // Delete a supplier by UUID
    public boolean deleteSupplier(UUID uuid) {
        Supplier supplier = supplierRepo.getSupplierByUuid(uuid);
        if (supplier != null) {
            supplierRepo.removeSupplier(supplier);
            return true;
        }
        return false;
    }
    
    // Search suppliers by name
    public List<Supplier> searchSuppliersByName(String name) {
        return supplierRepo.getAllSuppliers().stream()
                .filter(supplier -> supplier.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
    
    // Display supplier details
    public String displaySupplierDetails(Supplier supplier) {
        if (supplier == null) return "Supplier not found";
        
        // Query inventory for items supplied by this supplier
        List<Item> supplierItems = inventoryRepo.getItemsBySupplier(supplier.getUuid());
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== Supplier Details ===\n");
        sb.append("ID: ").append(supplier.getUuid()).append("\n");
        sb.append("Name: ").append(supplier.getName()).append("\n");
        sb.append("Contact Info: ").append(supplier.getContactInfo()).append("\n");
        sb.append("Number of Items in Inventory: ").append(supplierItems.size()).append("\n");
        sb.append("Number of Purchases: ").append(supplier.getPurchases().size()).append("\n");
        sb.append("Number of Deliveries: ").append(supplier.getDeliveries().size()).append("\n");
        return sb.toString();
    }
    
    // Get items supplied by this supplier from inventory
    public List<Item> getSupplierItems(UUID supplierId) {
        return inventoryRepo.getItemsBySupplier(supplierId);
    }
    
    // Individual field update methods
    public boolean updateSupplierName(UUID uuid, String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            return false;
        }
        Supplier supplier = supplierRepo.getSupplierByUuid(uuid);
        if (supplier != null) {
            supplier.setName(newName.trim());
            return true;
        }
        return false;
    }
    
    public boolean updateSupplierContact(UUID uuid, String newContact) {
        if (newContact == null || newContact.trim().isEmpty()) {
            return false;
        }
        Supplier supplier = supplierRepo.getSupplierByUuid(uuid);
        if (supplier != null) {
            supplier.setContactInfo(newContact.trim());
            return true;
        }
        return false;
    }
    
}