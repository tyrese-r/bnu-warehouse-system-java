package dev.tbertie.warehousesystem.event;

public interface Subscriber {
    void onEvent(Event event);
}
