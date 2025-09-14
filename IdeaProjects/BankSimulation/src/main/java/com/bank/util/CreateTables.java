package com.bank.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {
    public static void main(String[] args) {
        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS customer (
                    customer_id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    phone_number VARCHAR(15) UNIQUE,
                    email VARCHAR(100) UNIQUE,
                    address TEXT,
                    customer_pin VARCHAR(10) NOT NULL,
                    aadhar_number VARCHAR(12) UNIQUE NOT NULL,
                    dob DATE,
                    status VARCHAR(20) DEFAULT 'Inactive'
                )
            """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS account (
                    account_id INT AUTO_INCREMENT PRIMARY KEY,
                    customer_id INT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                    balance DECIMAL(10,2) DEFAULT 50.00,
                    account_type VARCHAR(50),
                    account_name VARCHAR(100),
                    account_number VARCHAR(20) UNIQUE NOT NULL,
                    phone_number_linked VARCHAR(15),
                    status VARCHAR(20) DEFAULT 'Active',
                    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
                )
            """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS bank_transaction (
                    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
                    account_id INT,
                    transaction_amount DECIMAL(10,2) NOT NULL,
                    transaction_type ENUM('debited', 'credited') NOT NULL,
                    transaction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    transaction_mode ENUM('debit_card', 'upi', 'credit_card') NOT NULL,
                    receiver_details VARCHAR(100),
                    sender_details VARCHAR(100),
                    FOREIGN KEY (account_id) REFERENCES account(account_id)
                )
            """);

            System.out.println("Database and tables created successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to create tables!");
            e.printStackTrace();
        }
    }
}
