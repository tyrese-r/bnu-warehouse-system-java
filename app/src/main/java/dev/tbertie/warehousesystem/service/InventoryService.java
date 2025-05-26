package dev.tbertie.warehousesystem.service;

import dev.tbertie.warehousesystem.event.Event;
import dev.tbertie.warehousesystem.event.Subscriber;
import dev.tbertie.warehousesystem.model.Inventory;
import dev.tbertie.warehousesystem.model.Item;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class InventoryService implements Subscriber {
    @Getter
    private final Inventory inventory;
    private final List<Subscriber> subscribers;
    
    public InventoryService(int lowStockThreshold) {
        this.inventory = new Inventory(lowStockThreshold);
        this.subscribers = new ArrayList<>();
    }
    
    public void addItem(Item item, int quantity) {

    }
    
    public boolean removeItem(Item item, int quantity) {
        return false;
    }
    
    public List<Item> getLowStockItems() {
        return null;
    }
    
    public void monitorLowStock() {

    }
    
    public void registerSubscriber(Subscriber subscriber) {

    }
    
    public void notifySubscribers(Event event) {

    }
    
    @Override
    public void onEvent(Event event) {

    }
}