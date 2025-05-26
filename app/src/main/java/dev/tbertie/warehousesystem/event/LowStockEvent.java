package dev.tbertie.warehousesystem.event;

import dev.tbertie.warehousesystem.model.Item;
import lombok.Getter;

@Getter
public class LowStockEvent implements Event {
    private final Item item;
    private final int currentStock;
    private final int threshold;
    
    public LowStockEvent(Item item, int currentStock, int threshold) {
        this.item = item;
        this.currentStock = currentStock;
        this.threshold = threshold;
    }
    
    @Override
    public String getType() {
        return "LOW_STOCK";
    }
}