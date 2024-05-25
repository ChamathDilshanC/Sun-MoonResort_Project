package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.PlaceOrderMeal;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderMealRepo {

    public static boolean placeOrderMeal(PlaceOrderMeal placeOrderMeal) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            try {
                    boolean isinventoryQtyUpdated = OrderMealRepo.update(placeOrderMeal.getOrdermealinfo());
                    if (isinventoryQtyUpdated) {
                        System.out.println("i came");
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
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
