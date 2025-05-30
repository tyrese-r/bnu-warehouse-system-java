package dev.tbertie.warehousesystem.repository;

import dev.tbertie.warehousesystem.model.Supplier;

import java.util.*;

public class SupplierRepository {

    private SupplierRepository() {}
    private static SupplierRepository instance;

    public static SupplierRepository getInstance() {
        if(instance == null) {
            instance = new SupplierRepository();
        }
        return instance;
    }

    private Map<UUID, Supplier> suppliers = new HashMap<>();

    public void addSupplier(Supplier supplier) {
        suppliers.put(supplier.getUuid(), supplier);
    }

    public void removeSupplier(Supplier supplier) {
        suppliers.remove(supplier.getUuid());
    }

    public Supplier getSupplierByUuid(UUID uuid) {
        return suppliers.get(uuid);
    }

    public Supplier getSupplierByUuid(String uuid) {
        return getSupplierByUuid(UUID.fromString(uuid));
    }

    public List<Supplier> getAllSuppliers() {
        return new ArrayList<>(suppliers.values());
    }
}