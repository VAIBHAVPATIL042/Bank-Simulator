package com.bank.util;

import java.sql.*;

public class QueryRunner {

    public static void runUpdate(String sql) {
        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            System.out.println("Updated rows: " + rows);
        } catch (SQLException e) {
            System.out.println("Update failed:");
            e.printStackTrace();
        }
    }

    public static void runSelect(String sql) {
        try (Connection conn = DBConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData md = rs.getMetaData();
            int cols = md.getColumnCount();

            // header
            for (int i = 1; i <= cols; i++) {
                System.out.print(md.getColumnLabel(i) + "\t");
            }
            System.out.println("\n-------------------------------------------------");

            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Select failed:");
            e.printStackTrace();
        }
    }
}
