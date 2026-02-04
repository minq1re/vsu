package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.SupplyOrderItem;
import org.example.repository.SupplyOrderItemRepository;

import java.util.List;

@RequiredArgsConstructor
public class SupplyOrderItemService {
    private final SupplyOrderItemRepository supplyOrderItemRepository;

    public void addSupplyOrderItem(SupplyOrderItem supplyOrderItem, long orderId) {
        supplyOrderItemRepository.addSupplyOrderItem(supplyOrderItem, orderId);
    }

    public List<SupplyOrderItem> getSupplyOrderItemsByOrderId(long orderId) {
        return supplyOrderItemRepository.getSupplyOrderItemsByOrderId(orderId);
    }

    public void deleteSupplyOrderItemsByOrderId(long orderId) {
        supplyOrderItemRepository.deleteSupplyOrderItemsByOrderId(orderId);
    }
}
