package lk.ijse.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.db.DbConnection;
import lk.ijse.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT CustomerID FROM customers";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery()) {

            List<String> idList = new ArrayList<>();
            while (resultSet.next()) {
                idList.add(resultSet.getString("CustomerID"));
            }
            return idList;
        }
    }

    public static Customer searchById(String id) throws SQLException {
        String sql = "SELECT * FROM customers WHERE CustomerID = ?";
        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, id);
            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    String customerID = resultSet.getString("CustomerID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String nationality = resultSet.getString("Nationality");
                    String email = resultSet.getString("Email");
                    String phone = resultSet.getString("Phone");
                    String address = resultSet.getString("Address");
                    int totalReservations = resultSet.getInt("TotalReservations");
                    Date registrationDate = resultSet.getDate("RegistrationDate");

                    return new Customer(customerID, firstName, lastName, nationality, email, phone, address, totalReservations, registrationDate);
                }
            }
        }
        return null;
    }

    public static void save(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (CustomerID, FirstName, LastName, Nationality, Email, Phone, Address, TotalReservations, RegistrationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, customer.getCustomerID());
            pstm.setString(2, customer.getFirstName());
            pstm.setString(3, customer.getLastName());
            pstm.setString(4, customer.getNationality());
            pstm.setString(5, customer.getEmail());
            pstm.setString(6, customer.getPhone());
            pstm.setString(7, customer.getAddress());
            pstm.setInt(8, customer.getTotalReservations());
            pstm.setDate(9, customer.getRegistrationDate());
            pstm.executeUpdate();
        }
    }
    public static boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET FirstName = ?, LastName = ?, Nationality = ?, Email = ?, Phone = ?, Address = ?, TotalReservations = ?, RegistrationDate = ? WHERE CustomerID = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, customer.getFirstName());
            pstm.setString(2, customer.getLastName());
            pstm.setString(3, customer.getNationality());
            pstm.setString(4, customer.getEmail());
            pstm.setString(5, customer.getPhone());
            pstm.setString(6, customer.getAddress());
            pstm.setInt(7, customer.getTotalReservations());
            pstm.setDate(8, customer.getRegistrationDate());
            pstm.setString(9, customer.getCustomerID());

            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        }
    }
    public static String getNextCustomerId() throws SQLException {
        String sql = "SELECT CustomerID FROM customers ORDER BY CustomerID DESC LIMIT 1";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String lastId = rs.getString("CustomerID");
                int num = Integer.parseInt(lastId.replace("CUST", "")) + 1;
                return String.format("CUST%03d", num);
            } else {
                return "CUST001";  // Return the first ID if no existing records
            }
        }
    }
    public static Customer searchByPhone(String phone) throws SQLException {
        Connection conn = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customers WHERE phone = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getString("CustomerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Nationality"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getInt("TotalReservations"),
                        rs.getDate("RegistrationDate")
                );
            }
        }
        return null;
    }
    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        String query = "SELECT * FROM customers";
        try (Connection conn = DbConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getString("CustomerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Nationality"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getInt("TotalReservations"),
                        rs.getDate("RegistrationDate")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Ideally, use a logger and handle the exception more gracefully
        }
        return customers;
    }

    public static boolean Delete(Customer customer) {
        String sql = "DELETE FROM customers WHERE CustomerID = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, customer.getCustomerID());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
