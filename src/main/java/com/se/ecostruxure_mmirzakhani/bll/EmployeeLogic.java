package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Contract;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.util.List;

public class EmployeeLogic {
    private final EmployeeDAO dao;

    {
        try {
            dao = new EmployeeDAO();
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor
     */
    public EmployeeLogic(){

    }

    /**
     * Retrieve all the employees from the database with their latest contract (without history)
     * @return List of employees
     * @throws ExceptionHandler If any error occurred.
     */
    public List<Employee> getAllEmployees() throws ExceptionHandler{
        return dao.getAllEmployees();
    }

    // ToDo: Implement methods to calculate rates

    public double calculateHourlyRate(Employee employee) throws ExceptionHandler {

        Contract contract = employee.getContract();

        double annualSalary = contract.getAnnualSalary();
        double fixedAnnualAmount = contract.getFixedAnnualAmount();
        double annualWorkHours = contract.getAnnualWorkHours();
        double utilizationPercentage = contract.getUtilizationPercentage();
        double overheadMultiplier = 1 + (contract.getOverheadPercentage() / 100);

        double effectiveAnnualWorkHours = annualWorkHours * (utilizationPercentage / 100);

        double adjustedAnnualSalary = annualSalary + fixedAnnualAmount;

        double adjustedAnnualSalaryWithOverhead = adjustedAnnualSalary * overheadMultiplier;

        double hourlyRate = adjustedAnnualSalaryWithOverhead / effectiveAnnualWorkHours;

        return hourlyRate;
    }

    public double calculateDailyRate(Employee employee) throws ExceptionHandler {

        Contract contract = employee.getContract();

        double hourlyRate = calculateHourlyRate(employee);

        double averageDailyWorkHours = contract.getAverageDailyWorkHours();

        double dailyRate = hourlyRate * averageDailyWorkHours;

        return dailyRate;
    }


    public double hourlyRateMarkup(double hourlyRate, double markupPercentage) {
        double markupMultiplier = 1 + (markupPercentage / 100);
        return hourlyRate * markupMultiplier;
    }

    public double dailyRateMarkup(double dailyRate, double markupPercentage) {
        double markupMultiplier = 1 + (markupPercentage / 100);
        return dailyRate * markupMultiplier;
    }

    public double hourlyRateGM(double baseHourlyRate, double gmPercentage) {
        double gmMultiplier = 1 + (gmPercentage / 100);
        return baseHourlyRate * gmMultiplier;
    }

    public double dailyRateGM(double baseDailyRate, double gmPercentage) {
        double gmMultiplier = 1 + (gmPercentage / 100);
        return baseDailyRate * gmMultiplier;
    }
}
