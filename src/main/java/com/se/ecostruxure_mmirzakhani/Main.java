package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.bll.project.ProjectService;
import com.se.ecostruxure_mmirzakhani.dal.assignment.AssignmentDAO;
import com.se.ecostruxure_mmirzakhani.dal.employee.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import com.se.ecostruxure_mmirzakhani.gui.gui_utils.GUIHelper;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.AlertHandler;
import com.se.ecostruxure_mmirzakhani.utils.Window;
import com.se.ecostruxure_mmirzakhani.utils.WindowType;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {


//
//        Model model = new Model();
//        try {
//            System.out.println(model.getAllEmployees());
//        } catch (ExceptionHandler e) {
//            throw new RuntimeException(e);
//        }

//        model.getTotalHourlyRate();
//        model.getTotalDailyRate();
//        model.getTotalCosts();

        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        // Create and show a new stage
        try {
            Window.createStage(WindowType.MAIN);
        } catch (ExceptionHandler e) {
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
