package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Contract;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;

import java.util.List;

public class EmployeeLogic {
    private final EmployeeDAO dao;
    private Employee employee;

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

    private double calculateHourlyRate(Employee employee) throws ExceptionHandler {

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

    /**
     * Author: Radwan
     */
    private void calculateHourlyRate() throws ExceptionHandler {
        try {
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

            this.employee.getContract().setHourlyRate(hourlyRate);
        } catch (RuntimeException e){
        throw new ExceptionHandler(ExceptionMessage.CALCULATION_ERROR.getValue());
    }
    }
    private double calculateDailyRate(Employee employee) throws ExceptionHandler {

        Contract contract = employee.getContract();

        double hourlyRate = calculateHourlyRate(employee);

        double averageDailyWorkHours = contract.getAverageDailyWorkHours();

        double dailyRate = hourlyRate * averageDailyWorkHours;

        return dailyRate;
    }


    /**
     * Author: Radwan
     */
    private void calculateDailyRate() throws ExceptionHandler {
        try {
            Contract contract = employee.getContract();

            double hourlyRate = calculateHourlyRate(employee);

            double averageDailyWorkHours = contract.getAverageDailyWorkHours();

            double dailyRate = hourlyRate * averageDailyWorkHours;

            this.employee.getContract().setDailyRate(dailyRate);
        } catch (ExceptionHandler e){
            throw new ExceptionHandler(ExceptionMessage.CALCULATION_ERROR.getValue());
        }
    }

    /**
     * Updates the provided employee object with the daily and hourly rate and creates a record for it in the database.
     *
     * @param employee The provided employee object.
     * @return The updated employee object with daily and hourly rate.
     * @throws ExceptionHandler if an error occurs during the creation process in the database.
     */
    public Employee createEmployee(Employee employee) throws ExceptionHandler{
        this.employee = employee;

        // Calculate the hourly rate for the provided employee
        calculateHourlyRate();

        // Calculate the daily rate for the provided employee
        calculateDailyRate();

        // Creates an employee in the db
        dao.createEmployee(this.employee);

        // Returns the employee object
        return this.employee;
    }

    public void updateRates(Employee employee) throws ExceptionHandler {
        this.employee = employee;
        calculateHourlyRate();
        calculateDailyRate();
    }

    /*
    public double applyMarkupToHourlyRate(double hourlyRate, double markupPercentage) {
        double markupMultiplier = 1 + (markupPercentage / 100);
        return hourlyRate * markupMultiplier;
    }

    public double applyMarkupToDailyRate(double dailyRate, double markupPercentage) {
        double markupMultiplier = 1 + (markupPercentage / 100);
        return dailyRate * markupMultiplier;
    }
    */
}
