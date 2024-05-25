package lk.ijse.repository;

import lk.ijse.model.tm.InventoryCart;

import java.sql.PreparedStatement;
import java.util.List;

public class SupplyInventoryInfoRepo {

    public static boolean save(List<InventoryCart> supplyinventoryinfo) {
        System.out.println("info");
        System.out.println(supplyinventoryinfo);
        for (InventoryCart inventoryCart : supplyinventoryinfo) {
            try {
                PreparedStatement stm = lk.ijse.db.DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO inventory_supplier_info VALUES(?,?,?,?,?,?)");
                stm.setObject(1, inventoryCart.getInventoryID());
                stm.setObject(2, inventoryCart.getSupplierID());
                stm.setObject(3, inventoryCart.getSupplyQuantity());
                stm.setObject(4, inventoryCart.getDeliveryDate());
                stm.setObject(5, inventoryCart.getPricePerUnit());
                stm.setObject(6, inventoryCart.getTotalPrice());
                stm.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
