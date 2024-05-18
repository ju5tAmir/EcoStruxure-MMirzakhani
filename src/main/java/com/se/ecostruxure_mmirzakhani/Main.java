package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.be.*;
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
    //  : Observer pattern for currency system. so when it's changed in model, it changes in all the classes.

    public static void main(String[] args) throws ExceptionHandler {
        Model model = new Model();

        System.out.println("Country     " + Country.DENMARK);
        System.out.println("HourlyRate: " + model.getHourlyRate(Country.DENMARK));
        System.out.println("Daily Rate: " + model.getDailyRate(Country.DENMARK));
        System.out.println("==========================");
        System.out.println("Country     " + Country.SWEDEN);
        System.out.println("HourlyRate: " + model.getHourlyRate(Country.SWEDEN));
        System.out.println("Daily Rate: " + model.getDailyRate(Country.SWEDEN));
        System.out.println("==========================");
        System.out.println("Country     " + Country.NORTH_KOREA);
        System.out.println("HourlyRate: " + model.getHourlyRate(Country.NORTH_KOREA));
        System.out.println("Daily Rate: " + model.getDailyRate(Country.NORTH_KOREA));

        /*
         * Country     Denmark
         * HourlyRate: 208.8767397825
         * Daily Rate: 1671.01391826
         * ==========================
         * Country     Sweden
         * HourlyRate: 40.364583333333336
         * Daily Rate: 322.9166666666667
         * ==========================
         * Country     North Korea
         * HourlyRate: 0.0
         * Daily Rate: 0.0
         */


//        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create and show a new stage
        Window.createStage(WindowType.MAIN);

    }
}
