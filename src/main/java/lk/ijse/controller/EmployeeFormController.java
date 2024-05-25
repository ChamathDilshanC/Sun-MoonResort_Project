package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.Util.TextField;
import lk.ijse.model.Employee;
import lk.ijse.repository.EmployeeRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeeFormController {

    public JFXTextField txtPhonesearch;
    @FXML
    private DatePicker hireDatePicker;
    @FXML
    private JFXTextField txtEmployeeID, txtFirstName, txtLastName, txtPosition, txtEmail, txtPhone, txtSalary;
    @FXML
    private JFXComboBox<String> cmbDepartment;

    public void initialize() {
        loadNextEmployeeId();
        initializeEmployeeIdFieldListener();
        loadDepartments();
        setupPhoneFieldListener();
    }

    private void setupPhoneFieldListener() {
        txtPhonesearch.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchEmployeeByPhone(txtPhonesearch.getText());
            }
        });

        txtPhonesearch.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                searchEmployeeByPhone(txtPhonesearch.getText());
            }
        });
    }


    private void searchEmployeeByPhone(String phone) {
        if (!phone.isEmpty()) {
            try {
                Employee employee = EmployeeRepo.searchByPhone(phone);
                if (employee != null) {
                    fillEmployeeForm(employee);
                } else {
                    clearForm();
                }
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to fetch employee details: " + e.getMessage());
            }
        }
    }


    private void loadNextEmployeeId() {
        try {
            String nextId = EmployeeRepo.getNextEmployeeId();
            txtEmployeeID.setText(nextId);
            hireDatePicker.setValue(LocalDate.now());
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Connection Error", "Failed to load the next employee ID: " + e.getMessage());
        }
    }

    private void initializeEmployeeIdFieldListener() {
        txtEmployeeID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty() && !newValue.equals(oldValue)) {
                try {
                    Employee employee = EmployeeRepo.searchById(newValue);
                    if (employee != null) {
                        fillEmployeeForm(employee);
                    } else {
                        clearForm();
                    }
                } catch (SQLException e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to search employee: " + e.getMessage());
                }
            }
        });
    }

    private void fillEmployeeForm(Employee employee) {
        txtEmployeeID.setText(employee.getEmployeeID());
        txtFirstName.setText(employee.getFirstName());
        txtLastName.setText(employee.getLastName());
        txtPosition.setText(employee.getPosition());
        txtEmail.setText(employee.getEmail());
        txtPhone.setText(String.valueOf(employee.getPhone()));
        txtSalary.setText(String.format("%.2f", employee.getSalary()));
        cmbDepartment.setValue(employee.getDepartment());
        hireDatePicker.setValue(employee.getHireDate());
    }

    private void loadDepartments() {
        try {
            ObservableList<String> departments = FXCollections.observableArrayList(EmployeeRepo.getDistinctDepartments());
            cmbDepartment.setItems(departments);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load departments: " + e.getMessage());
        }
    }

    public void btnsaveOnAction(ActionEvent actionEvent) {
        if (areFieldsValid()) {
            Employee employee = createEmployeeFromForm();
            try {
                EmployeeRepo.save(employee);
                showAlert(Alert.AlertType.INFORMATION, "Save Successful", "Employee saved successfully.");
                refreshForm();
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to save employee: " + e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields are required.");
        }
    }

    public void btnupdateOnAction(ActionEvent actionEvent) {
        String employeeID = txtEmployeeID.getText();
        if (employeeID.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Employee ID is required.");
            return;
        }
        //validate feilds all not empty
        if (!areFieldsValid()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields are required.");
            return;
        }


        Employee employee = createEmployeeFromForm();
        try {
            boolean success = EmployeeRepo.update(employee);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Update Successful", "Employee updated successfully.");
                refreshForm();
            } else {
                showAlert(Alert.AlertType.ERROR, "Update Failed", "No such employee exists.");
                refreshForm();
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update employee: " + e.getMessage());
            refreshForm();
        }
    }

    public void btnclearOnAction(ActionEvent actionEvent) {
        clearForm();
    }

    private boolean areFieldsValid() {
        return !txtEmployeeID.getText().isEmpty() &&
                !txtFirstName.getText().isEmpty() &&
                !txtLastName.getText().isEmpty() &&
                !txtPosition.getText().isEmpty() &&
                !txtEmail.getText().isEmpty() &&
                !txtPhone.getText().isEmpty() &&
                !txtSalary.getText().isEmpty() &&
                cmbDepartment.getValue() != null &&
                hireDatePicker.getValue() != null;
    }

    private Employee createEmployeeFromForm() {
        return new Employee(
                txtEmployeeID.getText(),
                txtFirstName.getText(),
                txtLastName.getText(),
                txtPosition.getText(),
                txtEmail.getText(),
                txtPhone.getText(),
                hireDatePicker.getValue(),
                Double.parseDouble(txtSalary.getText()),
                cmbDepartment.getValue()
        );
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void cmbDepartmentOnAction(ActionEvent actionEvent) {
        String selectedDepartment = cmbDepartment.getValue();
        if (selectedDepartment != null) {
            System.out.println("Selected Department: " + selectedDepartment);
        }
    }

    public void btnEmployeeTableOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EmployeeTableForm.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    stage.setMaximized(false);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlertNew(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void btndeleteOnAction(ActionEvent actionEvent) {
        String EmployeeID = txtEmployeeID.getText();
        if (EmployeeID.isEmpty()) {
            showAlertNew("Validation Error", "Employee ID is required.");
            return;
        }
        boolean success = EmployeeRepo.Delete(EmployeeID);
        if (success) {
            showAlertNew("Delete Successful", "Employee Deleted successfully.");
            refreshForm();
        } else {
            showAlertNew("Delete Failed", "No such Employee exists.");
            refreshForm();
        }
    }

    private void clearForm() {
        txtEmployeeID.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtPosition.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtSalary.setText("");
        cmbDepartment.setValue(null);
        hireDatePicker.setValue(null);
        txtPhonesearch.setText("");
    }

    private void refreshForm() {
        clearForm();
        loadNextEmployeeId();
    }
    @FXML
    public void txtUserFristNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME, txtFirstName);
    }
    @FXML
    public void txtUserLastNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME, txtLastName);
    }
    @FXML
    public void txtEmpIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.EMPID, txtEmployeeID);
    }
    @FXML
    public void txtPositionOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.NAME, txtPosition);
    }
    @FXML
    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.EMAIL, txtEmail);
    }
    @FXML
    public void txtPhoneOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.PHONE, txtPhone);
    }
    @FXML
    public void txtSalaryOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.SALARY, txtSalary);
    }
    @FXML
    public void txtSearchPhoneOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.PHONE, txtPhonesearch);
    }

    //bubble sort
    public void sortEmployeeBySalary(ActionEvent actionEvent) {
        try {
            ObservableList<Employee> employees = FXCollections.observableArrayList(EmployeeRepo.getAllEmployees());
            for (int i = 0; i < employees.size(); i++) {
                for (int j = 0; j < employees.size() - 1; j++) {
                    if (employees.get(j).getSalary() > employees.get(j + 1).getSalary()) {
                        Employee temp = employees.get(j);
                        employees.set(j, employees.get(j + 1));
                        employees.set(j + 1, temp);
                    }
                }
            }
            showAlert(Alert.AlertType.INFORMATION, "Sort Successful", "Employees sorted by salary.");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to sort employees: " + e.getMessage());
        }
    }
}