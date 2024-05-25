package lk.ijse.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.db.DbConnection;
import lk.ijse.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT EmployeeID FROM employee";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery()) {

            List<String> idList = new ArrayList<>();
            while (resultSet.next()) {
                idList.add(resultSet.getString("EmployeeID"));
            }
            return idList;
        }
    }

    public static Employee searchById(String id) throws SQLException {
        String sql = "SELECT * FROM employee WHERE EmployeeID = ?";
        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, id);
            try (ResultSet resultSet = pstm.executeQuery()) {
                if (resultSet.next()) {
                    String employeeID = resultSet.getString("EmployeeID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String position = resultSet.getString("Position");
                    String email = resultSet.getString("Email");
                    String phone = resultSet.getString("Phone");
                    double salary = resultSet.getDouble("Salary");
                    Date hireDate = resultSet.getDate("HireDate");
                    String department = resultSet.getString("Department");

                    return new Employee(employeeID, firstName, lastName, position, email, phone, hireDate.toLocalDate(), salary, department);
                }
            }
        }
        return null;
    }

    public static void save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (EmployeeID, FirstName, LastName, Position, Email, Phone, HireDate, Salary, Department) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, employee.getEmployeeID());
            pstm.setString(2, employee.getFirstName());
            pstm.setString(3, employee.getLastName());
            pstm.setString(4, employee.getPosition());
            pstm.setString(5, employee.getEmail());
            pstm.setInt(6, Integer.parseInt(String.valueOf(employee.getPhone())));
            pstm.setDate(7, Date.valueOf(employee.getHireDate()));
            pstm.setDouble(8, employee.getSalary());
            pstm.setString(9, employee.getDepartment());
            pstm.executeUpdate();
        }
    }

    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET FirstName = ?, LastName = ?, Position = ?, Email = ?, Phone = ?, HireDate = ?, Salary = ?, Department = ? WHERE EmployeeID = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, employee.getFirstName());
            pstm.setString(2, employee.getLastName());
            pstm.setString(3, employee.getPosition());
            pstm.setString(4, employee.getEmail());
            pstm.setInt(5, Integer.parseInt(String.valueOf(employee.getPhone())));
            pstm.setDate(6, Date.valueOf(employee.getHireDate()));
            pstm.setDouble(7, employee.getSalary());
            pstm.setString(8, employee.getDepartment());
            pstm.setString(9, employee.getEmployeeID());
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        }
    }


    public static String getNextEmployeeId() throws SQLException {
        String sql = "SELECT EmployeeID FROM employee ORDER BY EmployeeID DESC LIMIT 1";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                String lastId = rs.getString("EmployeeID");
                int num = Integer.parseInt(lastId.substring(3)) + 1;
                return String.format("EMP%03d", num);
            } else {
                return "EMP001";
            }
        }
    }





    public static List<String> getDistinctDepartments() throws SQLException {
        List<String> departments = new ArrayList<>();
        String sql = "SELECT DISTINCT Department FROM employee"; // Ensure the table name is correct
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet resultSet = pstm.executeQuery()) {
            while (resultSet.next()) {
                departments.add(resultSet.getString("Department"));
            }
            return departments;
        }
    }
    public static Employee searchByPhone(String phone) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM employee WHERE phone = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, phone);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Employee(
                    resultSet.getString("employeeID"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("position"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getDate("hireDate").toLocalDate(),
                    resultSet.getDouble("salary"),
                    resultSet.getString("department")
            );
        }

        return null;
    }
    public static ObservableList<Employee> getAllEmployees() throws SQLException {
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employee";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getString("EmployeeID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Position"),
                        rs.getString("Email"),
                        rs.getInt("Phone"),
                        rs.getDate("HireDate").toLocalDate(), // Ensure conversion from SQL Date to LocalDate
                        rs.getDouble("Salary"),
                        rs.getString("Department")
                );
                employees.add(employee);
            }
        }
        return employees;
    }

    public static boolean Delete(String employee) {

        String sql = "DELETE FROM employee WHERE EmployeeID = ?";
        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, employee);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
