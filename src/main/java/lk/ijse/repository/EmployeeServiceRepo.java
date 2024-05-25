package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Employee;
import lk.ijse.model.EmployeeService;
import lk.ijse.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceRepo {

    public static Employee getEmployeeByPhone(String phone) throws SQLException {
        String sql = "SELECT * FROM employee WHERE phone = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1, phone);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return new Employee(
                    resultSet.getString("employeeID"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getInt("phone")
            );
        }
        return null;
    }

    public static Service getServiceById(String serviceId) throws SQLException {
        String sql = "SELECT * FROM service WHERE serviceID = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1, serviceId);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return new Service(
                    resultSet.getString("serviceID"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price")
            );
        }
        return null;
    }

    public static List<String> getAllEmployeeIds() throws SQLException {
        List<String> employeeIds = new ArrayList<>();
        String sql = "SELECT employeeID FROM employee";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            employeeIds.add(resultSet.getString("employeeID"));
        }
        return employeeIds;
    }

    public static List<String> getAllServiceIds() throws SQLException {
        List<String> serviceIds = new ArrayList<>();
        String sql = "SELECT serviceID FROM service";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            serviceIds.add(resultSet.getString("serviceID"));
        }
        return serviceIds;
    }

    public static boolean save(List<EmployeeService> employees) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        boolean isSuccessful = true;

        try {
            for (EmployeeService employee : employees) {
                // Check if the entry already exists
                String checkSql = "SELECT COUNT(*) FROM employee_service_info WHERE employeeID = ? AND serviceID = ?";
                PreparedStatement checkStmt = connection.prepareStatement(checkSql);
                checkStmt.setString(1, employee.getEmployeeID());
                checkStmt.setString(2, employee.getServiceID());
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                if (count == 0) {
                    // If the entry does not exist, insert it
                    String insertSql = "INSERT INTO employee_service_info VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement insertStmt = connection.prepareStatement(insertSql);
                    insertStmt.setString(1, employee.getEmployeeID());
                    insertStmt.setString(2, employee.getServiceID());
                    insertStmt.setInt(3, employee.getHoursAllocated());
                    insertStmt.setString(4, employee.getServiceDate());
                    insertStmt.setDouble(5, employee.getTotalServicePrice());

                    if (insertStmt.executeUpdate() <= 0) {
                        isSuccessful = false;
                    }
                } else {
                    System.out.println("Duplicate entry found for employeeID: " + employee.getEmployeeID() + ", serviceID: " + employee.getServiceID());
                }

                // the totalservice price add to salary of employee table
                String updateSql = "UPDATE employee SET salary = salary + ? WHERE employeeID = ?";
                PreparedStatement updateStmt = connection.prepareStatement(updateSql);
                updateStmt.setDouble(1, employee.getTotalServicePrice());
                updateStmt.setString(2, employee.getEmployeeID());

                if (updateStmt.executeUpdate() <= 0) {
                    isSuccessful = false;
                }
            }

            return isSuccessful;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
