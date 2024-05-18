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

        History history = model.getEmployeeHistory(new Employee());
        System.out.println(history);

        System.out.println("Contract Changes:" + history.getContracts().size() + " Times.");
        System.out.println("Contract Changes:" + history.getContracts());
        System.out.println("======================");
        System.out.println("Projects Changes:" + history.getProjects().size() + " Times.");
        System.out.println("Projects Changes:" + history.getProjects());


        /*
         * History{contracts=[Contract{id=3, country=null, currency=null, annualSalary=50000.0, fixedAnnualAmount=0.0, annualWorkHours=0.0, averageDailyWorkHours=0.0, overallUtilizationPercentage=0.0, overheadPercentage=0.0, isOverhead=false, validFrom=2020-05-18T19:10:13.107807264, validUntil=2021-05-18T19:10:13.107831921}, Contract{id=0, country=null, currency=null, annualSalary=80000.0, fixedAnnualAmount=0.0, annualWorkHours=0.0, averageDailyWorkHours=0.0, overallUtilizationPercentage=0.0, overheadPercentage=0.0, isOverhead=false, validFrom=2022-05-18T19:10:13.107841222, validUntil=2023-05-18T19:10:13.107848468}, Contract{id=0, country=null, currency=null, annualSalary=99000.0, fixedAnnualAmount=0.0, annualWorkHours=0.0, averageDailyWorkHours=0.0, overallUtilizationPercentage=0.0, overheadPercentage=0.0, isOverhead=false, validFrom=2023-05-18T19:10:13.107855102, validUntil=+999999999-12-31T23:59:59.999999999}], projects=[]}
         * Contract Changes:3 Times.
         * Contract Changes:[Contract{id=3, country=null, currency=null, annualSalary=50000.0, fixedAnnualAmount=0.0, annualWorkHours=0.0, averageDailyWorkHours=0.0, overallUtilizationPercentage=0.0, overheadPercentage=0.0, isOverhead=false, validFrom=2020-05-18T19:10:13.107807264, validUntil=2021-05-18T19:10:13.107831921}, Contract{id=0, country=null, currency=null, annualSalary=80000.0, fixedAnnualAmount=0.0, annualWorkHours=0.0, averageDailyWorkHours=0.0, overallUtilizationPercentage=0.0, overheadPercentage=0.0, isOverhead=false, validFrom=2022-05-18T19:10:13.107841222, validUntil=2023-05-18T19:10:13.107848468}, Contract{id=0, country=null, currency=null, annualSalary=99000.0, fixedAnnualAmount=0.0, annualWorkHours=0.0, averageDailyWorkHours=0.0, overallUtilizationPercentage=0.0, overheadPercentage=0.0, isOverhead=false, validFrom=2023-05-18T19:10:13.107855102, validUntil=+999999999-12-31T23:59:59.999999999}]
         * ======================
         * Projects Changes:0 Times.
         * Projects Changes:[]
         */


//        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create and show a new stage
        Window.createStage(WindowType.MAIN);

    }
}
