package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Reservationpackageinfo;
import lk.ijse.model.Reservationserviceinfo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class ReservationserviceinfoRepo {
    public static boolean save(List<Reservationserviceinfo> reservationserviceinfo) {
        for (
                Reservationserviceinfo rs : reservationserviceinfo) {
            try {
                PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO reservation_service_info VALUES(?,?)");
                stm.setObject(1, rs.getReservationID());
                stm.setObject(2, rs.getServiceID());
                stm.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return true;

    }
}
