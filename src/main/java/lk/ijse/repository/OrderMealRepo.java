package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.InventoryDetails;
import lk.ijse.model.MealDetails;
import lk.ijse.model.tm.InventoryCart;
import lk.ijse.model.tm.OrderMealCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMealRepo {
    public static List<String> getDistinctReservationIds() throws SQLException {
        List<String> reservationIds = new ArrayList<>();
        String sql = "SELECT DISTINCT ReservationID FROM reservation_room_info ORDER BY ReservationID";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                reservationIds.add(rs.getString("ReservationID"));
            }
        }
        return reservationIds;
    }
    public static List<String> getRoomIdsByReservationId(String reservationId) throws SQLException {
        List<String> roomIds = new ArrayList<>();
        String sql = "SELECT RoomID FROM reservation_room_info WHERE ReservationID = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reservationId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                roomIds.add(rs.getString("RoomID"));
            }
        }
        return roomIds;
    }

    public static List<String> getAllMealNames() throws SQLException {
        List<String> mealNames = new ArrayList<>();
        String sql = "SELECT Name FROM meal ORDER BY Name;";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                mealNames.add(rs.getString("Name"));
            }
        }
        return mealNames;
    }

    public static MealDetails getMealIdByMealName(String mealName) throws SQLException {
        MealDetails mealDetails = null;
        String sql = "SELECT MealID, Price FROM meal WHERE Name = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mealName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String mealId = rs.getString("MealID");
                double price = rs.getDouble("Price");
                mealDetails = new MealDetails(mealId, price);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching meal ID and price: " + e.getMessage());
        }
        return mealDetails;
    }
    public static InventoryDetails getInventoryDetailsByMealId(String mealId) throws SQLException {
        InventoryDetails details = null;
        String sql = "SELECT i.InventoryID, i.Quantity, i.MinimumStockLevel, i.Ingredients " +
                "FROM inventory i JOIN meal m ON i.MealID = m.MealID " +
                "WHERE m.MealID = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mealId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                details = new InventoryDetails(
                        rs.getString("InventoryID"),
                        rs.getInt("Quantity"),
                        rs.getInt("MinimumStockLevel"),
                        rs.getString("Ingredients")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching inventory details: " + e.getMessage());
        }
        return details;
    }


    public static boolean update(List<OrderMealCart> ordermealinfo) {
        try {
            for (OrderMealCart inventoryCart : ordermealinfo) {
                String sql = "UPDATE inventory SET Quantity = Quantity - ? WHERE InventoryID = ?";
                Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setObject(1, inventoryCart.getQuantity());
                stm.setObject(2, inventoryCart.getInventoryID());
                stm.executeUpdate();
            }return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
