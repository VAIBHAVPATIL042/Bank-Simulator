package com.bank.dao;

import com.bank.model.Customer;
import com.bank.util.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public void createCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (name, phone_number, email, address, customer_pin, aadhar_number, dob, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhoneNumber());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getCustomerPin());
            ps.setString(6, customer.getAadharNumber());

            if (customer.getDob() != null) {
                ps.setDate(7, Date.valueOf(customer.getDob()));
            } else {
                ps.setNull(7, Types.DATE);
            }

            ps.setString(8, customer.getStatus() != null ? customer.getStatus() : "Active");

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    customer.setCustomerId(keys.getInt(1));
                }
            }
        }
    }

    public Customer getCustomerById(int customerId) throws SQLException {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToCustomer(rs);
                } else {
                    return null;
                }
            }
        }
    }

    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customers.add(mapRowToCustomer(rs));
            }
        }
        return customers;
    }

    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET name=?, phone_number=?, email=?, address=?, customer_pin=?, aadhar_number=?, dob=?, status=? WHERE customer_id=?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhoneNumber());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getCustomerPin());
            ps.setString(6, customer.getAadharNumber());

            if (customer.getDob() != null) {
                ps.setDate(7, Date.valueOf(customer.getDob()));
            } else {
                ps.setNull(7, Types.DATE);
            }

            ps.setString(8, customer.getStatus() != null ? customer.getStatus() : "Active");
            ps.setInt(9, customer.getCustomerId());

            ps.executeUpdate();
        }
    }

    // Helper to map ResultSet row to Customer object
    private Customer mapRowToCustomer(ResultSet rs) throws SQLException {
        Customer c = new Customer();
        c.setCustomerId(rs.getInt("customer_id"));
        c.setName(rs.getString("name"));
        c.setPhoneNumber(rs.getString("phone_number"));
        c.setEmail(rs.getString("email"));
        c.setAddress(rs.getString("address"));
        c.setCustomerPin(rs.getString("customer_pin"));
        c.setAadharNumber(rs.getString("aadhar_number"));

        Date dobDate = rs.getDate("dob");
        if (dobDate != null) {
            c.setDob(dobDate.toLocalDate().toString()); // Convert LocalDate to String
        } else {
            c.setDob(null);
        }

        c.setStatus(rs.getString("status"));
        return c;
    }
}