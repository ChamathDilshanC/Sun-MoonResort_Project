package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Reservationpackageinfo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReservationpackageinfoRepo {
    public static boolean save(List<Reservationpackageinfo> reservationpackageinfo) throws SQLException {
        for (Reservationpackageinfo reservationpackageinfo1 : reservationpackageinfo) {
            try {
                PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO reservation_package_info VALUES(?,?,?)");
                stm.setObject(1, reservationpackageinfo1.getReservationID());
                stm.setObject(2, reservationpackageinfo1.getPackageID());
                stm.setObject(3, reservationpackageinfo1.getCustomerID());
                stm.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return true;

    }
}