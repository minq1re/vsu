package org.example.repository;

import org.example.model.SupplyOrder;

import java.util.List;

public interface SupplyOrderRepository extends CrudRepository<SupplyOrder> {
    List<SupplyOrder> findOrdersByIds(List<Long> supplyOrderIds);
}