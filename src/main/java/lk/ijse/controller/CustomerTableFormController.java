package lk.ijse.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.model.Customer;
import lk.ijse.repository.CustomerRepo;
import java.sql.Date;


public class CustomerTableFormController {

    @FXML
    private TableColumn<Customer, String> colCustomerID;
    @FXML
    private TableColumn<Customer, String> colFirstName;
    @FXML
    private TableColumn<Customer, String> colLastName;
    @FXML
    private TableColumn<Customer, String> colNationality;
    @FXML
    private TableColumn<Customer, String> colEmail;
    @FXML
    private TableColumn<Customer, String> colPhone;
    @FXML
    private TableColumn<Customer, String> colAddress;
    @FXML
    private TableColumn<Customer, Date> colRegistrationDate;
    @FXML
    private TableColumn<Customer, Integer> colTotalReservations;

    @FXML
    private TableView<Customer> tablecustomers;

    @FXML
    public void initialize() {
        setCellValueFactory();
        loadTableData();
    }

    private  void setCellValueFactory(){
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        colNationality.setCellValueFactory(new PropertyValueFactory<>("Nationality"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("RegistrationDate"));
        colTotalReservations.setCellValueFactory(new PropertyValueFactory<>("TotalReservations"));
    }
    private void loadTableData() {
        try {
            tablecustomers.setItems(CustomerRepo.getAllCustomers());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong, please contact IT team");
            alert.show();
        }
    }
}
