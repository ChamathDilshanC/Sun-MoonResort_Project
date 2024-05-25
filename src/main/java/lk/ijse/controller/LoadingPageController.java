package lk.ijse.controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.stage.Window;
import java.io.IOException;

public class LoadingPageController {

    public void initialize(){
        // Create a PauseTransition that waits for 3 seconds
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> loadLoginForm(event));
        delay.play();
    }


    public void loadLoginForm(ActionEvent event) {
        try {
            Parent loginForm = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
            Scene loginScene = new Scene(loginForm);
            Stage currentStage;

            if (event != null && event.getSource() instanceof Node) {
                currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            } else {
                // As a fallback, use the primary stage
                currentStage = (Stage) Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
            }

            if (currentStage != null) {
                currentStage.setScene(loginScene);
                currentStage.show();
            } else {
                System.err.println("Error: No suitable stage found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
