package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Payment;
import lk.ijse.model.Reservationserviceinfo;

import java.sql.*;

public class PaymentRepo {

    public static String getCurrentPaymentId() throws SQLException {
        String sql = "SELECT PaymentID FROM payment ORDER BY PaymentID DESC LIMIT 1";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("PaymentID");
            } else {
                return null;
            }
        }
    }

    public static boolean save(Payment rps) {
        try {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO payment VALUES(?, ?, ?, ?, ?, ?)");
            stm.setObject(1, rps.getPaymentId());
            stm.setObject(2, rps.getReservationId());
            stm.setObject(3, rps.getAmount());
            stm.setDate(4, Date.valueOf(String.valueOf(rps.getDate())));
            stm.setObject(5, rps.getMethod());
            stm.setObject(6, rps.getStatus());

            stm.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        return true;

    }
}
