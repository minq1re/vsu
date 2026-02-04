package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.example.service.SupplierService;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    private Long id;
    private String name;
    private String email;

    private List<Long> supplyOrderIds;

    public Supplier(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.supplyOrderIds = new ArrayList<>();
    }
}