package org.example.repository;

import org.example.model.SupplyOrderItem;

import java.util.List;

public interface SupplyOrderItemRepository extends CrudRepository<SupplyOrderItem> {
    void addSupplyOrderItem(SupplyOrderItem supplyOrderItem, long supplyOrderId);

    List<SupplyOrderItem> getSupplyOrderItemsByOrderId(long supplyOrderId);

    void deleteSupplyOrderItemsByOrderId(long supplyOrderId);
}