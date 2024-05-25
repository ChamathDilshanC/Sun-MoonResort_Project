package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.Util.TextField;
import lk.ijse.model.Customer;
import lk.ijse.model.Supplier;
import lk.ijse.repository.CustomerRepo;
import lk.ijse.repository.SupplierRepo;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class SupplierFormController {

    public JFXTextField txtSearchContactNumber;
    @FXML
    private JFXButton btnClear, btnSave, btnUpdate;
    @FXML
    private JFXTextField txtContactNumber, txtEmailAddress, txtName, txtPostalCode, txtSupplierID;

    @FXML
    public void initialize() {
        loadNextSupplierId();
        setupSupplierIdFieldListener();
        setupContactNumberFieldListener();

    }

    private void loadNextSupplierId() {
        try {
            String nextId = SupplierRepo.getNextSupplierId();  // Implement this method in the repository
            txtSupplierID.setText(nextId);
        } catch (SQLException e) {
            showAlert("Database Connection Error", "Failed to load the next supplier ID: " + e.getMessage());
        }
    }

    @FXML
    private void setupContactNumberFieldListener() {
        txtSearchContactNumber.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchSupplierByContactNumber(txtSearchContactNumber.getText().trim());
            }
        });

        txtSearchContactNumber.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {  // focus lost
                searchSupplierByContactNumber(txtSearchContactNumber.getText().trim());
            }
        });
    }

    private void searchSupplierByContactNumber(String contactNumber) {
        if (!contactNumber.isEmpty()) {
            try {
                Supplier supplier = SupplierRepo.searchByContactNumber(contactNumber);
                if (supplier != null) {
                    fillFormWithSupplierData(supplier);
                } else {
                    clearForm();
                }
            } catch (SQLException e) {
                showAlert("Database Error", "Failed to fetch supplier details: " + e.getMessage());
            }
        }
    }

    @FXML
    void btnclearOnAction(ActionEvent event) {
        clearForm();
    }

    @FXML
    void btnsaveOnAction(ActionEvent event) {
        if (areFieldsValid()) {
            Supplier supplier = createSupplierFromForm();
            try {
                SupplierRepo.save(supplier);
                clearForm();
                showAlert("Save Successful", "Supplier saved successfully.", Alert.AlertType.INFORMATION);
                refreshForm();
            } catch (SQLException e) {
                showAlert("Error", "Failed to save supplier: " + e.getMessage(), Alert.AlertType.ERROR);
                refreshForm();
            }
        } else {
            showAlert("Validation Error", "All fields are required.", Alert.AlertType.WARNING);
        }
    }
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void btnupdateOnAction(ActionEvent event) {
        String supplierID = txtSupplierID.getText().trim();
        if (supplierID.isEmpty()) {
            showAlert("Validation Error", "Supplier ID is required.", Alert.AlertType.WARNING);
            return;
        }

        if (areFieldsValid()) {
            Supplier supplier = createSupplierFromForm();
            try {
                boolean updated = SupplierRepo.update(supplier);
                if (updated) {
                    showAlert("Update Successful", "Supplier updated successfully.", Alert.AlertType.INFORMATION);
                    refreshForm();
                } else {
                    showAlert("Update Failed", "No such supplier exists with ID: " + supplierID, Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                showAlert("Error", "Failed to update supplier: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Validation Error", "Change Supplier Id that want toupdate.", Alert.AlertType.WARNING);
        }
    }


    private boolean areFieldsValid() {
        return !txtSupplierID.getText().isEmpty() &&
                !txtName.getText().isEmpty() &&
                !txtPostalCode.getText().isEmpty() &&
                !txtEmailAddress.getText().isEmpty() &&
                !txtContactNumber.getText().isEmpty();
    }

    private Supplier createSupplierFromForm() {
        return new Supplier(
                txtSupplierID.getText(),
                txtName.getText(),
                txtPostalCode.getText(),
                txtEmailAddress.getText(),
                txtContactNumber.getText()
        );
    }

    private void clearForm() {
        txtName.setText("");
        txtPostalCode.setText("");
        txtEmailAddress.setText("");
        txtContactNumber.setText("");
        txtSearchContactNumber.setText("");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void setupSupplierIdFieldListener() {
        txtSupplierID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty() && !oldValue.equals(newValue)) {
                    try {
                        Supplier supplier = SupplierRepo.searchById(newValue);
                        if (supplier != null) {
                            fillFormWithSupplierData(supplier);
                        } else {
                            clearForm();  // Optional: Clear the form if no supplier is found
                        }
                    } catch (SQLException e) {
                        showAlert("Error", "Failed to search supplier: " + e.getMessage());
                    }
                }
            }
        });
    }

    private void fillFormWithSupplierData(Supplier supplier) {
        txtSupplierID.setText(supplier.getSupplierId());
        txtName.setText(supplier.getName());
        txtPostalCode.setText(String.valueOf(supplier.getPostalCode()));
        txtEmailAddress.setText(supplier.getEmailAddress());
        txtContactNumber.setText(supplier.getContactNumber());
    }

    public void btnSupplierTableOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SupplierTableForm.fxml"));
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

    public void btndeleteOnAction(ActionEvent actionEvent) {
        String SupplierID = txtSupplierID.getText();
        if (SupplierID.isEmpty()) {
            showAlert("Validation Error", "Customer ID is required.");
            return;
        }

        Supplier supplier = createCustomerFromForm();
        boolean success = SupplierRepo.Delete(supplier);
        if (success) {
            showAlert("Delete Successful", "Supplier Deleted successfully.");
            refreshForm();
        } else {
            showAlert("Delete Failed", "No such Supplier exists.");
            refreshForm();
        }
    }

    private Supplier createCustomerFromForm() {
        return new Supplier(
                txtSupplierID.getText(),
                txtName.getText(),
                txtPostalCode.getText(),
                txtEmailAddress.getText(),
                txtContactNumber.getText()
        );
    }

    void refreshForm() {
        loadNextSupplierId();
        clearForm();
    }
    @FXML
    public void txtSupNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME, txtName);
    }
    @FXML
    public void txtSupIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.SUPID, txtSupplierID);
    }
    @FXML
    public void txtPostalCodeOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.POSTALCODE, txtPostalCode);
    }
    @FXML
    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.EMAIL, txtEmailAddress);
    }
    @FXML
    public void txtPhoneOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.PHONE, txtContactNumber);
    }
    @FXML
    public void txtsearchPhoneOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.PHONE, txtSearchContactNumber);
    }

    public void contactnumOnAction(ActionEvent actionEvent) {
    }
}

