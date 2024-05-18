package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Country;
import com.se.ecostruxure_mmirzakhani.be.Currency;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Project;

import java.util.List;

public class CountryService {
    private Currency systemCurrency;
    private EmployeeService employeeService;
    public CountryService(Currency systemCurrency) {
        this.systemCurrency = systemCurrency;
        this.employeeService = new EmployeeService(systemCurrency);
    }

    public double getHourlyRateForCountry(Country country, List<Project> projects){
        double totalRate = 0;

        for (Project project: projects){
            // Select all the projects for the given country
            if (project.getEmployee().getContract().getCountry().equals(country)) {
                double employeeTotalCost            = employeeService.getTotalCost(project.getEmployee());
                double employeeAnnualWorkHours      = project.getEmployee().getContract().getAnnualWorkHours();
                double projectUtilizationPercentage = project.getUtilizationPercentage() / 100;
                double employeeEffectiveWorkHours   = employeeAnnualWorkHours * projectUtilizationPercentage;

                double employeeHourlyRate           = employeeTotalCost / employeeEffectiveWorkHours;

                totalRate += employeeHourlyRate;
            }
        }
        return totalRate;
    }


    public double getDailyRateForCountry(Country country, List<Project> projects){
        double rate = 0;

        for (Project project: projects){
            // Select all the projects for the given country
            if (project.getEmployee().getContract().getCountry().equals(country)) {
                double employeeTotalCost                = employeeService.getTotalCost(project.getEmployee());
                double employeeAnnualWorkHours          = project.getEmployee().getContract().getAnnualWorkHours();
                double employeeAverageDailyWorkHours    = project.getEmployee().getContract().getAverageDailyWorkHours();
                double projectUtilizationPercentage     = project.getUtilizationPercentage() / 100;
                double employeeEffectiveWorkHours       = employeeAnnualWorkHours * projectUtilizationPercentage ;

                double employeeRateHourlyRate           = employeeTotalCost / employeeEffectiveWorkHours;
                double employeeDailyRate                = employeeRateHourlyRate * employeeAverageDailyWorkHours;

                rate += employeeDailyRate;
            }
        }
        return rate;
    }
}
