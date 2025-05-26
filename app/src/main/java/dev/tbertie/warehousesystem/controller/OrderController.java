package dev.tbertie.warehousesystem.controller;

import dev.tbertie.warehousesystem.model.Customer;
import dev.tbertie.warehousesystem.model.CustomerOrder;
import dev.tbertie.warehousesystem.model.Item;
import dev.tbertie.warehousesystem.service.OrderService;

public class OrderController {
    private final OrderService orderService;
    
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    public CustomerOrder createOrder(Customer customer) {
        return null;
    }
    
    public void addItemToOrder(CustomerOrder order, Item item, int quantity) {

    }
    
    public boolean processOrder(CustomerOrder order) {
        return false;
    }
    
    public boolean cancelOrder(CustomerOrder order) {
        return false;
    }
    
    public void displayAllOrders() {

    }
    
    public void displayOrdersByStatus(String status) {

    }
}