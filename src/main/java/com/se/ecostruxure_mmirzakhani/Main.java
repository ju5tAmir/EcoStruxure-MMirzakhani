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

    public static void main(String[] args) throws ExceptionHandler {
        Model model = new Model();


        Employee e = model.getRandomEmployee();
        System.out.println(e);
        System.out.println(model.getEmployeeProjects(e));
        System.out.println("Util %: " + model.getTotalUtilizationPercentage(e));


        /* Working as expected.
         * Employee{contract=Contract{id=1, country=Denmark, currency=USD, annualSalary=80000.0, fixedAnnualAmount=5000.0, annualWorkHours=2000.0, averageDailyWorkHours=8.0, overallUtilizationPercentage=0.0, overheadPercentage=20.0, isOverhead=true, validFrom=null, validUntil=null}}
         * [Project{id=0, employee=Employee{contract=Contract{id=1, country=Denmark, currency=USD, annualSalary=80000.0, fixedAnnualAmount=5000.0, annualWorkHours=2000.0, averageDailyWorkHours=8.0, overallUtilizationPercentage=0.0, overheadPercentage=20.0, isOverhead=true, validFrom=null, validUntil=null}}, team=Team{id=1, name='IT'}, utilizationPercentage=50.0}, Project{id=0, employee=Employee{contract=Contract{id=1, country=Denmark, currency=USD, annualSalary=80000.0, fixedAnnualAmount=5000.0, annualWorkHours=2000.0, averageDailyWorkHours=8.0, overallUtilizationPercentage=0.0, overheadPercentage=20.0, isOverhead=true, validFrom=null, validUntil=null}}, team=Team{id=2, name='HR'}, utilizationPercentage=40.0}]
         * Util %: 90.0
         */


//        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create and show a new stage
        Window.createStage(WindowType.MAIN);

    }
}
