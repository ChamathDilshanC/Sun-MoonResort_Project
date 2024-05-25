package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Reservationroominfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomRepo {
    public static List<String> getRoomIds() throws SQLException {
        List<String> roomIds = new ArrayList<>();
        String sql = "SELECT RoomID FROM room WHERE Status = 'Available'";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                roomIds.add(rs.getString("RoomID"));
            }
        }
        return roomIds;
    }
    public static String getRoomStatus(String roomId) throws SQLException {
        String status = "Unavailable";
        String sql = "SELECT Status FROM room WHERE RoomID = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, roomId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("Status");
                }
            }
        }
        return status;
    }

    public static boolean update(List<Reservationroominfo> reservationroominfo) {
        String sql = "UPDATE room SET Status = 'Booked' WHERE RoomID = ?";
        try {Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
            for (Reservationroominfo room : reservationroominfo) {
                stmt.setString(1, room.getRoomID());
                stmt.addBatch();
            }
            int[] affectedRows = stmt.executeBatch();
            for (int affectedRow : affectedRows) {
                if (affectedRow == 0) {
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
