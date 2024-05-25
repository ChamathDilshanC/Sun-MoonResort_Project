package lk.ijse.repository;

import lk.ijse.model.Reservationroominfo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReservationroominfoRepo {
    public static boolean save(List<Reservationroominfo> reservationroominfo) {
        for (Reservationroominfo reservationroominfo1 : reservationroominfo) {
            try {
                PreparedStatement stm = lk.ijse.db.DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO reservation_room_info VALUES(?,?)");
                stm.setObject(1, reservationroominfo1.getReservationID());
                stm.setObject(2, reservationroominfo1.getRoomID());
                stm.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
