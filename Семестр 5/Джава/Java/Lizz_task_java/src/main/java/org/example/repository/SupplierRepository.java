package org.example.repository;

import org.example.model.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier> {
    Supplier findByEmail(String email);

    void deleteByEmail(String email);
}