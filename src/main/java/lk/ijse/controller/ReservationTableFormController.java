package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.db.DbConnection;
import lk.ijse.model.Reservation;
import lk.ijse.model.TableReservation;
import lk.ijse.repository.ReservationRepo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReservationTableFormController {

    @FXML
    private TableView<Reservation> tablereservations;

    @FXML
    private TableColumn<Reservation, String> colReservationID;

    @FXML
    private TableColumn<Reservation, Date> colCheckInDate;

    @FXML
    private TableColumn<Reservation, Date> colCheckOutDate;

    @FXML
    private TableColumn<Reservation, Date> colReservationDate;

    @FXML
    private TableColumn<Reservation, String> colDuration;

    @FXML
    private TableColumn<Reservation, Integer> colNumberofGuests;

    @FXML
    private TableColumn<Reservation, String> colStatus;

    @FXML
    public void initialize() {
        setCellValueFactory();
        loadTableData();
    }

    private void setCellValueFactory() {
        colReservationID.setCellValueFactory(new PropertyValueFactory<>("ReservationID"));
        colCheckInDate.setCellValueFactory(new PropertyValueFactory<>("CheckInDate"));
        colCheckOutDate.setCellValueFactory(new PropertyValueFactory<>("CheckOutDate"));
        colReservationDate.setCellValueFactory(new PropertyValueFactory<>("ReservationDate"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        colNumberofGuests.setCellValueFactory(new PropertyValueFactory<>("NumberofGuests"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
    }

    private void loadTableData() {
        tablereservations.setItems(ReservationRepo.getAllReservations());
    }
}
