package lk.ijse.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.db.DbConnection;
import lk.ijse.model.Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageRepo {
    public static List<String> getIds() throws SQLException {
        String sql = "SELECT PackageID FROM package WHERE Name NOT LIKE '%Wedding%' ORDER BY PackageID ASC;";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery()) {

            List<String> idList = new ArrayList<>();
            while (resultSet.next()) {
                idList.add(resultSet.getString("PackageID"));
            }
            return idList;
        }
    }

    public static Package searchById(String id) throws SQLException {
        String sql = "SELECT * FROM package WHERE PackageID = ?";

        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, id);

            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    String packageID = resultSet.getString("PackageID");
                    String packageName = resultSet.getString("Name");
                    double packagePrice = resultSet.getDouble("Price");
                    String servicesIncluded = resultSet.getString("ServicesIncluded");

                    return new Package(packageID, packageName, packagePrice, servicesIncluded);
                }
            }
        }

        return null;
    }
    public static ObservableList<Package> getAllPackages() throws SQLException {
        ObservableList<Package> packages = FXCollections.observableArrayList();
        String sql = "SELECT PackageID, Name, Price, ServicesIncluded FROM package";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                packages.add(new Package(
                        rs.getString("PackageID"),
                        rs.getString("Name"),
                        rs.getDouble("Price"),
                        rs.getString("ServicesIncluded")
                ));
            }
        }
        return packages;
    }
}
