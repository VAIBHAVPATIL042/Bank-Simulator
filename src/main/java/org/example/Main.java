package org.example;

import com.bank.dao.AccountDAO;
import com.bank.dao.CustomerDAO;
import com.bank.dao.TransactionDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CustomerDAO customerDAO = new CustomerDAO();
        AccountDAO accountDAO = new AccountDAO();
        TransactionDAO txDAO = new TransactionDAO();

        while (true) {
            System.out.println("\n1 Insert Customer  2 View Customers  3 Create Account  4 View Accounts  5 Deposit  6 Withdraw  7 Transfer  8 View Transactions  0 Exit");
            int ch = Integer.parseInt(sc.nextLine().trim());
            switch (ch) {
                case 1 -> {
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Phone: "); String phone = sc.nextLine();
                    System.out.print("Email: "); String email = sc.nextLine();
                    System.out.print("PIN: "); String pin = sc.nextLine();
                    System.out.print("Aadhar: "); String aadhar = sc.nextLine();
                    customerDAO.insertCustomer(name, phone, email, pin, aadhar);
                }
                case 2 -> customerDAO.viewCustomers();
                case 3 -> {
                    System.out.print("CustomerId: "); int cid = Integer.parseInt(sc.nextLine());
                    System.out.print("Type: "); String type = sc.nextLine();
                    System.out.print("Name: "); String aname = sc.nextLine();
                    System.out.print("AccountNumber (or empty): "); String anum = sc.nextLine();
                    accountDAO.createAccount(cid, type, aname, anum);
                }
                case 4 -> accountDAO.viewAccounts();
                case 5 -> {
                    System.out.print("AccountNumber: "); String acc = sc.nextLine();
                    System.out.print("Amount: "); double amt = Double.parseDouble(sc.nextLine());
                    accountDAO.deposit(acc, amt, "upi", "receiverInfo", "senderInfo");
                }
                case 6 -> {
                    System.out.print("AccountNumber: "); String acc = sc.nextLine();
                    System.out.print("Amount: "); double amt = Double.parseDouble(sc.nextLine());
                    accountDAO.withdraw(acc, amt, "upi", "receiverInfo", "senderInfo");
                }
                case 7 -> {
                    System.out.print("SenderAcc: "); String s = sc.nextLine();
                    System.out.print("ReceiverAcc: "); String r = sc.nextLine();
                    System.out.print("Amount: "); double amt = Double.parseDouble(sc.nextLine());
                    accountDAO.transfer(s, r, amt, "upi");
                }
                case 8 -> txDAO.viewTransactions();
                case 0 -> { System.out.println("Bye"); return; }
                default -> System.out.println("Invalid");
            }
        }
    }
}




















//package org.example;
//
//import com.bank.util.DBConfig;
//import java.sql.*;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("\n=== Bank Simulation Menu ===");
//            System.out.println("1. Insert Customer");
//            System.out.println("2. View Customers");
//            System.out.println("3. Delete Customer");
//            System.out.println("0. Exit");
//            System.out.print("Enter choice: ");
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // consume newline
//
//            switch (choice) {
//                case 1 -> insertCustomer(scanner);
//                case 2 -> viewCustomers();
//                case 3 -> deleteCustomer(scanner);
//                case 0 -> {
//                    System.out.println("Exiting...");
//                    return;
//                }
//                default -> System.out.println("Invalid choice!");
//            }
//        }
//    }
//
//    private static void insertCustomer(Scanner scanner) {
//        try (Connection conn = DBConfig.getConnection()) {
//            System.out.print("Enter name: ");
//            String name = scanner.nextLine();
//
//            System.out.print("Enter phone number: ");
//            String phone = scanner.nextLine();
//
//            System.out.print("Enter email: ");
//            String email = scanner.nextLine();
//
//            System.out.print("Enter PIN: ");
//            String pin = scanner.nextLine();
//
//            System.out.print("Enter Aadhar number: ");
//            String aadhar = scanner.nextLine();
//
//            String sql = "INSERT INTO customer (name, phone_number, email, customer_pin, aadhar_number) VALUES (?, ?, ?, ?, ?)";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, name);
//            ps.setString(2, phone);
//            ps.setString(3, email);
//            ps.setString(4, pin);
//            ps.setString(5, aadhar);
//
//            int rows = ps.executeUpdate();
//            System.out.println(rows > 0 ? "✅ Customer inserted successfully!" : "❌ Insert failed!");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void viewCustomers() {
//        try (Connection conn = DBConfig.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM customer")) {
//
//            System.out.println("\n=== Customers ===");
//            while (rs.next()) {
//                System.out.println(
//                        rs.getInt("customer_id") + " | " +
//                                rs.getString("name") + " | " +
//                                rs.getString("phone_number") + " | " +
//                                rs.getString("email")
//                );
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void deleteCustomer(Scanner scanner) {
//        try (Connection conn = DBConfig.getConnection()) {
//            System.out.print("Enter customer_id to delete: ");
//            int id = scanner.nextInt();
//
//            String sql = "DELETE FROM customer WHERE customer_id = ?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, id);
//
//            int rows = ps.executeUpdate();
//            System.out.println(rows > 0 ? "✅ Customer deleted!" : "❌ No such customer!");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
