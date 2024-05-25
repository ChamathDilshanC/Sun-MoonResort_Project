package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardFormController {

    public static String UserId;
    public static String username;
    public JFXButton btnpackages;
    public JFXButton btnlogout;
    public JFXButton byndashboard;
    public JFXButton btnlogo;
    public Label lableusername;
    public JFXButton reservationbutton;
    @FXML
    private AnchorPane FormAnchorpane;

    @FXML
    private JFXButton btnCustomers;

    @FXML
    private JFXButton btnReservations;

    @FXML
    private Label labelEmpid;

    @FXML
    private Label labelemppossition;

    @FXML
    private Label lablephone;

    @FXML
    private AnchorPane paneSlide;

    @FXML
    public void initialize() throws SQLException {
        //load the Dashboard Form to the FormAnchorpane
        dashboardOnAction();
        setEmployeeDetails();
        lableusername.setText(username);
    }

    private void dashboardOnAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Dashboard.fxml"));  // Update the path as necessary
            Parent reservationForm = loader.load();
            FormAnchorpane.getChildren().setAll(reservationForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onRefreshButtonClicked(ActionEvent event) {
        refreshDashboard();
    }

    public void refreshDashboard() {
        setEmployeeDetails();
    }

    // New method to click the reservation button
    public void clickReservationButton() {
        reservationbutton.fire();
    }


    @FXML
    void logoutOnAction(ActionEvent event) {
        try {
            // Show confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            // Get the response value
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        Stage currentStage = (Stage) labelEmpid.getScene().getWindow();
                        currentStage.close();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginForm.fxml"));
                        Parent rootNode = loader.load();
                        Scene scene = new Scene(rootNode);
                        Stage loginStage = new Stage();
                        loginStage.setScene(scene);
                        loginStage.setTitle("Sun & Moon Resort Login Form");
                        loginStage.show();
                        loginStage.centerOnScreen();
                        loginStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
                            if (newValue) {
                                loginStage.setMaximized(false);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                        showAlert(Alert.AlertType.ERROR, "Failed to load the login form: " + e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String content) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void setEmployeeDetails() {
        String sql = "SELECT e.EmployeeID, e.Position, e.Phone FROM user u JOIN employee e ON u.EmployeeID = e.EmployeeID WHERE u.UserId = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, UserId); // Assuming UserId contains the ID from the user table
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                String empId = resultSet.getString("EmployeeID");
                String position = resultSet.getString("Position");
                String phone = resultSet.getString("Phone");
                // Update the labels on the JavaFX Application Thread
                Platform.runLater(() -> {
                    labelEmpid.setText(empId);
                    labelemppossition.setText(position);
                    lablephone.setText(phone);
                });
            } else {
                Platform.runLater(() -> {
                    labelEmpid.setText("ID not found");
                    labelemppossition.setText("Position not found");
                    lablephone.setText("Phone not found");
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Platform.runLater(() -> {
                labelEmpid.setText("Database error");
                labelemppossition.setText("Database error");
                lablephone.setText("Database error");
            });
        }
    }


    @FXML
    void customerOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerForm.fxml"));  // Update the path as necessary
            Parent reservationForm = loader.load();
            FormAnchorpane.getChildren().setAll(reservationForm);
            //set formnmames as customer
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void dashboardOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Dashboard.fxml"));  // Update the path as necessary
            Parent reservationForm = loader.load();
            FormAnchorpane.getChildren().setAll(reservationForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void employeesOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EmployeeForm.fxml"));  // Update the path as necessary
            Parent reservationForm = loader.load();
            FormAnchorpane.getChildren().setAll(reservationForm);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void orderMealOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OrderMeal.fxml"));  // Update the path as necessary
            Parent reservationForm = loader.load();
            FormAnchorpane.getChildren().setAll(reservationForm);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void reservationOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ReservationForm.fxml"));  // Update the path as necessary
            Parent reservationForm = loader.load();
            FormAnchorpane.getChildren().setAll(reservationForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void supplierOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SupplierForm.fxml"));  // Update the path as necessary
            Parent reservationForm = loader.load();
            FormAnchorpane.getChildren().setAll(reservationForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnReservationsOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ReservationTableForm.fxml"));  // Update the path as necessary
            Parent reservationForm = loader.load();
            FormAnchorpane.getChildren().setAll(reservationForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnpackagesOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PackageTableForm.fxml"));  // Update the path as necessary
            Parent reservationForm = loader.load();
            FormAnchorpane.getChildren().setAll(reservationForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnCustomersOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerTableForm.fxml"));  // Update the path as necessary
            Parent reservationForm = loader.load();
            FormAnchorpane.getChildren().setAll(reservationForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSuppliersOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SupplierTableForm.fxml"));  // Update the path as necessary
            Parent reservationForm = loader.load();
            FormAnchorpane.getChildren().setAll(reservationForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void InventoryOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InventoryForm.fxml"));  // Update the path as necessary
            Parent reservationForm = loader.load();
            FormAnchorpane.getChildren().setAll(reservationForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ServiceOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EmployeeServiceForm.fxml"));  // Update the path as necessary
            Parent reservationForm = loader.load();
            FormAnchorpane.getChildren().setAll(reservationForm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnNewEventOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EventForm.fxml"));  // Update the path as necessary
            Parent reservationForm = loader.load();
            FormAnchorpane.getChildren().setAll(reservationForm);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
