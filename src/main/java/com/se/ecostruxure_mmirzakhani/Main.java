package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import com.se.ecostruxure_mmirzakhani.utils.Window;
import com.se.ecostruxure_mmirzakhani.utils.WindowType;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {

        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws ExceptionHandler {

        // Create and show a new stage
        Window.createStage(WindowType.MAIN);
    }
}
