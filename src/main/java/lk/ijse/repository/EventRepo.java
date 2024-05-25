package lk.ijse.repository;

import javafx.scene.control.Alert;
import lk.ijse.db.DbConnection;
import lk.ijse.model.Event;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventRepo {
    public static String getCurrentEventId() throws SQLException {
        String sql = "SELECT EventID FROM event ORDER BY EventID DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public static String generateNextEventId(String currentId) {
        if (currentId != null && currentId.startsWith("EV")) {
            int idNum = Integer.parseInt(currentId.substring(2));
            idNum++;
            return "EV" + String.format("%03d", idNum);
        }
        return "EV001";
    }

    public static boolean save(Event ev) {
        try {
            String sql = "INSERT INTO event VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

            pstm.setString(1, ev.getEventID());
            pstm.setString(2, ev.getReservationID());
            pstm.setString(3, ev.getEventName());
            pstm.setDate(4, ev.getEventDate());
            pstm.setInt(5, ev.getNumberOfAttendees());
            pstm.setInt(6, ev.getCustomerPhone());
            pstm.setInt(7, ev.getHallNumber());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace for debugging
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save the Event: " + e.getMessage());
            alert.show();
            return false;
        }
    }
}
