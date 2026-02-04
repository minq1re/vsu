package org.example.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Product;
import org.example.service.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = ServletUtils.getServiceFromContext(getServletContext(), "productService", ProductService.class);

        resp.setContentType("application/json");
        String idParam = req.getParameter("id");

        if (idParam != null) {
            long id = Long.parseLong(idParam);
            Product product = productService.getProductById(id);
            if (product != null) {
                String jsonResponse = gson.toJson(product);
                resp.getWriter().write(jsonResponse);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"message\":\"Product not found\"}");
            }
        } else {
            List<Product> products = productService.getAllProducts();
            String jsonResponse = gson.toJson(products);
            resp.getWriter().write(jsonResponse);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = ServletUtils.getServiceFromContext(getServletContext(), "productService", ProductService.class);

        resp.setContentType("application/json");
        BufferedReader reader = req.getReader();
        Product product = gson.fromJson(reader, Product.class);

        if (product != null && product.getName() != null && !product.getName().trim().isEmpty() && product.getUnitPrice() != null && product.getUnitPrice() > 0) {
            productService.addProduct(product);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"message\":\"Product added successfully\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\":\"Invalid product data\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = ServletUtils.getServiceFromContext(getServletContext(), "productService", ProductService.class);

        resp.setContentType("application/json");
        BufferedReader reader = req.getReader();
        Product product = gson.fromJson(reader, Product.class);

        if (product != null && product.getId() != null && product.getId() > 0 && product.getName() != null && !product.getName().trim().isEmpty() && product.getUnitPrice() != null && product.getUnitPrice() > 0) {
            productService.updateProduct(product);
            resp.getWriter().write("{\"message\":\"Product updated successfully\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\":\"Invalid product data\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = ServletUtils.getServiceFromContext(getServletContext(), "productService", ProductService.class);

        String idParam = req.getParameter("id");

        if (idParam != null) {
            long id = Long.parseLong(idParam);
            productService.deleteProduct(id);
            resp.getWriter().write("{\"message\":\"Product deleted successfully\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\":\"Invalid ID\"}");
        }
    }
}