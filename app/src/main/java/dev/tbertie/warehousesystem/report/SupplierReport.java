package dev.tbertie.warehousesystem.report;

import dev.tbertie.warehousesystem.model.SupplierOrder;
import dev.tbertie.warehousesystem.service.SupplierService;

import java.time.LocalDateTime;

public class SupplierReport {
    private final SupplierService supplierService;
    
    public SupplierReport(SupplierService supplierService) {
        this.supplierService = supplierService;
    }
    
    public void generatePendingOrdersReport() {
    }
    
    public void generateOrdersInDateRangeReport(LocalDateTime startDate, LocalDateTime endDate) {
    }
    
    public void generateSupplierPerformanceReport() {
    }
    
    public void generateTotalSpendingBySupplierReport() {

    }
}