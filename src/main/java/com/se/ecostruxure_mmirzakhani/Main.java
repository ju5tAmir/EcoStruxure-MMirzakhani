package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.model.Model;

import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    // ToDo : Fix the Region and Country handling part in DAO
    //  : GUI Logic class
    //  : Validate in the front-end
    //  : Autofill and auto-suggest and typo detector based expected average value from DB for some fields
    //  : Lazy loading for team employees and caching

    public static void main(String[] args) throws ExceptionHandler {
        Model model = new Model();

        for (Team t : model.getTeamProjects().keySet()) {
            System.out.println(t.getName());
            System.out.println("Hourly  Rate: " + model.getHourlyRate(t));
            System.out.println("Daily   Rate: " + model.getDailyRate(t));
        }

        /*
         * IT
         * Hourly  Rate: 133.19868990333333
         * Daily   Rate: 1065.5895192266667
         * HR
         * Hourly  Rate: 116.04263321250001
         * Daily   Rate: 928.3410657000001
         */


//        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create and show a new stage
        Window.createStage(WindowType.MAIN);

    }
}
