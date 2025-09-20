package com.bank.dao;

import com.bank.util.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    private final TransactionDAO transactionDAO = new TransactionDAO();

    // create account (auto-generate account number if null)
    public void createAccount(int customerId, String type, String name, String accountNumber) {
        String accNo = (accountNumber == null || accountNumber.isBlank()) ? "AC" + System.currentTimeMillis() : accountNumber;
        String sql = "INSERT INTO account (customer_id, account_type, account_name, account_number) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ps.setString(2, type);
            ps.setString(3, name);
            ps.setString(4, accNo);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Account created: " + accNo : "Account creation failed");
        } catch (SQLException e) {
            System.out.println("Error creating account:");
            e.printStackTrace();
        }
    }

    public void viewAccounts() {
        String sql = "SELECT account_id, customer_id, account_number, account_name, account_type, balance, status FROM account";
        try (Connection conn = DBConfig.getConnection();
             var ps = conn.prepareStatement(sql);
             var rs = ps.executeQuery()) {

            System.out.println("=== Accounts ===");
            while (rs.next()) {
                System.out.printf("%d | cust:%d | %s | %s | %s | %.2f | %s%n",
                        rs.getInt("account_id"),
                        rs.getInt("customer_id"),
                        rs.getString("account_number"),
                        rs.getString("account_name"),
                        rs.getString("account_type"),
                        rs.getDouble("balance"),
                        rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("Error reading accounts:");
            e.printStackTrace();
        }
    }

    public void deleteAccount(int accountId) {
        String sql = "DELETE FROM account WHERE account_id = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Account deleted!" : "No such account");
        } catch (SQLException e) {
            System.out.println("Error deleting account:");
            e.printStackTrace();
        }
    }

    // helper to get account_id by account_number using provided connection
    private Integer getAccountIdByNumber(Connection conn, String accountNumber) throws SQLException {
        String sql = "SELECT account_id FROM account WHERE account_number = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accountNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("account_id");
                else return null;
            }
        }
    }

    // deposit (updates balance and records transaction)
    public void deposit(String accountNumber, double amount, String mode, String receiverInfo, String senderInfo) {
        String updateSql = "UPDATE account SET balance = balance + ? WHERE account_number = ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(updateSql)) {

            conn.setAutoCommit(false);
            ps.setDouble(1, amount);
            ps.setString(2, accountNumber);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                conn.rollback();
                System.out.println("Deposit failed: account not found");
                return;
            }

            Integer accId = getAccountIdByNumber(conn, accountNumber);
            if (accId != null) {
                transactionDAO.addTransactionWithConnection(conn, accId, amount, "credited", mode, receiverInfo, senderInfo);
            }
            conn.commit();
            System.out.println("Deposit successful");

        } catch (SQLException e) {
            System.out.println("Error during deposit:");
            e.printStackTrace();
        }
    }

    // withdraw (updates balance with check and records transaction)
    public void withdraw(String accountNumber, double amount, String mode, String receiverInfo, String senderInfo) {
        String updateSql = "UPDATE account SET balance = balance - ? WHERE account_number = ? AND balance >= ?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(updateSql)) {

            conn.setAutoCommit(false);
            ps.setDouble(1, amount);
            ps.setString(2, accountNumber);
            ps.setDouble(3, amount);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                conn.rollback();
                System.out.println("Withdraw failed: insufficient balance or account not found");
                return;
            }

            Integer accId = getAccountIdByNumber(conn, accountNumber);
            if (accId != null) {
                transactionDAO.addTransactionWithConnection(conn, accId, amount, "debited", mode, receiverInfo, senderInfo);
            }
            conn.commit();
            System.out.println("Withdraw successful");

        } catch (SQLException e) {
            System.out.println("Error during withdraw:");
            e.printStackTrace();
        }
    }

    // transfer between two accounts (atomic)
    public void transfer(String senderAccNo, String receiverAccNo, double amount, String mode) {
        try (Connection conn = DBConfig.getConnection()) {
            conn.setAutoCommit(false);

            // withdraw from sender
            String withdrawSql = "UPDATE account SET balance = balance - ? WHERE account_number = ? AND balance >= ?";
            try (PreparedStatement ps1 = conn.prepareStatement(withdrawSql)) {
                ps1.setDouble(1, amount);
                ps1.setString(2, senderAccNo);
                ps1.setDouble(3, amount);
                int w = ps1.executeUpdate();
                if (w == 0) {
                    conn.rollback();
                    System.out.println("Transfer failed: insufficient funds or sender not found");
                    return;
                }
            }

            // deposit to receiver
            String depositSql = "UPDATE account SET balance = balance + ? WHERE account_number = ?";
            try (PreparedStatement ps2 = conn.prepareStatement(depositSql)) {
                ps2.setDouble(1, amount);
                ps2.setString(2, receiverAccNo);
                int d = ps2.executeUpdate();
                if (d == 0) {
                    conn.rollback();
                    System.out.println("Transfer failed: receiver not found");
                    return;
                }
            }

            // fetch account ids
            Integer senderId = getAccountIdByNumber(conn, senderAccNo);
            Integer receiverId = getAccountIdByNumber(conn, receiverAccNo);

            // record transactions
            if (senderId != null) transactionDAO.addTransactionWithConnection(conn, senderId, amount, "debited", mode, receiverAccNo, senderAccNo);
            if (receiverId != null) transactionDAO.addTransactionWithConnection(conn, receiverId, amount, "credited", mode, receiverAccNo, senderAccNo);

            conn.commit();
            System.out.println("Transfer successful");

        } catch (SQLException e) {
            System.out.println("Error during transfer:");
            e.printStackTrace();
        }
    }
}
