package lk.ijse.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.repository.DashboardRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DashboardController {

    public static String UserId;

    @FXML
    private Label dateLabel;

    @FXML
    private Label lableavaroom;

    @FXML
    private Label lablebestpackage;

    @FXML
    private Label lablecheckin;

    @FXML
    private Label lablecheckout;

    @FXML
    private Label lablecustomers;

    @FXML
    private Label lablereservation;

    @FXML
    private Label lablerevenue;

    @FXML
    private Label timeLabel;

    @FXML
    private Label usernameLabel;

    private DashboardRepository repository = new DashboardRepository();

    @FXML
    public void initialize() throws SQLException {
        startDateTimeService();
        setUsername();
        setBestPackage();
        setAvailableRooms();
        setCheckInOutLabels();
        setDashboardStatistics();
    }

    public void refreshDashboard() {
        setBestPackage();
        setAvailableRooms();
        setCheckInOutLabels();
        setDashboardStatistics();
    }

    private void setUsername() {
        try {
            String username = repository.getUsername(UserId);
            if (username != null) {
                Platform.runLater(() -> usernameLabel.setText(username + " ,"));
                DashboardFormController.username = username;
            } else {
                Platform.runLater(() -> usernameLabel.setText("Username not found."));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.runLater(() -> usernameLabel.setText("Database error."));
        }
    }

    private void startDateTimeService() {
        Thread dateTimeThread = new Thread(() -> {
            while (true) {
                LocalDate localDate = LocalDate.now();
                LocalTime localTime = LocalTime.now();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                Platform.runLater(() -> {
                    dateLabel.setText(dateFormatter.format(localDate));
                    timeLabel.setText(timeFormatter.format(localTime));
                    refreshDashboard();
                    checkcheckout();
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        dateTimeThread.setDaemon(true);
        dateTimeThread.start();
    }

    @FXML
    private void setBestPackage() {
        try {
            String bestPackageId = repository.getBestPackage();
            if (bestPackageId != null) {
                Platform.runLater(() -> lablebestpackage.setText(bestPackageId));
            } else {
                Platform.runLater(() -> lablebestpackage.setText("No packages found."));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.runLater(() -> lablebestpackage.setText("Database error."));
        }
    }

    private void setAvailableRooms() {
        try {
            int availableRoomsCount = repository.getAvailableRooms();
            Platform.runLater(() -> lableavaroom.setText(String.valueOf(availableRoomsCount)));
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.runLater(() -> lableavaroom.setText("Database error."));
        }
    }

    private void setCheckInOutLabels() {
        try {
            int checkInCount = repository.getCheckInCount();
            Platform.runLater(() -> lablecheckin.setText(String.valueOf(checkInCount)));

            int checkOutCount = repository.getCheckOutCount();
            Platform.runLater(() -> lablecheckout.setText(String.valueOf(checkOutCount)));
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                lablecheckin.setText("Database error.");
                lablecheckout.setText("Database error.");
            });
        }
    }

    private void setDashboardStatistics() {
        try {
            int totalReservations = repository.getTotalReservations();
            Platform.runLater(() -> lablereservation.setText(String.valueOf(totalReservations)));

            int customerCount = repository.getCustomerCount();
            Platform.runLater(() -> lablecustomers.setText(String.valueOf(customerCount)));

            double totalRevenueUSD = repository.getTotalRevenue() / 320;
            Platform.runLater(() -> lablerevenue.setText(String.format("$%.2f", totalRevenueUSD)));
        } catch (SQLException e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                lablereservation.setText("DB error");
                lablecustomers.setText("DB error");
                lablerevenue.setText("DB error");
            });
        }
    }

    private void showAlert(Alert.AlertType alertType, String content) {
        Alert alert = new Alert(alertType);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void checkcheckout() {
        try {
            // Ensure dateLabel contains the date in "yyyy-MM-dd" format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.now(); // Assuming you want to check the current date
            Date date = Date.valueOf(localDate.format(formatter));
            check(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void check(Date date) {
        try {
            List<String> roomIds = repository.getRoomIdsByCheckoutDate(date);
            for (String roomId : roomIds) {
                repository.updateRoomStatusToAvailable(roomId);
            }
            setAvailableRooms(); // Refresh the available rooms count
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
