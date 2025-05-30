package dev.tbertie.warehousesystem.controller;

import dev.tbertie.warehousesystem.model.Supplier;
import dev.tbertie.warehousesystem.repository.SupplierRepository;
import dev.tbertie.warehousesystem.service.SupplierService;

import java.util.List;
import java.util.UUID;

public class SupplierController {
    private final SupplierService supplierService;
    private final SupplierRepository supplierRepo = SupplierRepository.getInstance();

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // Create a new supplier
    public void createSupplier(String name, String contactInfo, List<String> purchases, List<String> items, List<String> deliveries) {
        Supplier supplier = new Supplier(UUID.randomUUID(), name, contactInfo, purchases, items, deliveries);
        supplierRepo.addSupplier(supplier);
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
    public void updateSupplier(UUID uuid, String newName, String newContactInfo, List<String> newPurchases, List<String> newItems, List<String> newDeliveries) {
        Supplier supplier = supplierRepo.getSupplierByUuid(uuid);
        if (supplier != null) {
            supplier.setName(newName);
            supplier.setContactInfo(newContactInfo);
            supplier.setPurchases(newPurchases);
            supplier.setItems(newItems);
            supplier.setDeliveries(newDeliveries);
        } else {
            throw new IllegalArgumentException("Supplier not found");
        }
    }

    // Delete a supplier
    public void deleteSupplier(Supplier supplier) {
        supplierRepo.removeSupplier(supplier);
    }
}