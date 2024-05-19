package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.be.*;
import com.se.ecostruxure_mmirzakhani.bll.EmployeeService;
import com.se.ecostruxure_mmirzakhani.bll.HistoryService;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.model.Model;

import com.se.ecostruxure_mmirzakhani.utils.ObjectService;
import com.se.ecostruxure_mmirzakhani.utils.Validate;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;

import javafx.application.Application;
import javafx.stage.Stage;

import java.lang.reflect.Field;
import java.time.LocalDateTime;


public class Main extends Application {
    // ToDo : Fix the Region and Country handling part in DAO
    //  : GUI Logic class
    //  : Validate in the front-end
    //  : Autofill and auto-suggest and typo detector based expected average value from DB for some fields
    //  : Lazy loading for team employees and caching
    //  : Observer pattern for currency system. so when it's changed in model, it changes in all the classes.

    public static void main(String[] args) throws ExceptionHandler {
        Model model = new Model();

        Contract contract1 = new Contract();
        contract1.setId(1);
        contract1.setCountry(Country.DENMARK);
        contract1.setCurrency(Currency.USD);
        contract1.setAnnualSalary(80_000);     // 80K Salary
        contract1.setFixedAnnualAmount(5_000);
        contract1.setAnnualWorkHours(2000);
        contract1.setAverageDailyWorkHours(8);
        contract1.setOverhead(true);
        contract1.setOverheadPercentage(20);
        contract1.setTimeLine(new TimeLine(LocalDateTime.now().minusYears(1), LocalDateTime.now()));

        Contract contract2 = new Contract();
        contract2.setId(1);
        contract2.setCountry(Country.DENMARK);
        contract2.setCurrency(Currency.USD);
        contract2.setAnnualSalary(90_000);       // Salary increased from 80K to 90K
        contract2.setFixedAnnualAmount(5_000);
        contract2.setAnnualWorkHours(2000);
        contract2.setAverageDailyWorkHours(10);  // Changed from 8 to 10
        contract2.setOverhead(true);
        contract2.setOverheadPercentage(20);
        contract2.setTimeLine(new TimeLine(LocalDateTime.now(), LocalDateTime.MAX));

        ;

        for (Change change: ObjectService.compare(contract1, contract2)){
            System.out.println(change);
        }

        /*
         * Change{object='Contract', property='averageDailyWorkHours', previousState=8.0, currentState=10.0, changeState=CHANGED}
         * Change{object='Contract', property='annualSalary', previousState=80000.0, currentState=90000.0, changeState=CHANGED}
         * Change{object='TimeLine', property='validFrom', previousState=2023-05-19T17:15:22.336772684, currentState=2024-05-19T17:15:22.336799406, changeState=CHANGED}
         * Change{object='TimeLine', property='validUntil', previousState=2024-05-19T17:15:22.336790276, currentState=+999999999-12-31T23:59:59.999999999, changeState=CHANGED}
         */


//        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create and show a new stage
        Window.createStage(WindowType.MAIN);

    }
}
