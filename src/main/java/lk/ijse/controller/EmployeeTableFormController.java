package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.model.Employee;
import lk.ijse.repository.EmployeeRepo;

import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeeTableFormController {

    @FXML
    private TableView<Employee> tableEmployees;

    @FXML
    private TableColumn<Employee, String> colEmployeeID, colFirstName, colLastName, colPosition, colEmail, colPhone, colDepartment;

    @FXML
    private TableColumn<Employee, LocalDate> colHireDate;

    @FXML
    private TableColumn<Employee, Double> colSalary;

    @FXML
    public void initialize() {
        setCellValueFactory();
        try {
            loadEmployees();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR , "Failed to load employees");
            alert.show();
        }
    }

    private void setCellValueFactory() {
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colHireDate.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
    }

    private void loadEmployees() throws SQLException {
        tableEmployees.setItems(EmployeeRepo.getAllEmployees());
    }
}
