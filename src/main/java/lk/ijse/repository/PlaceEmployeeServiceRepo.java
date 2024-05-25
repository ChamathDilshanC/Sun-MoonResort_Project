package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.PlaceEmployeeService;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceEmployeeServiceRepo {
    public static boolean placeEmployeeService(PlaceEmployeeService placeEmployeeService) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isAdded = EmployeeServiceRepo.save(placeEmployeeService.getEmployees());
            if (isAdded) {
                connection.commit();
                return true;
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