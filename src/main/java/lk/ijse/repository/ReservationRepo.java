package lk.ijse.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import lk.ijse.db.DbConnection;
import lk.ijse.model.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ReservationRepo {
    public static String getCurrentId() throws SQLException {
        String sql = "SELECT ReservationID FROM reservation ORDER BY ReservationID DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String Reservationid = resultSet.getString(1);
            return Reservationid;
        }
        return null;
    }

    public static boolean save(Reservation reservation) {
        try {
            String sql = "INSERT INTO reservation VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);

            pstm.setObject(1, reservation.getReservationID());
            pstm.setObject(2, Date.valueOf(String.valueOf(reservation.getCheckInDate())));
            pstm.setObject(3, Date.valueOf(String.valueOf(reservation.getCheckOutDate())));
            pstm.setObject(4, Date.valueOf(String.valueOf(reservation.getReservationDate())));
            pstm.setObject(5, reservation.getDuration());
            pstm.setObject(6, reservation.getNumberofGuests());
            pstm.setObject(7, reservation.getStatus());

            return pstm.executeUpdate() > 0;
        }
        catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR , "Failed to save the reservation");alert.show();
            return false;
        }
    }
    public static String generateNextReservationId(String currentId) {
        if (currentId != null && currentId.startsWith("RES")) {
            int idNum = Integer.parseInt(currentId.substring(3));
            idNum++;
            return "RES" + String.format("%03d", idNum);
        }
        return "RES001";
    }
    public static ObservableList<Reservation> getAllReservations() {
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        String query = "SELECT * FROM reservation";
        try (Connection conn = DbConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getString("ReservationID"),
                        rs.getDate("CheckInDate"),
                        rs.getDate("CheckOutDate"),
                        rs.getDate("ReservationDate"),
                        rs.getString("Duration"),
                        rs.getInt("NumberofGuests"),
                        rs.getString("Status")
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching reservations: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to fetch reservations");
        }
        return reservations;
    }
    public static Map<String, String> getWeddingEvents() throws SQLException {
        String query = "SELECT PackageID, Name FROM package WHERE Name LIKE '%Wedding%'";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        Map<String, String> eventMap = new HashMap<>();
        while (resultSet.next()) {
            String packageId = resultSet.getString("PackageID");
            String packageName = resultSet.getString("Name");
            eventMap.put(packageName, packageId);
        }

        return eventMap;
    }
}
