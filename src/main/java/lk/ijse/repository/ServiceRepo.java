package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepo {

    public static List<String> getServiceIds() throws SQLException {
        List<String> ids = new ArrayList<>();
        String sql = "SELECT ServiceID FROM Service";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            ids.add(rs.getString("ServiceID"));
        }
        return ids;
    }

    public static double getServicePriceById(String serviceId) throws SQLException {
        String sql = "SELECT Price FROM Service WHERE ServiceID = ?";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, serviceId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return rs.getDouble("Price");
        }
        return 0;
    }
}
