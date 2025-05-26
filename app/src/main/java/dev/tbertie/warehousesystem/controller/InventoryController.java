package dev.tbertie.warehousesystem.controller;

import dev.tbertie.warehousesystem.model.Item;
import dev.tbertie.warehousesystem.service.InventoryService;

public class InventoryController {
    private final InventoryService inventoryService;
    
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    
    public void addItemToInventory(Item item, int quantity) {

    }
    
    public boolean removeItemFromInventory(Item item, int quantity) {
        return false;
    }
    
    public void displayAllItems() {

    }
    
    public void displayLowStockItems() {

    }
}