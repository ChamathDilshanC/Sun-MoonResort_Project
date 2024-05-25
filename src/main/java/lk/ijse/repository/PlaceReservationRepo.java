package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.PlaceReservation;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceReservationRepo {
    public static boolean placereservation(PlaceReservation reservation) throws SQLException{
            Connection connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            try {
                boolean isReservationSaved = ReservationRepo.save(reservation.getReservation());
                System.out.println("isReservationSaved: " + isReservationSaved);
                if (isReservationSaved) {
                    boolean isPackageInfoSaved = ReservationpackageinfoRepo.save(reservation.getReservationpackageinfo());
                    System.out.println("isPackageInfoSaved: " + isPackageInfoSaved);
                    if (isPackageInfoSaved) {
                        boolean isRoomInfoSaved = ReservationroominfoRepo.save(reservation.getReservationroominfo());
                        System.out.println("isRoomInfoSaved: " + isRoomInfoSaved);
                        boolean isRoomUpdated = RoomRepo.update(reservation.getReservationroominfo());
                        System.out.println("isRoomUpdated: " + isRoomUpdated);
                        if (isRoomInfoSaved && isRoomUpdated) {
                            boolean isPaymentInfoSaved = PaymentRepo.save(reservation.getReservationpaymentinfo());
                            System.out.println("isPaymentInfoSaved: " + isPaymentInfoSaved);
                            if (isPaymentInfoSaved) {
                                boolean isServiceInfoSaved = ReservationserviceinfoRepo.save(reservation.getReservationserviceinfo());
                                System.out.println("isServiceInfoSaved: " + isServiceInfoSaved);
                                if (isServiceInfoSaved) {
                                    connection.commit();
                                    return true;
                                }

                            }
                        }
                    }
                }
                connection.rollback();
                return false;
            } catch (Exception e) {
                connection.rollback();
                return false;
            } finally {
                connection.setAutoCommit(true);
            }
    }
}

