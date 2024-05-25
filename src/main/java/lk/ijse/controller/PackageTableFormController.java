package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.model.Package;
import lk.ijse.repository.PackageRepo;

import java.sql.SQLException;

public class PackageTableFormController {

    @FXML
    private TableView<Package> tablepackage;

    @FXML
    private TableColumn<Package, String> colPackageID;
    @FXML
    private TableColumn<Package, String> colName;
    @FXML
    private TableColumn<Package, Double> colPrice;
    @FXML
    private TableColumn<Package, String> colServicesIncluded;

    @FXML
    public void initialize() {
        setCellValueFactory();
        try {
            loadTableData();
        } catch (SQLException ex) {
            System.err.println("Error loading packages: " + ex.getMessage());
        }
    }

    private void setCellValueFactory() {
        colPackageID.setCellValueFactory(new PropertyValueFactory<>("packageID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("PackageName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("PackagePrice"));
        colServicesIncluded.setCellValueFactory(new PropertyValueFactory<>("servicesIncluded"));
    }

    private void loadTableData() throws SQLException {
        tablepackage.setItems(FXCollections.observableArrayList(PackageRepo.getAllPackages()));
    }
}
