package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Currency;
import com.se.ecostruxure_mmirzakhani.be.Project;
import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;

import java.util.List;

public class TeamService {
    private EmployeeService employeeService = new EmployeeService();
    private Currency systemCurrency;

    public TeamService(){

    }
    public TeamService(Currency systemCurrency){
        this.systemCurrency = systemCurrency;
        employeeService.setCurrency(systemCurrency);
    }


    /**
     * Calculate and return the total cost of employees for a team.
     * @param projects A list of Project objects, each containing an associated employee, and all of the projects are related to a team.
     * @return The total cost for all employees across the team.
     */
    public double getTotalCost(List<Project> projects) {
        // ToDO: Incorrect Calculations
        //      : Move calculations to EmployeeService
        // Initialize the total cost to 0
        double totalCost = 0;

        // Iterate through each project in the provided team
        for (Project project : projects) {
            double employeeTotalCost = employeeService.getTotalCost(project.getEmployee());
            double employeeUtilizationPercentage = project.getUtilizationPercentage() / 100;

            totalCost += employeeTotalCost * employeeUtilizationPercentage;
        }

        // Return the calculated total cost
        return totalCost;
    }


    public double getHourlyRate(List<Project> projects){
        // Initialize the total rate to accumulate the hourly rates of all employees
        double totalRate = 0;

        // Iterate through each project to calculate the hourly rate for the employee assigned to the project
        for (Project project : projects) {
            double employeeRate = employeeService.getHourlyRate(project);

            // Accumulate the employee's hourly rate into the total rate
            totalRate += employeeRate;
        }

        // Return the calculated total cost
        return totalRate;
    }

    public double getDailyRate(List<Project> projects) {
        // Initialize the total rate to accumulate the daily rates of all employees
        double totalRate = 0;

        // Iterate through each project to calculate the daily rate for the employee assigned to the project
        for (Project project : projects) {
            double employeeRate = employeeService.getDailyRate(project);

            // Accumulate the employee's daily rate into the total rate
            totalRate += employeeRate;

        }
        return totalRate;
    }
}
