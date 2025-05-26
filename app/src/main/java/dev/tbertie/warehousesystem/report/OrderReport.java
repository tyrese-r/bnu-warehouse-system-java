package dev.tbertie.warehousesystem.report;

import dev.tbertie.warehousesystem.model.CustomerOrder;
import dev.tbertie.warehousesystem.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;

public class OrderReport {
    private final OrderService orderService;
    
    public OrderReport(OrderService orderService) {
        this.orderService = orderService;
    }
    
    public void generateOrderStatusReport() {

    }
    
    public void generateOrdersInDateRangeReport(LocalDateTime startDate, LocalDateTime endDate) {

    }
    
    public void generateCustomerOrderHistoryReport(String customerName) {

    }
    
    public void generatePopularItemsReport() {

    }
}