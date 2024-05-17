package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Currency;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Project;
import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;

import java.util.List;

public class EmployeeService {

    private Currency systemCurrency;

    public EmployeeService(){

    }

    public EmployeeService(Currency systemCurrency){
        this.systemCurrency = systemCurrency;
    }

    public void setCurrency(Currency systemCurrency) {
        this.systemCurrency = systemCurrency;
    }
    private static final EmployeeDAO dao = new EmployeeDAO();

    public List<Employee> getAllEmployees(){
        return dao.getAllEmployees();
    }

    public List<Team> getAllTeams() {
        return dao.getAllTeams();
    }

    public List<Project> getAllProjects() {
        return dao.getAllProjects();
    }

    /**
     * Calculate the total cost for the given employee (without
     */
    public double getTotalCost(Employee employee){
        double      annualSalary                 = employee.getContract().getAnnualSalary();
        double      annualFixedAmount            = employee.getContract().getFixedAnnualAmount();
        double      overheadPercentage           = employee.getContract().getOverheadPercentage();
        double      overheadAmount               = annualSalary * overheadPercentage / 100;
        double      totalAmountInLocalCurrency   = annualSalary + annualFixedAmount + overheadAmount;
        Currency    localCurrency                = employee.getContract().getCurrency();

        double      convertedRate                = CurrencyService.convert(localCurrency, systemCurrency, totalAmountInLocalCurrency);

        return convertedRate;
    }

    public double getHourlyRate(Project project){
        // Initialize the employee's rate
        double employeeRate = 0;

        // Retrieve the employee's contract details
        double employeeAnnualSalary = project.getEmployee().getContract().getAnnualSalary();
        double employeeAnnualWorkHours = project.getEmployee().getContract().getAnnualWorkHours();
        double employeeAnnualFixedAmount = project.getEmployee().getContract().getFixedAnnualAmount();
        double employeeOverheadPercentage = project.getEmployee().getContract().getOverheadPercentage() / 100.0;
        double employeeUtilizationPercentage = project.getUtilizationPercentage() / 100.0;
        Currency employeeLocalCurrency = project.getEmployee().getContract().getCurrency();

        // Calculate the overhead amount based on the annual salary and overhead percentage
        double overheadAmount = employeeAnnualSalary * employeeOverheadPercentage;

        // Calculate the total cost in the employee's local currency
        double totalCostInLocalCurrency = employeeAnnualSalary + employeeAnnualFixedAmount + overheadAmount;

        // Convert the total cost to the system's currency using the CurrencyService
        double totalCostInSystemCurrency = CurrencyService.convert(employeeLocalCurrency, systemCurrency, totalCostInLocalCurrency);

        // Calculate the effective work hours by multiplying the annual work hours by the utilization percentage
        double effectiveWorkHours = employeeAnnualWorkHours * employeeUtilizationPercentage;

        // Calculate the hourly rate by dividing the total cost in system currency by the effective work hours
        employeeRate = totalCostInSystemCurrency / effectiveWorkHours;

        return employeeRate;
    }

    public double getDailyRate(Project project) {
        double employeeAverageDailyWorkHours = project.getEmployee().getContract().getAverageDailyWorkHours();
        double employeeHourlyRate            = getHourlyRate(project);

        return employeeHourlyRate * employeeAverageDailyWorkHours;
    }
}
