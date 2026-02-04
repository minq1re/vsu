package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Supplier;
import org.example.model.SupplyOrder;
import org.example.repository.SupplierRepository;
import org.example.repository.SupplyOrderRepository;

import java.util.List;

@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplyOrderRepository supplyOrderRepository;

    public void addSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public void deleteSupplierByEmail(String email) {
        supplierRepository.deleteByEmail(email);
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public List<SupplyOrder> getOrdersForSupplier(Supplier supplier) {
        return supplyOrderRepository.findOrdersByIds(supplier.getSupplyOrderIds());
    }
}
