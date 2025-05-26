package dev.tbertie.warehousesystem.event;

import dev.tbertie.warehousesystem.model.SupplierOrder;
import lombok.Getter;

@Getter
public class SupplierOrderEvent implements Event {
    private final SupplierOrder order;
    private final String action;
    
    public SupplierOrderEvent(SupplierOrder order, String action) {
        this.order = order;
        this.action = action;
    }
    
    @Override
    public String getType() {
        return "SUPPLIER_ORDER_" + action.toUpperCase();
    }
}