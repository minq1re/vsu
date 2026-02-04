package org.example.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.db.DatabaseConnection;
import org.example.repository.*;
import org.example.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class AppContextListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(AppContextListener.class);
    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            logger.info("Initializing database connection...");
            connection = DatabaseConnection.getConnection();
            logger.info("Database connection initialized successfully.");

            ProductRepository productRepository = new ProductRepositoryImpl(connection);
            SupplierRepository supplierRepository = new SupplierRepositoryImpl(connection);
            SupplyOrderItemRepository supplyOrderItemRepository = new SupplyOrderItemRepositoryImpl(connection, productRepository);
            SupplyOrderRepository supplyOrderRepository = new SupplyOrderRepositoryImpl(connection, supplyOrderItemRepository, supplierRepository);

            logger.info("Repositories initialized successfully.");

            ProductService productService = new ProductService(productRepository);
            SupplierService supplierService = new SupplierService(supplierRepository, supplyOrderRepository);
            SupplyOrderService supplyOrderService = new SupplyOrderService(supplyOrderRepository);

            logger.info("Services initialized successfully.");

            sce.getServletContext().setAttribute("productService", productService);
            sce.getServletContext().setAttribute("supplierService", supplierService);
            sce.getServletContext().setAttribute("supplyOrderService", supplyOrderService);

            logger.info("All repositories and services initialized and stored in the context.");
        } catch (Exception e) {
            logger.error("Failed to initialize repositories and services", e);
            sce.getServletContext().setAttribute("initError", e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                logger.info("Database connection closed.");
            }
        } catch (SQLException e) {
            logger.error("Failed to close database connection", e);
        }
    }
}