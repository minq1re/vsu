package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.Product;
import org.example.model.SupplyOrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SupplyOrderItemRepositoryImpl implements SupplyOrderItemRepository {
    private final Connection connection;
    private final ProductRepository productRepository;

    @Override
    public void addSupplyOrderItem(SupplyOrderItem supplyOrderItem, long supplyOrderId) {
        String sql = "INSERT INTO supply_order_item (supply_order_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, supplyOrderId);
            preparedStatement.setLong(2, supplyOrderItem.getProduct().getId());
            preparedStatement.setInt(3, supplyOrderItem.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add supply order item", e);
        }
    }

    @Override
    public List<SupplyOrderItem> getSupplyOrderItemsByOrderId(long supplyOrderId) {
        String sql = "SELECT * FROM supply_order_item WHERE supply_order_id = ?";
        List<SupplyOrderItem> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, supplyOrderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long productId = resultSet.getLong("product_id");
                Product product = productRepository.findById(productId);
                int quantity = resultSet.getInt("quantity");
                items.add(new SupplyOrderItem(product, quantity));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error when retrieving supply order items for order_id = " + supplyOrderId, e);
        }
        return items;
    }

    @Override
    public void deleteSupplyOrderItemsByOrderId(long supplyOrderId) {
        String sql = "DELETE FROM supply_order_item WHERE supply_order_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, supplyOrderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error when deleting supply order items for order_id = " + supplyOrderId, e);
        }
    }
}