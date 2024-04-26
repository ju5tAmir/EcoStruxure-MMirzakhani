package com.se.ecostruxure_mmirzakhani;

import com.se.ecostruxure_mmirzakhani.be.Country;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Region;
import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {

        Employee employee = new Employee();
        employee.setFirstName("Aria");
        employee.setLastName("Jackson");
        employee.setAnnualSalary(8000.0);
        employee.setAnnualWorkHours(1090);
        employee.setAverageDailyWorkHours(8.1);
        employee.setFixedAnnualAmount(5000);
        employee.setOverheadPercentage(20);
        employee.setUtilizationPercentage(80);
        employee.setMarkupPercentage(10);
        employee.setGrossMarginPercentage(30);
        employee.setOverhead(false);

        employee.setRegion(Region.EUROPE);
        employee.setCountry(Country.SWAZILAND);


        employee.setTeam(new Team("r3k4p1g"));

        try {
            EmployeeDAO dao = new EmployeeDAO();
            System.out.println(dao.createEmployee(employee));
        } catch (ExceptionHandler e){
            System.out.println(e.getMessage());
        }



        Application.launch(args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create and show a new stage
        Window.createStage(WindowType.EMPLOYEE_DASHBOARD);
    }
}
