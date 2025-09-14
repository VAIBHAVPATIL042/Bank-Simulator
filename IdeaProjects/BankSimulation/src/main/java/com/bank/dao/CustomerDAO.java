package com.bank.dao;

import com.bank.util.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    public void insertCustomer(String name, String phone, String email, String pin, String aadhar) {
        String sql = "INSERT INTO customer (name, phone_number, email, customer_pin, aadhar_number) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setString(4, pin);
            ps.setString(5, aadhar);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Customer added!" : "❌ Customer add failed");

        } catch (SQLException e) {
            System.out.println("Error inserting customer:");
            e.printStackTrace();
        }
    }

    public void viewCustomers() {
        String sql = "SELECT customer_id, name, phone_number, email, status FROM customer";
        try (Connection conn = DBConfig.getConnection();
             var ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("=== Customers ===");
            while (rs.next()) {
                System.out.printf("%d | %s | %s | %s | %s%n",
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("status"));
            }

        } catch (SQLException e) {
            System.out.println("Error reading customers:");
            e.printStackTrace();
        }
    }

    public void updateCustomer(int customerId, String name, String phone, String email, String status) {
        String sql = "UPDATE customer SET name = ?, phone_number = ?, email = ?, status = ? WHERE customer_id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setString(4, status);
            ps.setInt(5, customerId);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Customer updated!" : "❌ No such customer");

        } catch (SQLException e) {
            System.out.println("Error updating customer:");
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "✅ Customer deleted!" : "❌ No such customer");

        } catch (SQLException e) {
            System.out.println("Error deleting customer:");
            e.printStackTrace();
        }
    }
}
