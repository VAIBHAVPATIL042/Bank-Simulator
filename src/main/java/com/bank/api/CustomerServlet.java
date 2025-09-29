package com.bank.api;

import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = new Customer();
        customer.setName(req.getParameter("name"));
        customer.setPhoneNumber(req.getParameter("phoneNumber"));
        customer.setEmail(req.getParameter("email"));
        customer.setAddress(req.getParameter("address"));
        customer.setCustomerPin(req.getParameter("customerPin"));
        customer.setAadharNumber(req.getParameter("aadharNumber"));
        customer.setDob(req.getParameter("dob"));

        try {
            customerDAO.createCustomer(customer);
            resp.getWriter().write("Customer created successfully");
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Customer> customers = customerDAO.getAllCustomers();
            PrintWriter out = resp.getWriter();
            for (Customer c : customers) {
                out.println(c.getCustomerId() + " - " + c.getName() + " - " + c.getEmail());
            }
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
