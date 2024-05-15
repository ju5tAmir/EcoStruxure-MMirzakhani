//package com.se.ecostruxure_mmirzakhani.bll;
//
//import com.se.ecostruxure_mmirzakhani.be.Profile;
//import com.se.ecostruxure_mmirzakhani.be.Employee;
//import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;
//import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
//import com.se.ecostruxure_mmirzakhani.utils.Validate;
//
//import java.util.List;
//
//public class EmployeeLogic {
//    private final EmployeeDAO dao;
//    private Employee employee;
//
//    {
//        try {
//            dao = new EmployeeDAO();
//        } catch (ExceptionHandler e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * Constructor
//     */
//    public EmployeeLogic(){
//
//    }
//
//    /**
//     * Retrieve all the employees from the database with their latest contract (without history)
//     * @return List of employees
//     * @throws ExceptionHandler If any error occurred.
//     */
//    public List<Employee> getAllEmployees() throws ExceptionHandler{
//        // List to store all retrieved employees from the database
//        List<Employee> employees = dao.getAllEmployees();
//
//        // Update all the employees with their rates
//        for (Employee e: employees){
//            updateRates(e);
//        }
//        return employees;
//    }
//
//    public boolean updateEmployee(Employee employee) throws ExceptionHandler {
//       return dao.updateEmployee(employee);
//
//
//        // ToDo: exception handling
//    }
//
//    // ToDo: Implement methods to calculate rates
//
//    private double calculateHourlyRate(Employee employee) throws ExceptionHandler {
//
//        Profile contract = employee.getContract();
//
//        double annualSalary = contract.getAnnualSalary();
//        double fixedAnnualAmount = contract.getFixedAnnualAmount();
//        double annualWorkHours = contract.getAnnualWorkHours();
//        double utilizationPercentage = contract.getUtilizationPercentage();
//        double overheadMultiplier = 1 + (contract.getOverheadPercentage() / 100);
//
//        double effectiveAnnualWorkHours = annualWorkHours * (utilizationPercentage / 100);
//
//        double adjustedAnnualSalary = annualSalary + fixedAnnualAmount;
//
//        double adjustedAnnualSalaryWithOverhead = adjustedAnnualSalary * overheadMultiplier;
//
//        double hourlyRate = adjustedAnnualSalaryWithOverhead / effectiveAnnualWorkHours;
//
//        return hourlyRate;
//    }
//
//    /**
//     * Author: Radwan
//     */
//    private void calculateHourlyRate() throws ExceptionHandler {
//        // Check if it's safe to do the division (non-zero numbers)
//        if (Validate.safeDivision(employee.getContract())) {
//
//            Profile contract = employee.getContract();
//            double annualSalary = contract.getAnnualSalary();
//            double fixedAnnualAmount = contract.getFixedAnnualAmount();
//            double annualWorkHours = contract.getAnnualWorkHours();
//            double utilizationPercentage = contract.getUtilizationPercentage();
//            double overheadMultiplier = 1 + (contract.getOverheadPercentage() / 100);
//            double effectiveAnnualWorkHours = annualWorkHours * (utilizationPercentage / 100);
//            double adjustedAnnualSalary = annualSalary + fixedAnnualAmount;
//            double adjustedAnnualSalaryWithOverhead = adjustedAnnualSalary * overheadMultiplier;
//            double hourlyRate = adjustedAnnualSalaryWithOverhead / effectiveAnnualWorkHours;
//
//            this.employee.getContract().setHourlyRate(hourlyRate);
//        } else {
//            this.employee.getContract().setHourlyRate(0);
//        }
//    }
//
//
//    /**
//     * Author: Radwan
//     */
//    private void calculateDailyRate() throws ExceptionHandler {
//        // Check if it's safe to do the division (non-zero numbers)
//        if (Validate.safeDivision(employee.getContract())){
//
//            Profile contract = employee.getContract();
//
//            double hourlyRate = calculateHourlyRate(employee);
//
//            double averageDailyWorkHours = contract.getAverageDailyWorkHours();
//
//            double dailyRate = hourlyRate * averageDailyWorkHours;
//
//            this.employee.getContract().setDailyRate(dailyRate);
//        } else {
//            this.employee.getContract().setDailyRate(0);
//        }
//    }
//
//    /**
//     * Updates the provided employee object with the daily and hourly rate and creates a record for it in the database.
//     *
//     * @param employee The provided employee object.
//     * @return The updated employee object with daily and hourly rate.
//     * @throws ExceptionHandler if an error occurs during the creation process in the database.
//     */
//    public Employee createEmployee(Employee employee) throws ExceptionHandler{
//        this.employee = employee;
//
//        // Creates an employee in the db
//        dao.createEmployee(this.employee);
//
//        // Calculate and update the rates for the employee
//        updateRates(this.employee);
//
//        // Returns the employee object
//        return this.employee;
//    }
//
//    public void updateRates(Employee employee) throws ExceptionHandler {
//        this.employee = employee;
//        // Calculate the hourly rate for the provided employee
//        calculateHourlyRate();
//
//        // Calculate the daily rate for the provided employee
//        calculateDailyRate();
//
//    }
//
//
//    public double hourlyRateMarkup(double hourlyRate, double markupPercentage) {
//        double markupMultiplier = 1 + (markupPercentage / 100);
//        return hourlyRate * markupMultiplier;
//    }
//
//    public double dailyRateMarkup(double dailyRate, double markupPercentage) {
//        double markupMultiplier = 1 + (markupPercentage / 100);
//        return dailyRate * markupMultiplier;
//    }
//
//
//    public double hourlyRateGM(double hourlyRate, double gmPercentage) {
//        double gmMultiplier = 1 + (gmPercentage / 100);
//        return hourlyRate * gmMultiplier;
//    }
//
//    public double dailyRateGM(double dailyRate, double gmPercentage) {
//        double gmMultiplier = 1 + (gmPercentage / 100);
//        return dailyRate * gmMultiplier;
//    }
//}
