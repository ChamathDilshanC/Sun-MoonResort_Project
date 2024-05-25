package lk.ijse.repository;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardRepository {

    public String getUsername(String userId) throws SQLException {
        String sql = "SELECT Username FROM user WHERE UserId = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Username");
            } else {
                return null;
            }
        }
    }

    public String getBestPackage() throws SQLException {
        String sql = "SELECT PackageID, COUNT(PackageID) AS PackageCount FROM reservation_package_info GROUP BY PackageID ORDER BY PackageCount DESC LIMIT 1";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("PackageID");
            } else {
                return null;
            }
        }
    }

    public int getAvailableRooms() throws SQLException {
        String sql = "SELECT COUNT(*) AS AvailableRooms FROM room WHERE Status = 'Available'";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("AvailableRooms");
            } else {
                return 0;
            }
        }
    }

    public int getCheckInCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS CheckInCount FROM reservation WHERE CheckInDate BETWEEN '2024-03-01' AND '2024-07-31'";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("CheckInCount");
            } else {
                return 0;
            }
        }
    }

    public int getCheckOutCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS CheckOutCount FROM reservation WHERE CheckOutDate BETWEEN '2024-03-01' AND '2024-07-31'";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("CheckOutCount");
            } else {
                return 0;
            }
        }
    }

    public int getTotalReservations() throws SQLException {
        String sql = "SELECT COUNT(*) AS TotalReservations FROM reservation";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("TotalReservations");
            } else {
                return 0;
            }
        }
    }

    public int getCustomerCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS CustomerCount FROM customers WHERE RegistrationDate >= '2024-02-01'";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("CustomerCount");
            } else {
                return 0;
            }
        }
    }

    public double getTotalRevenue() throws SQLException {
        String sql = "SELECT SUM(Amount) AS TotalRevenue FROM payment";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("TotalRevenue");
            } else {
                return 0.0;
            }
        }
    }

    public List<String> getRoomIdsByCheckoutDate(Date checkoutDate) throws SQLException {
        String sql = "SELECT rri.RoomID " +
                "FROM reservation r " +
                "JOIN reservation_room_info rri ON r.ReservationID = rri.ReservationID " +
                "WHERE r.CheckOutDate = ?";
        List<String> roomIds = new ArrayList<>();
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, checkoutDate);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    roomIds.add(rs.getString("RoomID"));
                }
            }
        }
        return roomIds;
    }

    public void updateRoomStatusToAvailable(String roomId) throws SQLException {
        String sql = "UPDATE room SET Status = 'Available' WHERE RoomID = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomId);
            pstmt.executeUpdate();
        }
    }
}
