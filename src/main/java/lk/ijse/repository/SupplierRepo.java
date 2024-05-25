package lk.ijse.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.db.DbConnection;
import lk.ijse.model.Supplier;
import lk.ijse.model.tm.InventoryCart;

import java.sql.*;
import java.util.List;

public class SupplierRepo {

    public static void save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier (SupplierID, Name, PostalCode, EmailAddress, ContactNumber) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, supplier.getSupplierId());
            pstm.setString(2, supplier.getName());
            pstm.setInt(3, Integer.parseInt(supplier.getPostalCode()));
            pstm.setString(4, supplier.getEmailAddress());
            pstm.setString(5, supplier.getContactNumber());
            pstm.executeUpdate();
        }
    }

    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET Name = ?, PostalCode = ?, EmailAddress = ?, ContactNumber = ? WHERE SupplierID = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, supplier.getName());
            pstm.setInt(2, Integer.parseInt(supplier.getPostalCode()));
            pstm.setString(3, supplier.getEmailAddress());
            pstm.setString(4, supplier.getContactNumber());
            pstm.setString(5, supplier.getSupplierId());
            int updated = pstm.executeUpdate();
            return updated > 0;
        }
    }

    public static String getNextSupplierId() throws SQLException {
        String sql = "SELECT SupplierID FROM supplier ORDER BY SupplierID DESC LIMIT 1";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String lastId = rs.getString("SupplierID");
                int numericPart = Integer.parseInt(lastId.substring(3));
                numericPart++;
                return String.format("SUP%03d", numericPart);
            } else {
                return "SUP001";
            }
        }
    }
    public static Supplier searchById(String supplierId) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE SupplierID = ?";
        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, supplierId);
            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    return new Supplier(
                            resultSet.getString("SupplierID"),
                            resultSet.getString("Name"),
                            resultSet.getString("PostalCode"),
                            resultSet.getString("EmailAddress"),
                            resultSet.getString("ContactNumber")
                    );
                }
            }
        }
        return null;
    }
    public static Supplier searchByContactNumber(String contactNumber) throws SQLException {
        Connection conn = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM supplier WHERE ContactNumber = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contactNumber);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return new Supplier(
                        resultSet.getString("SupplierID"),
                        resultSet.getString("Name"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("EmailAddress"),
                        resultSet.getString("ContactNumber")
                );
            }
        }
        return null;
    }
    public static ObservableList<Supplier> getAllSuppliers() throws SQLException {
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM supplier";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                suppliers.add(new Supplier(
                        rs.getString("SupplierID"),
                        rs.getString("Name"),
                        rs.getString("PostalCode"),
                        rs.getString("EmailAddress"),
                        rs.getString("ContactNumber")
                ));
            }
        }
        return suppliers;
    }

    public static boolean Delete(Supplier supplier) {
        String sql = "DELETE FROM supplier WHERE SupplierID = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, supplier.getSupplierId());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static Supplier getSupplierByPhoneNumber(String phoneNumber) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM supplier WHERE ContactNumber = ?");
        pst.setString(1, phoneNumber);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return new Supplier(
                                rs.getString("SupplierID"),
                                rs.getString("Name"),
                                rs.getString("PostalCode"),
                                rs.getString("EmailAddress"),
                                rs.getString("ContactNumber")
                    );
        }
        return null;
    }
    public static ObservableList<String> getAllSupplierIds() throws SQLException {
        ObservableList<String> ids = FXCollections.observableArrayList();
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT SupplierID FROM supplier");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ids.add(rs.getString("SupplierID"));
            }
        }
        return ids;
    }

}
