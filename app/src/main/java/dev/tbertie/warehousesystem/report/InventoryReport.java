package dev.tbertie.warehousesystem.report;

import dev.tbertie.warehousesystem.service.InventoryService;

public class InventoryReport {
    private final InventoryService inventoryService;
    
    public InventoryReport(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    
    public void generateCurrentStockReport() {

    }
    
    public void generateLowStockReport() {

    }
    
    public void generateStockValueReport() {

    }
    
    public void generateSupplierDistributionReport() {

    }
}