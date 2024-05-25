package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.model.Supplier;
import lk.ijse.repository.SupplierRepo;

import java.sql.SQLException;

public class SupplierTableController {

    @FXML
    private TableView<Supplier> tablesupplier;

    @FXML
    private TableColumn<Supplier, String> colSupplierID;
    @FXML
    private TableColumn<Supplier, String> colName;
    @FXML
    private TableColumn<Supplier, Integer> colPostalCode;
    @FXML
    private TableColumn<Supplier, String> colEmailAddress;
    @FXML
    private TableColumn<Supplier, String> colContactNumber;

    @FXML
    public void initialize() {
        setcellValueFactory();
        loadSuppliers();
    }

    private void setcellValueFactory() {
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        colEmailAddress.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
    }

    private void loadSuppliers() {
        try {
            tablesupplier.setItems(SupplierRepo.getAllSuppliers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
