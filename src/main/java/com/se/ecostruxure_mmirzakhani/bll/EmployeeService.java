package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Currency;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Project;
import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;
import javafx.beans.property.SimpleObjectProperty;

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



    public List<Project> getAllProjects() {
        return dao.getAllProjects();
    }

    /**
     * Calculate the total cost for the given employee
     */
    public double getTotalCost(Employee employee){
        // Retrieve the employee's contract details
        double employeeAnnualSalary = employee.getContract().getAnnualSalary();
        double employeeAnnualFixedAmount = employee.getContract().getFixedAnnualAmount();
        double employeeOverheadPercentage = employee.getContract().getOverheadPercentage() / 100.0;
        Currency employeeLocalCurrency = employee.getContract().getCurrency();

        // Calculate the overhead amount based on the annual salary and overhead percentage
        double overheadAmount = employeeAnnualSalary * employeeOverheadPercentage;

        // Calculate the total cost in the employee's local currency
        double totalCostInLocalCurrency = employeeAnnualSalary + employeeAnnualFixedAmount + overheadAmount;

        // Convert the total cost to the system's currency using the CurrencyService
        double totalCostInSystemCurrency = CurrencyService.convert(employeeLocalCurrency, systemCurrency, totalCostInLocalCurrency);
        return totalCostInSystemCurrency;
    }

    public boolean create(Employee employee, List<Project> projects) {
        return dao.createEmployee(employee, projects);
    }

    /**
     * Calculate and returns total utilization percentage for an employee based on working projects.
     */
    public double getTotalUtilization(List<Project> projects) {
        double totalUtilizationPercentage = 0;
        for (Project project: projects){
            totalUtilizationPercentage += project.getUtilizationPercentage();
        }
        return totalUtilizationPercentage;
    }
}
