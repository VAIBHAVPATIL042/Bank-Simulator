package com.bank.dao;

import com.bank.util.DBConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {

    // Use this when you already have a Connection (participating in a transaction)
    public boolean addTransactionWithConnection(Connection conn, int accountId, double amount, String type, String mode, String receiver, String sender) throws SQLException {
        String sql = "INSERT INTO bank_transaction (account_id, transaction_amount, transaction_type, transaction_mode, receiver_details, sender_details) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ps.setDouble(2, amount);
            ps.setString(3, type);
            ps.setString(4, mode);
            ps.setString(5, receiver);
            ps.setString(6, sender);
            return ps.executeUpdate() > 0;
        }
    }

    // Convenience method when you don't have a connection
    public void addTransaction(int accountId, double amount, String type, String mode, String receiver, String sender) {
        try (Connection conn = DBConfig.getConnection()) {
            addTransactionWithConnection(conn, accountId, amount, type, mode, receiver, sender);
            System.out.println("Transaction recorded");
        } catch (SQLException e) {
            System.out.println("Error recording transaction");
            e.printStackTrace();
        }
    }

    public void viewTransactions() {
        String sql = "SELECT transaction_id, account_id, transaction_amount, transaction_type, transaction_mode, transaction_time FROM bank_transaction ORDER BY transaction_time DESC";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("=== Transactions ===");
            while (rs.next()) {
                System.out.printf("%d | acc:%d | %.2f | %s | %s | %s%n",
                        rs.getInt("transaction_id"),
                        rs.getInt("account_id"),
                        rs.getDouble("transaction_amount"),
                        rs.getString("transaction_type"),
                        rs.getString("transaction_mode"),
                        rs.getTimestamp("transaction_time").toString());
            }

        } catch (SQLException e) {
            System.out.println("Error reading transactions");
            e.printStackTrace();
        }
    }
}
