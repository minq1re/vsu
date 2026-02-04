package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplyOrder {
    private Long id;
    private Supplier supplier;
    private List<SupplyOrderItem> items;
}