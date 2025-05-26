package dev.tbertie.warehousesystem.service;

import dev.tbertie.warehousesystem.event.Event;
import dev.tbertie.warehousesystem.event.Subscriber;
import dev.tbertie.warehousesystem.model.Customer;
import dev.tbertie.warehousesystem.model.CustomerOrder;
import dev.tbertie.warehousesystem.model.Item;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements Subscriber {
    private final List<CustomerOrder> orders;
    private final InventoryService inventoryService;
    private final List<Subscriber> subscribers;
    
    public OrderService(InventoryService inventoryService) {
        this.orders = new ArrayList<>();
        this.inventoryService = inventoryService;
        this.subscribers = new ArrayList<>();
        inventoryService.registerSubscriber(this);
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
    
    public List<CustomerOrder> getOrdersByStatus(String status) {

        return null;
    }
    
    public void registerSubscriber(Subscriber subscriber) {

    }
    
    public void notifySubscribers(Event event) {

    }
    
    @Override
    public void onEvent(Event event) {
        // Method to handle events
    }
}