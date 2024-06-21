package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.db.DbConnection;
import lk.ijse.model.*;
import lk.ijse.model.Package;
import lk.ijse.model.tm.ReservationCartItem;
import lk.ijse.repository.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class ReservationFormController {
    public JFXComboBox cmbPaymentMethod, cmbroomid;
    public Label roomavailability;
    public JFXButton btnAddToCart;
    public Label lablenextpaymentid, lablenettotal;
    @FXML
    public JFXTextField txtCustomerphone;
    public Label lablecustEmail;

    @FXML
    private DatePicker checkindatepiker, checkoutdatepiker;
    @FXML
    private JFXComboBox<String> cmbCustomerId, cmbpackageid, cmbStatus, cmbServiceId;

    @FXML
    private Label lableServicePrice, lablepackagename, lablepackageprice, lablereservationdate, lablereservationid, labelduration, lablecustomerfristname, lablecustomerlastname, lablecusNationality;
    @FXML
    private JFXComboBox<Integer> cmbNumGuest;

    @FXML
    private TableView<ReservationCartItem> tblreservationcart;
    @FXML
    private TableColumn<ReservationCartItem, String> colReservationId, colPackageId, colServiceId, colRoomID;
    @FXML
    private TableColumn<ReservationCartItem, Double> colPackagePrice, colServicePrice, colTotalPrice;
    @FXML
    private TableColumn<ReservationCartItem, String> colCustomerId;
    @FXML
    private TableColumn<ReservationCartItem, Void> colAction;


    public void initialize() throws SQLException {
        txtCustomerphone.requestFocus();
        setcellValueFactory();
        fillStatusComboBox();
        getCurrentreservationId();
        setDate();
        getCustomerIds();
        getPackageIds();
        fillStatusComboBox();
        fillNumGuestComboBox();
        setupDatePickers();
        fillServiceComboBox();
        setupRemoveButtonColumn();
        fillPaymentMethods();
        loadRoomIds();
        getNextPaymentId();
        setcheckindate();
        setupFocusTransferHandlers();
        Platform.runLater(() -> txtCustomerphone.requestFocus());
        setupCustomerPhoneFieldListener();
        setNetTotal();
    }

    private void setNetTotal() {
        double netTotal = tblreservationcart.getItems().stream()
                .mapToDouble(ReservationCartItem::getTotalPrice)
                .sum();
        DecimalFormat df = new DecimalFormat("#,###.00");
        lablenettotal.setText("LKR " + df.format(netTotal));
    }

    private void setcellValueFactory() {
        colReservationId.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        colPackageId.setCellValueFactory(new PropertyValueFactory<>("packageId"));
        colPackagePrice.setCellValueFactory(new PropertyValueFactory<>("packagePrice"));
        colServiceId.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        colServicePrice.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colRoomID.setCellValueFactory(new PropertyValueFactory<>("RoomID"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    }

    private void setcheckindate() {
        LocalDate now = LocalDate.now();
        checkindatepiker.setValue(now);
    }

    private void loadRoomIds() {
        ObservableList<String> roomIds = FXCollections.observableArrayList();
        try {
            roomIds.addAll(RoomRepo.getRoomIds());
            cmbroomid.setItems(roomIds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillPaymentMethods() {
        ObservableList<String> paymentMethods = FXCollections.observableArrayList("Cash", "Credit Card", "Debit Card", "Online Transfer");
        cmbPaymentMethod.setItems(paymentMethods);
    }

    private void setupRemoveButtonColumn() {
        colAction.setCellFactory(param -> new TableCell<ReservationCartItem, Void>() {
            private final Button btn = new Button("Remove");

            {
                btn.setOnAction((ActionEvent event) -> {
                    ReservationCartItem item = getTableView().getItems().get(getIndex());
                    Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmDialog.setTitle("Confirm Removal");
                    confirmDialog.setHeaderText(null);
                    confirmDialog.setContentText("Do you really want to delete this item?");

                    ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                    ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
                    confirmDialog.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                    Optional<ButtonType> result = confirmDialog.showAndWait();
                    if (result.isPresent() && result.get() == buttonTypeYes) {
                        getTableView().getItems().remove(item);
                        setNetTotal();  // Update net total after removing an item
                        tblreservationcart.refresh();
                        Platform.runLater(() -> tblreservationcart.requestFocus()); // Focus the table view again for keyboard navigation
                    }
                });
                btn.getStyleClass().add("remove-button");
                btn.setCursor(Cursor.HAND);
                setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });
    }

    private void fillServiceComboBox() throws SQLException {
        ObservableList<String> serviceIds = FXCollections.observableArrayList(ServiceRepo.getServiceIds());
        cmbServiceId.setItems(serviceIds);
    }

    private void setupDatePickers() {
        checkindatepiker.valueProperty().addListener((obs, oldDate, newDate) -> updateDuration());
        checkoutdatepiker.valueProperty().addListener((obs, oldDate, newDate) -> updateDuration());
    }

    private void updateDuration() {
        LocalDate checkIn = checkindatepiker.getValue();
        LocalDate checkOut = checkoutdatepiker.getValue();
        if (checkIn != null && checkOut != null && !checkOut.isBefore(checkIn)) {
            long daysBetween = ChronoUnit.DAYS.between(checkIn, checkOut);
            labelduration.setText(daysBetween + " days");
        } else {
            labelduration.setText("Invalid range");
        }
    }

    private void fillNumGuestComboBox() {
        ObservableList<Integer> numbers = FXCollections.observableArrayList();
        for (int i = 1; i <= 30; i++) {
            numbers.add(i);
        }
        cmbNumGuest.setItems(numbers);
    }

    private void fillStatusComboBox() {
        ObservableList<String> statusList = FXCollections.observableArrayList("completed", "confirmed");
        cmbStatus.setItems(statusList);
    }

    private void getPackageIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = PackageRepo.getIds();
            obList.addAll(idList);
            cmbpackageid.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        if (areFieldsValid()) {
            double packagePrice = Double.parseDouble(lablepackageprice.getText().replace(",", ""));
            var servicePrice = Double.parseDouble(lableServicePrice.getText().replace(",", ""));
            double totalPrice = packagePrice + servicePrice;

            JFXButton btnRemove = new JFXButton("Remove");
            btnRemove.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #0055ff;"); // Style button with red color and white text
            btnRemove.setCursor(Cursor.HAND);
            setNetTotal();
            ReservationCartItem item = new ReservationCartItem(
                    lablereservationid.getText(),
                    cmbCustomerId.getValue(),
                    cmbpackageid.getValue(),
                    packagePrice,
                    cmbServiceId.getValue(),
                    servicePrice,
                    totalPrice,
                    cmbroomid.getValue().toString()
            );

            setNetTotal();
            btnRemove.setOnAction(e -> {
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this item?", yes, no);
                confirmAlert.setTitle("Confirmation");
                Optional<ButtonType> result = confirmAlert.showAndWait();
                if (result.isPresent() && result.get() == yes) {
                    tblreservationcart.getItems().remove(item);
                    tblreservationcart.refresh();
                }
            });
            tblreservationcart.getItems().add(item);
            tblreservationcart.refresh();
            setNetTotal();
            cmbpackageid.setValue(null);
            cmbServiceId.setValue(null);
            cmbroomid.setValue(null);

        } else {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required and must be valid.");
            tblreservationcart.refresh();
            txtCustomerphone.requestFocus();
            setNetTotal();
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) this.lablereservationid.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Failed to open Dashboard", e.getMessage());
        }
    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerForm.fxml"));
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
            showAlert(Alert.AlertType.ERROR, "Failed to open new customer form", e.getMessage());
        }
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String selectedCustomerId = cmbCustomerId.getValue();
        if (selectedCustomerId != null) {
            try {
                Customer customer = CustomerRepo.searchById(selectedCustomerId);
                if (customer != null) {
                    lablecustomerfristname.setText(customer.getFirstName());
                    lablecustomerlastname.setText(customer.getLastName());
                    lablecusNationality.setText(customer.getNationality());
                    lablecustEmail.setText(customer.getEmail());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Customer not found");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to fetch customer details: " + e.getMessage());
            }
        }
    }

    @FXML
    void cmbpackageidOnAction(ActionEvent event) {
        String selectedPackageId = cmbpackageid.getValue();
        if (selectedPackageId != null) {
            try {
                Package packageObj = PackageRepo.searchById(selectedPackageId);
                if (packageObj != null) {
                    double usdPrice = packageObj.getPackagePrice();
                    double lkrPrice = usdPrice * 300;
                    DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
                    String formattedPrice = decimalFormat.format(lkrPrice);

                    lablepackagename.setText(packageObj.getPackageName());
                    lablepackageprice.setText(formattedPrice);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Package not found");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to fetch package details: " + e.getMessage());
            }
        }
    }

    private void getCurrentreservationId() {
        try {
            String currentId = ReservationRepo.getCurrentId();
            String nextOrderId = ReservationRepo.generateNextReservationId(currentId);
            lablereservationid.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = CustomerRepo.getIds();
            obList.addAll(idList);
            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lablereservationdate.setText(String.valueOf(now));
    }

    @FXML
    void cmbStatusOnAction(ActionEvent event) {
        String selectedStatus = cmbStatus.getValue();
        if (selectedStatus != null) {
            System.out.println("Selected Status: " + selectedStatus);
        }
    }

    @FXML
    void cmbserviceidOnAction(ActionEvent event) {
        String selectedServiceId = cmbServiceId.getValue();
        if (selectedServiceId != null) {
            try {
                double priceUSD = ServiceRepo.getServicePriceById(selectedServiceId);
                final double exchangeRateUSDToLKR = 200;
                double priceLKR = priceUSD * exchangeRateUSDToLKR;
                DecimalFormat df = new DecimalFormat("#,###.00");
                lableServicePrice.setText(df.format(priceLKR));
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to fetch service price: " + e.getMessage());
                lableServicePrice.setText("Error loading price");
            }
        }
    }

    @FXML
    void cmbnumguestOnAction(ActionEvent actionEvent) {
        Integer selectedNumber = cmbNumGuest.getValue();
    }

    @FXML
    void cmbroomidOnAction(ActionEvent actionEvent) {
        String selectedRoomId = (String) cmbroomid.getValue();
        if (selectedRoomId != null) {
            try {
                String status = RoomRepo.getRoomStatus(selectedRoomId);
                roomavailability.setText(status);
                btnAddToCart.setDisable(!"Available".equals(status));
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to fetch room status: " + e.getMessage());
            }
        }
    }

    public void cmbPaymentOnAction(ActionEvent actionEvent) {
    }

    @FXML
    void cmbPaymentMethodOnAction(ActionEvent event) {
        String selectedPaymentMethod = (String) cmbPaymentMethod.getValue();
        if (selectedPaymentMethod != null) {
            System.out.println("Selected Payment Method: " + selectedPaymentMethod);
        }
    }

    private boolean areFieldsValid() {
        boolean validCombos = cmbCustomerId.getValue() != null &&
                cmbpackageid.getValue() != null &&
                cmbServiceId.getValue() != null &&
                cmbPaymentMethod.getValue() != null &&
                cmbroomid.getValue() != null &&
                cmbNumGuest.getValue() != null &&
                cmbStatus.getValue() != null;

        boolean validDates = checkindatepiker.getValue() != null &&
                checkoutdatepiker.getValue() != null &&
                !checkoutdatepiker.getValue().isBefore(checkindatepiker.getValue());
        return validCombos && validDates;
    }

    private void getNextPaymentId() {
        try {
            String currentId = PaymentRepo.getCurrentPaymentId();
            String nextId = generateNextPaymentId(currentId);
            lablenextpaymentid.setText(nextId);
        } catch (SQLException e) {
            e.printStackTrace();  // Handle exceptions appropriately
        }
    }

    private void setupFocusTransferHandlers() {
        List<Control> controls = Arrays.asList(txtCustomerphone, cmbpackageid, cmbServiceId, cmbPaymentMethod, cmbroomid, cmbNumGuest, cmbStatus, checkindatepiker, checkoutdatepiker, btnAddToCart);
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
            case "txtCustomerphone":
                nextControl = cmbpackageid;
                cmbpackageid.requestFocus();
                break;
            case "cmbpackageid":
                nextControl = cmbServiceId;
                break;
            case "cmbServiceId":
                nextControl = checkindatepiker;
                break;
            case "checkindatepiker":
                nextControl = checkoutdatepiker;
                break;
            case "checkoutdatepiker":
                nextControl = cmbNumGuest;
                break;
            case "cmbNumGuest":
                nextControl = cmbroomid;
                break;
            case "cmbroomid":
                nextControl = cmbStatus;
                break;
            case "cmbStatus":
                nextControl = cmbPaymentMethod;
                break;
            case "cmbPaymentMethod":
                nextControl = btnAddToCart;
                setNetTotal();
                break;
            default:
                break;
        }
        if (nextControl instanceof ComboBox) {
            ComboBox<?> nextCombo = (ComboBox<?>) nextControl;
            Platform.runLater(() -> {
                nextCombo.requestFocus();
                nextCombo.show();
            });
        } else if (nextControl instanceof DatePicker) {
            DatePicker nextDatePicker = (DatePicker) nextControl;
            Platform.runLater(() -> {
                nextDatePicker.requestFocus();
                nextDatePicker.show();
            });
        } else if (nextControl instanceof Button) {
            Platform.runLater(nextControl::requestFocus);
        }
    }

    private String generateNextPaymentId(String currentId) {
        if (currentId != null) {
            int idNum = Integer.parseInt(currentId.substring(3)) + 1;
            return String.format("PAY%03d", idNum);
        }
        return "PAY001";
    }

    public void checkindatepikerOnAction(ActionEvent actionEvent) {
        checkoutdatepikerOnAction(actionEvent);
    }

    public void checkoutdatepikerOnAction(ActionEvent actionEvent) {
    }

    public void ReservationTableOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ReservationTableForm.fxml"));
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
            showAlert(Alert.AlertType.ERROR, "Failed to open Reservation Table", e.getMessage());
        }
    }

    private void setupCustomerPhoneFieldListener() {
        txtCustomerphone.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                fetchCustomerDetailsByPhone(txtCustomerphone.getText().trim());
            }
        });
    }

    private void fetchCustomerDetailsByPhone(String phone) {
        try {
            Customer customer = CustomerRepo.searchByPhone(phone);
            if (customer != null) {
                cmbCustomerId.setValue(customer.getCustomerID());
                lablecustEmail.setText(customer.getEmail());
                lablecustomerfristname.setText(customer.getFirstName());
                lablecustomerlastname.setText(customer.getLastName());
                lablecusNationality.setText(customer.getNationality());
                cmbpackageid.requestFocus();
            } else {
                showAlert("No Customer Found", "No customer found with the entered phone number.");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to fetch customer details: " + e.getMessage());
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void btnPlaceReservationOnAction(ActionEvent event) throws SQLException {
        String reservationid = lablereservationid.getText();
        Date checkindate = Date.valueOf(checkindatepiker.getValue());
        Date checkoutdate = Date.valueOf(checkoutdatepiker.getValue());
        Date reservationdate = Date.valueOf(LocalDate.parse(lablereservationdate.getText()));
        String duration = labelduration.getText();
        int numofguest = cmbNumGuest.getValue();
        String status = cmbStatus.getValue();

        if (checkindate == null || checkoutdate == null || reservationdate == null || duration == null || numofguest == 0 || status == null) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required.");
            return;
        }
        Reservation reservation = new Reservation(
                reservationid, checkindate, checkoutdate, reservationdate, duration, numofguest, status
        );
        System.out.println(reservation);

        List<Reservationpackageinfo> reservationpackgagelist = new ArrayList<>();

        for (ReservationCartItem item : tblreservationcart.getItems()) {
            Reservationpackageinfo reservationpackageinfo = new Reservationpackageinfo(
                    reservationid,
                    item.getPackageId(),
                    item.getCustomerId()
            );
            reservationpackgagelist.add(reservationpackageinfo);
        }
        System.out.println(reservationpackgagelist);

        List<Reservationserviceinfo> reservationservicelist = new ArrayList<>();

        for (ReservationCartItem item : tblreservationcart.getItems()) {
            Reservationserviceinfo reservationserviceinfo = new Reservationserviceinfo(
                    reservationid,
                    item.getServiceId()
            );
            reservationservicelist.add(reservationserviceinfo);
        }
        System.out.println(reservationservicelist);

        List<Payment> paymentList = new ArrayList<>();
        Payment payment = new Payment(
                lablenextpaymentid.getText(),
                reservationid,
                Double.parseDouble(lablenettotal.getText().replace("LKR ", "").replace(",", "")),
                Date.valueOf(LocalDate.now()).toLocalDate(),
                (String) cmbPaymentMethod.getValue(),
                cmbStatus.getValue()
        );
        paymentList.add(payment);
        System.out.println(paymentList);

        List<Reservationroominfo> reservationroominfoList = new ArrayList<>();
        for (ReservationCartItem item : tblreservationcart.getItems()) {
            Reservationroominfo reservationroominfo = new Reservationroominfo(
                    reservationid,
                    item.getRoomID()
            );
            reservationroominfoList.add(reservationroominfo);
        }
        System.out.println(reservationroominfoList);

        PlaceReservation placereservation = new PlaceReservation(reservation, reservationpackgagelist, reservationroominfoList, reservationservicelist, payment);

        boolean isPlaced = PlaceReservationRepo.placereservation(placereservation);
        if (isPlaced) {
            new Alert(Alert.AlertType.CONFIRMATION, "Reservation Placed!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, " Reservation Placed Unsuccessfully!").show();
        }
    }

    @FXML
    public void txtPhoneOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.PHONE, txtCustomerphone);
    }

    public void PrintBillOnAction(ActionEvent actionEvent) throws SQLException, JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/Reservationbills.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("ReservationID", lablereservationid.getText());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }

    @FXML
    private void sendEmail() {
        String email = lablecustEmail.getText();
        String reservationId = lablereservationid.getText();
        String name = lablecustomerfristname.getText();
        String paymentId = lablenextpaymentid.getText();
        String checkinDate = String.valueOf(checkindatepiker.getValue());
        String checkoutDate = String.valueOf(checkoutdatepiker.getValue());
        String netTotal = lablenettotal.getText();

        String subject = "Booking Confirmed";
        String body = "<html>" +
                "<body style='font-family: Arial, sans-serif;'>" +
                "<div style='max-width: 600px; margin: auto; padding: 20px; border: 1px solid #dcdcdc; border-radius: 10px;'>" +
                "<h2 style='text-align: center; color: #4CAF50;'>Booking Confirmed</h2>" +
                "<p style='font-size: 16px;'>We are pleased to inform you that your reservation request has been received and confirmed. Thank you!</p>" +
                "<div style='border-top: 2px solid #dcdcdc; padding-top: 20px;'>" +
                "<h3 style='text-align: center; color: #333;'>Booking Details:</h3>" +
                "<table style='width: 100%; border-collapse: collapse;'>" +
                "<tr><td style='padding: 8px; border: 1px solid #dcdcdc;'>Reservation ID:</td><td style='padding: 8px; border: 1px solid #dcdcdc;'>" + reservationId + "</td></tr>" +
                "<tr><td style='padding: 8px; border: 1px solid #dcdcdc;'>Name:</td><td style='padding: 8px; border: 1px solid #dcdcdc;'>" + name + "</td></tr>" +
                "<tr><td style='padding: 8px; border: 1px solid #dcdcdc;'>Payment ID:</td><td style='padding: 8px; border: 1px solid #dcdcdc;'>" + paymentId + "</td></tr>" +
                "<tr><td style='padding: 8px; border: 1px solid #dcdcdc;'>Check-in Date:</td><td style='padding: 8px; border: 1px solid #dcdcdc;'>" + checkinDate + "</td></tr>" +
                "<tr><td style='padding: 8px; border: 1px solid #dcdcdc;'>Check-out Date:</td><td style='padding: 8px; border: 1px solid #dcdcdc;'>" + checkoutDate + "</td></tr>" +
                "<tr><td style='padding: 8px; border: 1px solid #dcdcdc;'>Net Total:</td><td style='padding: 8px; border: 1px solid #dcdcdc;'>" + netTotal + "</td></tr>" +
                "</table>" +
                "</div>" +
                "<p style='text-align: center; font-size: 16px; margin-top: 20px;'>Thank you for your booking!</p>" +
                "</div>" +
                "</body>" +
                "</html>";

        sendEmail(email, subject, body);
    }

    private void sendEmail(String to, String subject, String body) {
        String from = "sunmoonresrort@gmail.com"; // Replace with your Gmail address
        String password = "acamrmwuppdpjpyd"; // Replace with your Gmail password
        String host = "smtp.gmail.com"; // Gmail SMTP server

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(body, "text/html");

            Transport.send(message);
            System.out.println("Email sent successfully!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Email sent successfully!");
            alert.showAndWait();
        } catch (MessagingException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Email sent unsuccessfully! Cheak you Made a Reservation");
            alert.showAndWait();
        }
    }

    @FXML
    public void SendEmailOnAction(ActionEvent actionEvent) {
        sendEmail();
    }
}
