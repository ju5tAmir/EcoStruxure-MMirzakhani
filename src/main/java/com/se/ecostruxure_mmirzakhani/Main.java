package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.utils.AlertHandler;
import com.se.ecostruxure_mmirzakhani.utils.Window;
import com.se.ecostruxure_mmirzakhani.utils.WindowType;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {

        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) {


        // Create and show a new stage
        try {
            Window.createStage(WindowType.HISTORY);
        } catch (ExceptionHandler e) {
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
