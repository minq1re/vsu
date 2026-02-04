package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SupplierRepositoryImpl implements SupplierRepository {
    private final Connection connection;

    @Override
    public void save(Supplier supplier) {
        String sql = "INSERT INTO supplier (name, email) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getEmail());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                supplier.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save supplier", e);
        }
    }

    @Override
    public void update(Supplier supplier) {
        String sql = "UPDATE supplier SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getEmail());
            preparedStatement.setLong(3, supplier.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update supplier", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM supplier WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete supplier", e);
        }
    }

    @Override
    public Supplier findById(Long id) {
        String sql = "SELECT * FROM supplier WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Supplier supplier = new Supplier(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
                supplier.setSupplyOrderIds(getSupplyOrderIdsBySupplierId(supplier.getId()));
                return supplier;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find supplier by ID", e);
        }
        return null;
    }

    @Override
    public List<Supplier> findAll() {
        String sql = "SELECT * FROM supplier";
        List<Supplier> suppliers = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Supplier supplier = new Supplier(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
                supplier.setSupplyOrderIds(getSupplyOrderIdsBySupplierId(supplier.getId()));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find all suppliers", e);
        }
        return suppliers;
    }

    @Override
    public Supplier findByEmail(String email) {
        String sql = "SELECT * FROM supplier WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Supplier supplier = new Supplier(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
                supplier.setSupplyOrderIds(getSupplyOrderIdsBySupplierId(supplier.getId()));
                return supplier;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find supplier by email", e);
        }
        return null;
    }

    @Override
    public void deleteByEmail(String email) {
        String sql = "DELETE FROM supplier WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete supplier by email", e);
        }
    }

    private List<Long> getSupplyOrderIdsBySupplierId(Long supplierId) {

        String sql = "SELECT id FROM supply_order WHERE supplier_id = ?";
        List<Long> orderIds = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, supplierId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderIds.add(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get supply orders for supplier ID " + supplierId, e);
        }
        return orderIds;
    }
}
