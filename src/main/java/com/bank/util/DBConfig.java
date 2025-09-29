//package com.bank.util;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBConfig {
//    private static final String URL = "jdbc:mysql://localhost:3306/bankSimulation?serverTimezone=UTC";
//    private static final String USER = "root";
//    private static final String PASSWORD = "1234";
//
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }
//
//    public static void checkConnection() {
//    }
//}



package com.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

    // Change these values to your MySQL settings
    private static final String URL = "jdbc:mysql://localhost:3306/bankSimulation?serverTimezone=UTC&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    static {
        try {
            // explicit driver load - helps in some servlet containers
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // if this prints, driver jar is not on classpath
            System.err.println("MySQL JDBC Driver not found. Add it to your project's dependencies.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        // DriverManager will find the driver if jar is on classpath or Class.forName succeeded
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}




















//package com.bank.util;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBConfig {
//    private static final String URL = "jdbc:mysql://localhost:3306/bankSimulation?serverTimezone=UTC";
//    private static final String USER = "root";
//    private static final String PASSWORD = "1234";
//
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }
//
//    public static void checkConnection() {
//    }
//}

