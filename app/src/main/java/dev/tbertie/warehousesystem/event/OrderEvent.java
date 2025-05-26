package dev.tbertie.warehousesystem.event;

import dev.tbertie.warehousesystem.model.CustomerOrder;
import lombok.Getter;

@Getter
public class OrderEvent implements Event {
    private final CustomerOrder order;
    private final String action;
    
    public OrderEvent(CustomerOrder order, String action) {
        this.order = order;
        this.action = action;
    }
    
    @Override
    public String getType() {
        return "ORDER_" + action.toUpperCase();
    }
}