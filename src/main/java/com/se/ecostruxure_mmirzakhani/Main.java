package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.be.Config;
import com.se.ecostruxure_mmirzakhani.be.Contract;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.bll.config.ConfigService;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) throws ExceptionHandler {

        // Contract Object
        Contract contract = new Contract();
        contract.setAnnualSalary(60_000.00);
        contract.setHourlyRate(30.00);


        // Employee object
        Employee employee = new Employee();
        employee.setContract(contract);


//        // Gross margin config
//        Config grossMargin = new Config();
//        grossMargin.setName("Gross Margin");
//        grossMargin.setDescription("A config to calculate gross margin rate based on user input.");
//        grossMargin.setOperation("(HourlyRate * 10) / 100");
//        grossMargin.setKey("GrossMargin");
//        grossMargin.setManual(false); // Without user input
//        grossMargin.setVerified(false);


        // Bonus config
        Config bonusConfig = new Config();
        bonusConfig.setName("Bonus Config");
        bonusConfig.setDescription("$100 bonus");
        bonusConfig.setOperation("AnnualSalary + 100");
        bonusConfig.setKey("Bonus");
        bonusConfig.setManual(false);
        bonusConfig.setVerified(false);


        // Apply the bonus config for the employee
        ConfigService configService = new ConfigService();
        double output = configService.applyConfig(employee, bonusConfig);
        System.out.println(output); // 60100.0


        // Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create and show a new stage
        Window.createStage(WindowType.EMPLOYEE_DASHBOARD);
    }
}
