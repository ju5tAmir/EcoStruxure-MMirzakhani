package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Project;
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
            System.out.println(t.getName() + " " + model.getTeamProjects().get(t).size());
            System.out.println(model.getTeamProjects().get(t));
        }

        /*
         * IT 2
         * [Project{id=0, employee=Employee{contract=Contract{id=1, country=Denmark, currency=DKK, annualSalary=400000.0, fixedAnnualAmount=20000.0, annualWorkHours=1920.0, averageDailyWorkHours=8.0, overallUtilizationPercentage=0.0, overheadPercentage=20.0, isOverhead=true, validFrom=null, validUntil=null}}, team=Team{id=1, name='IT'}, utilizationPercentage=20.0}, Project{id=0, employee=Employee{contract=Contract{id=2, country=Sweden, currency=EUR, annualSalary=50000.0, fixedAnnualAmount=2000.0, annualWorkHours=1920.0, averageDailyWorkHours=8.0, overallUtilizationPercentage=0.0, overheadPercentage=20.0, isOverhead=true, validFrom=null, validUntil=null}}, team=Team{id=1, name='IT'}, utilizationPercentage=80.0}]
         * HR 1
         * [Project{id=0, employee=Employee{contract=Contract{id=1, country=Denmark, currency=DKK, annualSalary=400000.0, fixedAnnualAmount=20000.0, annualWorkHours=1920.0, averageDailyWorkHours=8.0, overallUtilizationPercentage=0.0, overheadPercentage=20.0, isOverhead=true, validFrom=null, validUntil=null}}, team=Team{id=2, name='HR'}, utilizationPercentage=40.0}]
         */

//        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create and show a new stage
        Window.createStage(WindowType.MAIN);

    }
}
