package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.be.entities.Multiplier;
import com.se.ecostruxure_mmirzakhani.be.entities.Project;
import com.se.ecostruxure_mmirzakhani.be.enums.MultiplierType;
import com.se.ecostruxure_mmirzakhani.dal.multiplier.MultiplierDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.utils.Window;
import com.se.ecostruxure_mmirzakhani.utils.WindowType;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        // ToDo: remember to edit ProjectDAO in delete method to include Multipliers delete query as well.

        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws ExceptionHandler {

        // Create and show a new stage
        Window.createStage(WindowType.MAIN);
    }
}
