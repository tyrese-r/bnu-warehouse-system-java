package dev.tbertie.warehousesystem.controller;

import dev.tbertie.warehousesystem.model.Item;
import dev.tbertie.warehousesystem.model.Supplier;
import dev.tbertie.warehousesystem.model.SupplierOrder;
import dev.tbertie.warehousesystem.service.SupplierService;

public class SupplierController {
    private final SupplierService supplierService;
    
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }
    
    public void addSupplier(Supplier supplier) {

    }
    
    public void displayAllSuppliers() {

    }
    
    public SupplierOrder createSupplierOrder(Supplier supplier) {
        return null;
    }
    
    public void addItemToSupplierOrder(SupplierOrder order, Item item, int quantity) {

    }
    
    public boolean submitSupplierOrder(SupplierOrder order) {

        return false;
    }
    
    public boolean receiveSupplierOrder(SupplierOrder order) {
        return false;
    }
    
    public void displayAllSupplierOrders() {
    }
}