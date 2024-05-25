package lk.ijse.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.db.DbConnection;
import lk.ijse.model.Inventory;
import lk.ijse.model.PlaceOrderMeal;
import lk.ijse.model.tm.InventoryCart;
import lk.ijse.model.tm.OrderMealCart;

public class InventoryRepo {

    public static ObservableList<String> getAllInventoryIds() throws SQLException {
        ObservableList<String> ids = FXCollections.observableArrayList();
        String sql = "SELECT InventoryID FROM inventory";  // Adjust SQL as per your table structure
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ids.add(rs.getString("InventoryID"));
            }
        }
        return ids;
    }

    public static Inventory getInventoryById(String inventoryId) throws SQLException {
        String sql = "SELECT * FROM inventory WHERE InventoryID = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, inventoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Inventory(
                            rs.getString("InventoryID"),
                            rs.getString("MealID"),
                            rs.getString("Name"),
                            rs.getInt("Quantity"),
                            rs.getInt("MinimumStockLevel"),
                            rs.getString("Ingredients")
                    );
                }
            }
        }
        return null;
    }

    public static boolean saveInventorySupplyInfo(List<InventoryCart> inventoryCart) {
        for (InventoryCart cart : inventoryCart) {
            boolean isSaved = saveInventorySupplyInfo(cart);
            if (!isSaved) {
                return false;
            }
        }
        return true;
    }

    //query
    private static boolean saveInventorySupplyInfo(InventoryCart cart) {
        String sql = "INSERT INTO inventory VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cart.getInventoryID());
            stmt.setString(2, cart.getSupplierID());
            stmt.setString(3, String.valueOf(cart.getSupplyQuantity()));
            stmt.setInt(4, Integer.parseInt(cart.getDeliveryDate()));
            stmt.setInt(5, cart.getPricePerUnit());
            stmt.setString(6, String.valueOf(cart.getTotalPrice()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(List<InventoryCart> supplyinventoryinfo) throws SQLException {

            try {
                for (InventoryCart inventoryCart : supplyinventoryinfo) {
                    String sql = "UPDATE inventory SET Quantity = Quantity + ? WHERE InventoryID = ?";
                    Connection connection = DbConnection.getInstance().getConnection();
                    PreparedStatement stm = connection.prepareStatement(sql);
                    stm.setObject(1, inventoryCart.getSupplyQuantity());
                    stm.setObject(2, inventoryCart.getInventoryID());
                    stm.executeUpdate();
                }return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

}