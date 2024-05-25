package lk.ijse.controller;

import com.gluonhq.charm.glisten.control.TextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.db.DbConnection;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class LoginFormController {
    @FXML
    private PasswordField TxtPassword;

    @FXML
    private TextField TxtUserId;

    @FXML
    private void initialize() {
        TxtUserId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                TxtPassword.requestFocus();
                event.consume();
            }
        });
        TxtPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                LoginOnAction(new ActionEvent());
                event.consume();
            }
        });
    }

    @FXML
    void ForgetPasswordOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/ForgetPasswordForm.fxml"));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(rootNode);
        Stage stage = (Stage) scene.getWindow();
        stage.setTitle("Sun & Moon Resort Forget Password Form");
        stage.show();
    }

    @FXML
    void LoginOnAction(ActionEvent event) {
        String userId = TxtUserId.getText();
        String password = TxtPassword.getText();
        DashboardController.UserId = userId;
        DashboardFormController.UserId = userId;

        Connection conn = null;
        PreparedStatement selectStmt = null;
        ResultSet rs = null;

        try {
            conn = DbConnection.getInstance().getConnection();
            String selectUserIdSql = "SELECT * FROM user WHERE UserID = ?";
            selectStmt = conn.prepareStatement(selectUserIdSql);
            selectStmt.setString(1, userId);

            rs = selectStmt.executeQuery();

            if (rs.next()) {
                if (rs.getString("Password").equals(password)) {
                    updateLoginTimestamp(userId, conn);
                    navigateToTheDashboard();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Incorrect password. Please try again.");
                    TxtPassword.setText("");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "User ID does not exist. Please try again or sign up.");
                TxtUserId.setText("");
                TxtPassword.setText("");
            }
        } catch (SQLException | IOException e) {
            showAlert(Alert.AlertType.ERROR, "Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (selectStmt != null) selectStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateLoginTimestamp(String userId, Connection conn) throws SQLException {
        String updateSql = "UPDATE user SET LastLoginDate = ?, LastLoginTime = ?, IsActive = 1 WHERE UserID = ?";
        try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            updateStmt.setDate(1, Date.valueOf(LocalDate.now()));
            updateStmt.setTime(2, Time.valueOf(LocalTime.now()));
            updateStmt.setString(3, userId);

            updateStmt.executeUpdate();
        }
    }

    private void showAlert(Alert.AlertType alertType, String content) {
        Alert alert = new Alert(alertType);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void SignUpOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/SignupForm.fxml"));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(rootNode);
        Stage stage = (Stage) scene.getWindow();
        stage.setTitle("Sun & Moon Resort SignUp Form");
        stage.show();
    }

    private void navigateToTheDashboard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashboardForm.fxml"));

        if (loader.getLocation() == null) {
            throw new IllegalStateException("Dashboard FXML file not found");
        }
        Parent rootNode = loader.load();
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) TxtUserId.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Sun & Moon Resort Dashboard");
        stage.show();
        stage.centerOnScreen();
    }
}
