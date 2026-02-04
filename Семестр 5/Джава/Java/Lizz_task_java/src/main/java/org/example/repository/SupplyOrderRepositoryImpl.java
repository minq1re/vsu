package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.Supplier;
import org.example.model.SupplyOrder;
import org.example.model.SupplyOrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class SupplyOrderRepositoryImpl implements SupplyOrderRepository {

    private final Connection connection;

    private final SupplyOrderItemRepository supplyOrderItemRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public void save(SupplyOrder supplyOrder) {
        String sql = "INSERT INTO supply_order (supplier_id) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, supplyOrder.getSupplier().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long supplyOrderId = generatedKeys.getLong(1);
                for (SupplyOrderItem item : supplyOrder.getItems()) {
                    supplyOrderItemRepository.addSupplyOrderItem(item, supplyOrderId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save supply order", e);
        }
    }

    @Override
    public SupplyOrder findById(Long id) {
        String sql = "SELECT * FROM supply_order WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Supplier supplier = supplierRepository.findById(resultSet.getLong("supplier_id"));
                List<SupplyOrderItem> items = supplyOrderItemRepository.getSupplyOrderItemsByOrderId(id);
                return new SupplyOrder(id, supplier, items);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching supply order by ID " + id, e);
        }
        return null;
    }

    @Override
    public List<SupplyOrder> findAll() {
        List<SupplyOrder> orders = new ArrayList<>();
        String sql = "SELECT * FROM supply_order";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                long orderId = resultSet.getLong("id");
                Supplier supplier = supplierRepository.findById(resultSet.getLong("supplier_id"));
                List<SupplyOrderItem> items = supplyOrderItemRepository.getSupplyOrderItemsByOrderId(orderId);
                orders.add(new SupplyOrder(orderId, supplier, items));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all supply orders", e);
        }
        return orders;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM supply_order WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            supplyOrderItemRepository.deleteSupplyOrderItemsByOrderId(id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete supply order with ID " + id, e);
        }
    }

    @Override
    public List<SupplyOrder> findOrdersByIds(List<Long> supplyOrderIds) {
        if (supplyOrderIds == null || supplyOrderIds.isEmpty()) {
            return new ArrayList<>();
        }
        String sql = "SELECT * FROM supply_order WHERE id IN (" +
                String.join(", ", Collections.nCopies(supplyOrderIds.size(), "?")) + ")";
        List<SupplyOrder> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < supplyOrderIds.size(); i++) {
                preparedStatement.setLong(i + 1, supplyOrderIds.get(i));
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long orderId = resultSet.getLong("id");
                    long supplierId = resultSet.getLong("supplier_id");
                    Supplier supplier = supplierRepository.findById(supplierId);
                    List<SupplyOrderItem> items = supplyOrderItemRepository.getSupplyOrderItemsByOrderId(orderId);
                    orders.add(new SupplyOrder(orderId, supplier, items));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching supply orders by IDs: " + supplyOrderIds, e);
        }

        return orders;
    }
}