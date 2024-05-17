package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.be.*;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends Application {
    // ToDo : Fix the Region and Country handling part in DAO
    //  : Validate in the front-end
    //  : Autofill and auto-suggest and typo detector based expected average value from DB for some fields
    //  : Lazy loading for team employees and caching

    public static void main(String[] args) throws ExceptionHandler {


        HashMap<Team, List<Employee>> teamEmployees = new HashMap<>();

        Team it = new Team(1, "IT");
        Team hr = new Team(2, "HR");

        Employee e1 = new Employee();

        Contract c1 = new Contract();
        c1.setAnnualSalary(60000);
        c1.setAnnualWorkHours(2090);

        e1.setContract(c1);

        Project p1 = new Project(it, 20);
        Project p2 = new Project(hr, 40);

        e1.addProject(p1);
        e1.addProject(p2);

        // ***** 2 *****
        Employee e2 = new Employee();
        Contract c2 = new Contract();
        c2.setAnnualWorkHours(2000);
        c2.setAnnualSalary(200000);
        e2.setContract(c2);

        Project p3 = new Project(it, 80);
        e2.addProject(p3);

        List<Employee> itEmployees = new ArrayList<>();
        itEmployees.add(e1);
        itEmployees.add(e2);

        List<Employee> hrEmployees = new ArrayList<>();
        hrEmployees.add(e1);

        teamEmployees.put(it, itEmployees);
        teamEmployees.put(hr, hrEmployees);

        for (Team team: teamEmployees.keySet()){
            System.out.println("Team: " + team + ": ");
            for (Employee e: teamEmployees.get(team)){
                System.out.println(e );
            }
            System.out.println("\n");
        }


        /* Output
         *
         * Team: Team{id=2, name='HR'}:
         * Employee{contract=Contract{id=0, country=null, currency=null, annualSalary=60000.0, fixedAnnualAmount=0.0, annualWorkHours=2090.0, averageDailyWorkHours=0.0, overallUtilizationPercentage=0.0, overheadPercentage=0.0, isOverhead=false, validFrom=null, validUntil=null}, projects=[Project{id=0, team=Team{id=1, name='IT'}, utilizationPercentage=20.0}, Project{id=0, team=Team{id=2, name='HR'}, utilizationPercentage=40.0}]}
         *
         * Team: Team{id=1, name='IT'}:
         * Employee{contract=Contract{id=0, country=null, currency=null, annualSalary=60000.0, fixedAnnualAmount=0.0, annualWorkHours=2090.0, averageDailyWorkHours=0.0, overallUtilizationPercentage=0.0, overheadPercentage=0.0, isOverhead=false, validFrom=null, validUntil=null}, projects=[Project{id=0, team=Team{id=1, name='IT'}, utilizationPercentage=20.0}, Project{id=0, team=Team{id=2, name='HR'}, utilizationPercentage=40.0}]}
         * Employee{contract=Contract{id=0, country=null, currency=null, annualSalary=200000.0, fixedAnnualAmount=0.0, annualWorkHours=2000.0, averageDailyWorkHours=0.0, overallUtilizationPercentage=0.0, overheadPercentage=0.0, isOverhead=false, validFrom=null, validUntil=null}, projects=[Project{id=0, team=Team{id=1, name='IT'}, utilizationPercentage=80.0}]}
         */







//        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create and show a new stage
        Window.createStage(WindowType.MAIN);

    }
}
