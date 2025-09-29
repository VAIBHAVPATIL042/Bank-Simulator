package com.bank.dao;

import com.bank.model.Account;
import com.bank.util.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {

    @Override
    public boolean createAccount(Account account) {
        String sql = """
            INSERT INTO account
            (customer_id, account_number, aadhar_number, ifsc_code,
             phone_number_linked, balance, account_name, account_type, status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'Active')
            """;

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, account.getCustomerId());       // int
            ps.setString(2, account.getAccountNumber());
            ps.setString(3, account.getAadharNumber());
            ps.setString(4, account.getIfscCode());
            ps.setString(5, account.getPhoneNumber());
            ps.setDouble(6, account.getAmount());
            ps.setString(7, account.getNameOnAccount());
            ps.setString(8, account.getBankName());      // maps to account_type

            int affected = ps.executeUpdate();
            if (affected == 0) return false;

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    account.setAccountId(keys.getInt(1));
                }
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Account> findByCustomerId(int customerId) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account WHERE customer_id=?";
        try (Connection con = DBConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setCustomerId(rs.getInt("customer_id"));
                acc.setAccountNumber(rs.getString("account_number"));
                acc.setAadharNumber(rs.getString("aadhar_number"));
                acc.setIfscCode(rs.getString("ifsc_code"));
                acc.setPhoneNumber(rs.getString("phone_number_linked"));
                acc.setAmount(rs.getDouble("balance"));
                acc.setBankName(rs.getString("account_type"));
                acc.setNameOnAccount(rs.getString("account_name"));
                acc.setStatus(rs.getString("status"));
                accounts.add(acc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public boolean updateAccount(String accountNumber, Account account) {
        String sql = "UPDATE account SET phone_number_linked=?, balance=?, account_name=?, modified_at=NOW() WHERE account_number=?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, account.getPhoneNumber());
            ps.setDouble(2, account.getAmount());
            ps.setString(3, account.getNameOnAccount());
            ps.setString(4, accountNumber);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        String sql = "SELECT * FROM account WHERE account_number=?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account a = new Account();
                a.setAccountId(rs.getInt("account_id"));
                a.setCustomerId(rs.getInt("customer_id"));
                a.setAccountNumber(rs.getString("account_number"));
                a.setAadharNumber(rs.getString("aadhar_number"));
                a.setIfscCode(rs.getString("ifsc_code"));
                a.setPhoneNumber(rs.getString("phone_number_linked"));
                a.setAmount(rs.getDouble("balance"));
                a.setBankName(rs.getString("account_type"));
                a.setNameOnAccount(rs.getString("account_name"));
                a.setStatus(rs.getString("status"));
                return a;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public boolean deleteByAccountNumber(String accountNumber) {
        String sql = "DELETE FROM account WHERE account_number=?";
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, accountNumber);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Account> findAll() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM account";
        try (Connection conn = DBConfig.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Account a = new Account();
                a.setAccountId(rs.getInt("account_id"));
                a.setCustomerId(rs.getInt("customer_id"));
                a.setAccountNumber(rs.getString("account_number"));
                a.setAadharNumber(rs.getString("aadhar_number"));
                a.setPhoneNumber(rs.getString("phone_number_linked"));
                a.setAmount(rs.getDouble("balance"));
                a.setBankName(rs.getString("account_type"));
                a.setNameOnAccount(rs.getString("account_name"));
                a.setStatus(rs.getString("status"));
                list.add(a);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
