package org.example.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.SupplyOrder;
import org.example.service.SupplyOrderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/supplyOrder")
public class SupplyOrderServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SupplyOrderService supplyOrderService = ServletUtils.getServiceFromContext(getServletContext(), "supplyOrderService", SupplyOrderService.class);

        resp.setContentType("application/json");
        String idParam = req.getParameter("id");

        if (idParam != null) {
            try {
                long id = Long.parseLong(idParam);
                SupplyOrder supplyOrder = supplyOrderService.getSupplyOrderById(id);
                if (supplyOrder != null) {
                    String jsonResponse = gson.toJson(supplyOrder);
                    resp.getWriter().write(jsonResponse);
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"message\":\"Supply order not found\"}");
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"message\":\"Invalid supply order ID format\"}");
            }
        } else {
            List<SupplyOrder> supplyOrders = supplyOrderService.getAllSupplyOrders();
            String jsonResponse = gson.toJson(supplyOrders);
            resp.getWriter().write(jsonResponse);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SupplyOrderService supplyOrderService = ServletUtils.getServiceFromContext(getServletContext(), "supplyOrderService", SupplyOrderService.class);

        resp.setContentType("application/json");
        BufferedReader reader = req.getReader();
        SupplyOrder supplyOrder = gson.fromJson(reader, SupplyOrder.class);

        if (supplyOrder != null && supplyOrder.getSupplier() != null && !supplyOrder.getItems().isEmpty()) {
            supplyOrderService.createSupplyOrder(supplyOrder);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"message\":\"Supply order created successfully\"}");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\":\"Invalid supply order data\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SupplyOrderService supplyOrderService = ServletUtils.getServiceFromContext(getServletContext(), "supplyOrderService", SupplyOrderService.class);

        String idParam = req.getParameter("id");

        if (idParam != null) {
            try {
                long id = Long.parseLong(idParam);
                supplyOrderService.deleteSupplyOrder(id);
                resp.getWriter().write("{\"message\":\"Supply order deleted successfully\"}");
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"message\":\"Invalid supply order ID format\"}");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"message\":\"ID parameter is missing\"}");
        }
    }
}