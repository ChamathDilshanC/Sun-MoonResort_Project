package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        // set login form as the initial scene
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/Loadningpage.fxml"));
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.setTitle("Sun & Moon Resort Login Form");
        primaryStage.centerOnScreen();
        primaryStage.show();
        primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                primaryStage.setMaximized(false);
            }
        });

    }
}
