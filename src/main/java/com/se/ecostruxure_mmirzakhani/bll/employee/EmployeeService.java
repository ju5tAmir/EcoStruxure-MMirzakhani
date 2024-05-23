package com.se.ecostruxure_mmirzakhani.bll.employee;

import com.se.ecostruxure_mmirzakhani.be.*;
import com.se.ecostruxure_mmirzakhani.dal.employee.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;

import java.util.List;

public class EmployeeService {

    private final static EmployeeDAO dao = new EmployeeDAO();;

    private static Currency systemCurrency = CurrencyService.getSystemCurrency();
    private Employee employee;
    private List<Project> projects;
    private List<Assignment> assignments;

    public EmployeeService(){

    }


    public EmployeeService(Employee employee){
        this.employee = employee;
    }

    public EmployeeService(Currency systemCurrency){
        this.systemCurrency = systemCurrency;
    }



    public void setCurrency(Currency systemCurrency) {
        this.systemCurrency = systemCurrency;
    }

    public List<Employee> getAllEmployees(){
        return dao.getAllEmployees();
    }



    public List<Project> getAllProjects(Employee employee) {
        return dao.getAllProjects(employee);
    }

    public List<Project> getAllProjects(){
        return dao.getAllProjects();
    }

    /**
     * Calculate the total cost for the given employee
     */
    public static double getTotalCost(Employee employee){
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
     * Get employee contract and projects changes
     */
    public History getEmployeeHistory(Employee employee){
        return dao.getEmployeeHistory(employee);
    }
}
