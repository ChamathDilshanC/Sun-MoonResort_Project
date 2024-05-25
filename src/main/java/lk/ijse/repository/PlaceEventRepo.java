package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Event;
import lk.ijse.model.Payment;
import lk.ijse.model.Reservation;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceEventRepo {
    public static boolean saveEvent(Event ev, Reservation reservation, Payment payment) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        System.out.println("ev: " + ev);
        System.out.println("reservation: " + reservation);

        try {
            boolean isReservationSaved = ReservationRepo.save(reservation);
            System.out.println("isReservationSaved: " + isReservationSaved);
            if (isReservationSaved) {
                boolean isEventSaved = EventRepo.save(ev);
                System.out.println("isEventSaved: " + isEventSaved);
                if (isEventSaved) {
                    boolean isPaymentSaved = PaymentRepo.save(payment);
                    System.out.println("isPaymentSaved: " + isPaymentSaved);
                    if (isPaymentSaved) {
                        connection.commit();
                        return true;
                    }
                }
                if (isEventSaved) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
