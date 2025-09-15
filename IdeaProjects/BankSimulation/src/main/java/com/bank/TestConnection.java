package com.bank;

import com.bank.util.DBConfig;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = DBConfig.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("JDBC connection established successfully to database: " + conn.getCatalog());
            } else {
                System.out.println("Failed to establish JDBC connection.");
            }
        } catch (SQLException e) {
            System.out.println("JDBC connection error!");
            e.printStackTrace();
        }
    }
}
