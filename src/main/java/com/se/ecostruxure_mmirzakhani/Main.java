package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) throws ExceptionHandler {

        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create and show a new stage
        Window.createStage(WindowType.EMPLOYEE_DASHBOARD);
    }
}
