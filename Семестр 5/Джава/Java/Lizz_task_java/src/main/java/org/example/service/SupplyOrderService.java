package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.SupplyOrder;
import org.example.repository.SupplyOrderRepository;

import java.util.List;

@RequiredArgsConstructor
public class SupplyOrderService {
    private final SupplyOrderRepository supplyOrderRepository;

    public void createSupplyOrder(SupplyOrder supplyOrder) {
        supplyOrderRepository.save(supplyOrder);
    }

    public SupplyOrder getSupplyOrderById(Long id) {
        return supplyOrderRepository.findById(id);
    }

    public List<SupplyOrder> getAllSupplyOrders() {
        return supplyOrderRepository.findAll();
    }

    public void deleteSupplyOrder(Long id) {
        supplyOrderRepository.delete(id);
    }
}
