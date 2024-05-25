package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.PlaceSupply;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceSupplyRepo {
    public static boolean placeSupply(PlaceSupply placeSupply) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            try {
                boolean isSupplyInventoryInfoSaved = SupplyInventoryInfoRepo.save(placeSupply.getSupplyinventoryinfo());
                if (isSupplyInventoryInfoSaved) {
                    boolean isinventoryQtyUpdated = InventoryRepo.update(placeSupply.getSupplyinventoryinfo());
                    if (isinventoryQtyUpdated) {
                        connection.commit();
                        return true;
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
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
