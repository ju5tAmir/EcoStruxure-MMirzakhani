package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.bll.project.ProjectService;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.Window;
import com.se.ecostruxure_mmirzakhani.utils.WindowType;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {

        Model m = new Model();
        try {
            System.out.println(m.getAllProjects());
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }


        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        // Create and show a new stage
        try {
            Window.createStage(WindowType.MAIN);
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }
}
