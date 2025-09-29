package com.bank.dao;

import com.bank.model.Customer;
import com.bank.util.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    private static final String INSERT_SQL =
            "INSERT INTO customer (name, phone_number, email, address, customer_pin, aadhar_number, dob, status) VALUES (?, ?, ?, ?, ?, ?, ?, 'Active')";
    private static final String SELECT_BY_ID = "SELECT * FROM customer WHERE customer_id = ?";
    private static final String SELECT_ALL = "SELECT * FROM customer";
    private static final String UPDATE_SQL = "UPDATE customer SET name=?, phone_number=?, email=?, address=? WHERE customer_id=?";
    private static final String DELETE_SQL = "DELETE FROM customer WHERE customer_id=?";

    @Override
    public boolean addCustomer(Customer customer) throws Exception {
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            if (conn == null) throw new Exception("DB connection is null");

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhoneNumber());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getCustomerPin());
            ps.setString(6, customer.getAadharNumber());
            ps.setDate(7, customer.getDob() == null ? null : Date.valueOf(customer.getDob()));

            int affected = ps.executeUpdate();
            if (affected == 0) return false;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) customer.setCustomerId(keys.getInt(1));
            }
            return true;
        }
    }

    @Override
    public Customer findById(long id) throws Exception {
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {
            if (conn == null) throw new Exception("DB connection is null");
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
            return null;
        }
    }

    @Override
    public List<Customer> findAll() throws Exception {
        List<Customer> list = new ArrayList<>();
        try (Connection conn = DBConfig.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL)) {
            if (conn == null) throw new Exception("DB connection is null");
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    @Override
    public boolean updateCustomer(long id, Customer customer) throws Exception {
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            if (conn == null) throw new Exception("DB connection is null");
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhoneNumber());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getAddress());
            ps.setLong(5, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteCustomer(long id) throws Exception {
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            if (conn == null) throw new Exception("DB connection is null");
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Customer mapRow(ResultSet rs) throws SQLException {
        Customer c = new Customer();
        c.setCustomerId(rs.getInt("customer_id"));
        c.setName(rs.getString("name"));
        c.setPhoneNumber(rs.getString("phone_number"));
        c.setEmail(rs.getString("email"));
        c.setAddress(rs.getString("address"));
        c.setCustomerPin(rs.getString("customer_pin"));
        c.setAadharNumber(rs.getString("aadhar_number"));
        Date dob = rs.getDate("dob");
        c.setDob(dob == null ? null : dob.toString());
        c.setStatus(rs.getString("status"));
        return c;
    }
}
