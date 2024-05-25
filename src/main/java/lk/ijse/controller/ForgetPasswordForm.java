package lk.ijse.controller;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.Util.TextField;
import lk.ijse.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgetPasswordForm {

    @FXML
    private JFXTextField TxtUserId, TxtUsername, TxtNewPassword;

    @FXML
    void resetbtnOnAction(ActionEvent event) throws SQLException {
        String userId = TxtUserId.getText();
        String username = TxtUsername.getText();
        String newPassword = TxtNewPassword.getText();

        if (userId.isEmpty() || username.isEmpty() || newPassword.isEmpty()) {
            showAlert("All fields are required.");
            return;
        }

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            String checkUserSql = "SELECT * FROM user WHERE UserID = ? AND Username = ?";
            PreparedStatement checkUserStmt = conn.prepareStatement(checkUserSql);
            checkUserStmt.setString(1, userId);
            checkUserStmt.setString(2, username);

            ResultSet rs = checkUserStmt.executeQuery();

            if (rs.next()) {

                String updatePasswordSql = "UPDATE user SET Password = ? WHERE UserID = ? AND Username = ?";
                PreparedStatement updatePasswordStmt = conn.prepareStatement(updatePasswordSql);
                updatePasswordStmt.setString(1, newPassword);
                updatePasswordStmt.setString(2, userId);
                updatePasswordStmt.setString(3, username);

                int rowsUpdated = updatePasswordStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    showAlert("Password has been reset successfully.");
                    clearTextFields();

                } else {
                    showAlert("Failed to reset the password. Please try again.");
                }

            } else {
                showAlert("User ID or Username is incorrect.");
            }

        } catch (SQLException e) {
            showAlert("A database error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    @FXML
    private void clearTextFields() {
        TxtUserId.setText("");
        TxtUsername.setText("");
        TxtNewPassword.setText("");
    }
    @FXML
    public void txtUserNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.NAME, TxtUsername);
    }
    @FXML
    public void txtUserIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.UserID, TxtUserId);
    }
    @FXML
    public void txtPasswordOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextField.PASSWORD, TxtNewPassword);
    }

    @FXML
    public void BackbtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/LoginForm.fxml"));
        Scene scene = ((Node) actionEvent.getSource()).getScene();
        scene.setRoot(rootNode);
        Stage stage = (Stage) scene.getWindow();
        stage.setTitle("Sun & Moon Resort Login Form");
        stage.show();
    }

}
