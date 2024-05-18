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

        Contract contract2 = new Contract();
        contract2.setId(1);
        contract2.setCountry(Country.DENMARK);
        contract2.setCurrency(Currency.USD);
        contract2.setAnnualSalary(90_000);       // Salary increased from 80K to 90K
        contract2.setFixedAnnualAmount(5_000);
        contract2.setAnnualWorkHours(2000);
        contract2.setAverageDailyWorkHours(8);
        contract2.setOverhead(true);
        contract2.setOverheadPercentage(20);

        ObjectService.compare(contract1, contract2);

        /*
         * [+] id: 1 -> 1
         * [+] country: Denmark -> Denmark
         * [+] currency: USD -> USD
         * [+] annualSalary: 80000.0 -> 90000.0         ** Change Detected **
         * [+] fixedAnnualAmount: 5000.0 -> 5000.0
         * [+] annualWorkHours: 2000.0 -> 2000.0
         * [+] averageDailyWorkHours: 8.0 -> 8.0
         * [+] overallUtilizationPercentage: 0.0 -> 0.0
         * [+] overheadPercentage: 20.0 -> 20.0
         * [+] isOverhead: true -> true
         * [+] timeLine: null -> null
         */
//        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create and show a new stage
        Window.createStage(WindowType.MAIN);

    }
}
