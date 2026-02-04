package org.example.servlet;

import com.google.gson.Gson;
import org.example.model.Supplier;
import org.example.service.SupplierService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/supplier")
public class SupplierServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SupplierService supplierService = ServletUtils.getServiceFromContext(
                getServletContext(), "supplierService", SupplierService.class);

        String idParam = req.getParameter("id");
        try {
            if (idParam != null) {
                Long id = Long.parseLong(idParam);
                Supplier supplier = supplierService.getSupplierById(id);
                if (supplier != null) {
                    resp.getWriter().write(gson.toJson(supplier));
                } else {
                    sendErrorResponse(resp, HttpServletResponse.SC_NOT_FOUND, "Supplier not found");
                }
            } else {
                resp.setContentType("application/json");
                resp.getWriter().write(gson.toJson(supplierService.getAllSuppliers()));
            }
        } catch (RuntimeException e) {
            sendErrorResponse(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching supplier data: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SupplierService supplierService = ServletUtils.getServiceFromContext(getServletContext(), "supplierService", SupplierService.class);

        try (BufferedReader reader = req.getReader()) {
            Supplier supplier = gson.fromJson(reader, Supplier.class);
            if (isValidSupplier(supplier)) {
                supplierService.addSupplier(supplier);
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"message\":\"Supplier added successfully\"}");
            } else {
                sendErrorResponse(resp, HttpServletResponse.SC_BAD_REQUEST, "Invalid supplier data");
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SupplierService supplierService = ServletUtils.getServiceFromContext(getServletContext(), "supplierService", SupplierService.class);

        String email = req.getParameter("email");
        if (email != null && !email.trim().isEmpty()) {
            try {
                supplierService.deleteSupplierByEmail(email);
                resp.getWriter().write("{\"message\":\"Supplier deleted successfully\"}");
            } catch (RuntimeException e) {
                sendErrorResponse(resp, HttpServletResponse.SC_NOT_FOUND, e.getMessage());
            }
        } else {
            sendErrorResponse(resp, HttpServletResponse.SC_BAD_REQUEST, "Invalid email");
        }
    }

    private boolean isValidSupplier(Supplier supplier) {
        return supplier != null && supplier.getName() != null && !supplier.getName().trim().isEmpty()
                && supplier.getEmail() != null && !supplier.getEmail().trim().isEmpty();
    }

    private void sendErrorResponse(HttpServletResponse resp, int statusCode, String message) throws IOException {
        resp.setStatus(statusCode);
        resp.setContentType("application/json");
        resp.getWriter().write("{\"message\":\"" + message + "\"}");
    }
}