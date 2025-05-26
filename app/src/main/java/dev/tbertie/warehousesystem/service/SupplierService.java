package dev.tbertie.warehousesystem.service;

import dev.tbertie.warehousesystem.event.Event;
import dev.tbertie.warehousesystem.event.Subscriber;
import dev.tbertie.warehousesystem.model.Supplier;
import dev.tbertie.warehousesystem.model.SupplierOrder;
import dev.tbertie.warehousesystem.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplierService implements Subscriber {
    private final Map<String, Supplier> suppliers;
    private final List<SupplierOrder> orders;
    private final InventoryService inventoryService;
    private final List<Subscriber> subscribers;
    
    public SupplierService(InventoryService inventoryService) {
        this.suppliers = new HashMap<>();
        this.orders = new ArrayList<>();
        this.inventoryService = inventoryService;
        this.subscribers = new ArrayList<>();
        inventoryService.registerSubscriber(this);
    }
    
    public void addSupplier(Supplier supplier) {

    }
    
    public Supplier getSupplier(String name) {

        return null;
    }
    
    public List<Supplier> getAllSuppliers() {

        return null;
    }
    
    public SupplierOrder createOrder(Supplier supplier) {

        return null;
    }
    
    public void addItemToOrder(SupplierOrder order, Item item, int quantity) {

    }
    
    public boolean submitOrder(SupplierOrder order) {

        return false;
    }
    
    public boolean receiveOrder(SupplierOrder order) {

        return false;
    }
    
    public void registerSubscriber(Subscriber subscriber) {

    }
    
    public void notifySubscribers(Event event) {

    }
    
    @Override
    public void onEvent(Event event) {

    }
}