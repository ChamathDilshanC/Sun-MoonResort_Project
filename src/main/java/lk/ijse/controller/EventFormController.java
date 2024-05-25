package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.model.Event;
import lk.ijse.model.Payment;
import lk.ijse.model.Reservation;
import lk.ijse.repository.EventRepo;
import lk.ijse.repository.PaymentRepo;
import lk.ijse.repository.PlaceEventRepo;
import lk.ijse.repository.ReservationRepo;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EventFormController {

    @FXML
    public Label lablepackageId;
    @FXML
    public JFXTextField txttotalamount;
    @FXML
    public JFXComboBox<String> cmbPaymentMethod;
    @FXML
    public JFXComboBox<String> cmbStatus;
    @FXML
    public Label lablenextpaymentid;
    @FXML
    private JFXComboBox<String> cmbEventNames;
    @FXML
    private DatePicker datepikerEventDate;
    @FXML
    private Label lableEventId;
    @FXML
    private Label lableReservationId;
    @FXML
    private JFXTextField mOfAttendence;
    @FXML
    private JFXTextField txtCustomerPhone;
    @FXML
    private JFXTextField txtholenumber;

    private Map<String, String> eventMap;

    @FXML
    public void initialize() {
        getCurrentReservationId();
        generateNextEventId();
        txtCustomerPhone.requestFocus();
        loadEventNames();
        fillcombo();
        getcurrentpaymentid();
        Platform.runLater(() -> txtCustomerPhone.requestFocus());
        setupFocusTransferHandlers();
        setDatepikerEventDate();
    }

    private void getcurrentpaymentid() {
        try {
            String currentId = PaymentRepo.getCurrentPaymentId();
            String nextId = generateNextPaymentId(currentId);
            lablenextpaymentid.setText(nextId);
        } catch (SQLException e) {
            e.printStackTrace();  // Handle exceptions appropriately
        }
    }

    private String generateNextPaymentId(String currentId) {
        if (currentId != null) {
            int idNum = Integer.parseInt(currentId.substring(3)) + 1;
            return String.format("PAY%03d", idNum);
        }
        return "PAY001";
    }

    private void loadEventNames() {
        try {
            eventMap = ReservationRepo.getWeddingEvents();
            System.out.println("Event Map: " + eventMap); // Debugging: print the event map
            cmbEventNames.getItems().addAll(eventMap.keySet());
        } catch (SQLException e) {
            e.printStackTrace();
            // Display an alert for better error visibility
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to load event names: " + e.getMessage());
            alert.show();
        }
    }

    @FXML
    void cmbEventNamesOnAction(ActionEvent event) {
        String selectedEventName = cmbEventNames.getValue();
        if (selectedEventName != null) {
            String packageId = eventMap.get(selectedEventName);
            lablepackageId.setText(packageId);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.lableReservationId.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Failed to open Dashboard", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    void txtCustomerPhoneOnAction(ActionEvent event) {
        cmbEventNames.requestFocus();
    }

    private void getCurrentReservationId() {
        try {
            String currentId = ReservationRepo.getCurrentId();
            String nextOrderId = ReservationRepo.generateNextReservationId(currentId);
            lableReservationId.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextEventId() {
        try {
            String currentEventId = EventRepo.getCurrentEventId();
            String nextEventId = EventRepo.generateNextEventId(currentEventId);
            lableEventId.setText(nextEventId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnPlaceEvent(ActionEvent event) throws SQLException {
        String eventId = lableEventId.getText();
        String reservationId = lableReservationId.getText();
        String eventName = cmbEventNames.getValue();

        String cphone = txtCustomerPhone.getText();
        if (cphone.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Please enter the Customer Phone Number");
            return;
        }

        if (eventName == null || eventName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Please select an event name.");
            return;
        }

        if (datepikerEventDate.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Please select an event date.");
            return;
        }

        String mOfAttendenceText = mOfAttendence.getText();
        if (mOfAttendenceText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Please enter the number of attendees.");
            return;
        }

        String txtCustomerPhoneText = txtCustomerPhone.getText();
        if (txtCustomerPhoneText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Please enter the customer phone number.");
            return;
        }

        String txtholenumberText = txtholenumber.getText();
        if (txtholenumberText.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Please enter the hall number.");
            return;
        }

        Date eventDate = Date.valueOf(datepikerEventDate.getValue().toString());
        int numberOfAttendees;
        int customerPhone;
        int hallNumber;

        try {
            numberOfAttendees = Integer.parseInt(mOfAttendenceText);
            customerPhone = Integer.parseInt(txtCustomerPhoneText);
            hallNumber = Integer.parseInt(txtholenumberText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Please enter valid numbers for attendees, phone, and hall number.");
            return;
        }
        double total = Double.parseDouble(txttotalamount.getText());
        Event ev = new Event(eventId, reservationId, eventName, eventDate, numberOfAttendees, customerPhone, hallNumber);
        Reservation reservation = new Reservation(reservationId,eventDate ,eventDate , eventDate, "1 Day", numberOfAttendees, "Wedding");
        Payment payment = new Payment(lablenextpaymentid.getText(), lableReservationId.getText(), total, datepikerEventDate.getValue(), cmbPaymentMethod.getValue().toString(), cmbStatus.getValue().toString());

        boolean isSaved = PlaceEventRepo.saveEvent(ev, reservation, payment);
        System.out.println("isSaved: " + isSaved);
        if (isSaved) {
            showAlert(Alert.AlertType.INFORMATION, "Event saved successfully.");
            clearFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "Failed to save the Event.");
        }
    }

    private void clearFields() {
        lableEventId.setText("");
        lableReservationId.setText("");
        cmbEventNames.getSelectionModel().clearSelection();
        datepikerEventDate.getEditor().clear();
        mOfAttendence.clear();
        txtCustomerPhone.clear();
        txtholenumber.clear();
    }

    private void showAlert(Alert.AlertType alertType, String content) {
        Alert alert = new Alert(alertType);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void cmbPaymentOnAction(ActionEvent actionEvent) {

    }

    public void cmbStatusOnAction(ActionEvent actionEvent) {

    }

    private void fillcombo() {
        ObservableList<String> statusList = FXCollections.observableArrayList("completed", "confirmed");
        cmbStatus.setItems(statusList);
        ObservableList<String> paymentMethods = FXCollections.observableArrayList("Cash", "Credit Card", "Debit Card", "Online Transfer");
        cmbPaymentMethod.setItems(paymentMethods);
    }

    private void setupFocusTransferHandlers() {
        List<Control> controls = Arrays.asList(txtCustomerPhone, cmbEventNames, datepikerEventDate, mOfAttendence, txtholenumber, txttotalamount, cmbPaymentMethod, cmbStatus);
        for (Control control : controls) {
            control.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    transferFocusAndOpen(control);
                }
            });
        }
    }

    private void transferFocusAndOpen(Control currentControl) {
        Control nextControl = null;
        switch (currentControl.getId()) {
            case "txtCustomerPhone":
                nextControl = cmbEventNames;
                break;
            case "cmbEventNames":
                nextControl = datepikerEventDate;
                break;
            case "datepikerEventDate":
                nextControl = txttotalamount;
                break;
            case "txttotalamount":
                nextControl = txtholenumber;
                break;
            case "txtholenumber":
                nextControl = mOfAttendence;
                break;
            case "mOfAttendence":
                nextControl = cmbPaymentMethod;
                break;
            case "cmbPaymentMethod":
                nextControl = cmbStatus;
                break;
            default:
                break;
        }

        if (nextControl != null) {
            if (nextControl instanceof ComboBox) {
                ComboBox<?> nextCombo = (ComboBox<?>) nextControl;
                Platform.runLater(() -> {
                    nextCombo.requestFocus();
                    nextCombo.show();
                });
            } else {
                Platform.runLater(nextControl::requestFocus);
            }
        }
    }
    @FXML
    public void txtPhoneOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.PHONE, txtCustomerPhone);
    }
    @FXML
    public void TxtqtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.QTY,txtholenumber);
    }
    @FXML
    public void txtPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.PRICE, txttotalamount);
    }
    @FXML
    public void TxtnumofguestOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.QTY,mOfAttendence);
    }

    public void setDatepikerEventDate() {
        LocalDate date = LocalDate.now();
        datepikerEventDate.setValue(date);
    }
}