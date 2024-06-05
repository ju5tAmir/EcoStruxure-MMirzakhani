package com.se.ecostruxure_mmirzakhani.bll.employee;

import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.be.entities.Employee;
import com.se.ecostruxure_mmirzakhani.be.entities.History;
import com.se.ecostruxure_mmirzakhani.be.enums.Currency;
import com.se.ecostruxure_mmirzakhani.bll.IService;
import com.se.ecostruxure_mmirzakhani.dal.employee.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;

import java.util.List;

public class EmployeeService implements IService<Employee> {

    private EmployeeDAO dao;

    public EmployeeService(){
        this.dao = new EmployeeDAO();
    }

    @Override
    public boolean create(Employee employee) throws ExceptionHandler {
        return dao.createEmployee(employee);
    }


    @Override
    public boolean update(Employee employee) throws ExceptionHandler {
        return dao.updateEmployee(employee);

    }

    @Override
    public boolean delete(Employee employee) throws ExceptionHandler {
        return dao.deleteEmployee(employee);

    }
    /**
     * Get total utilization percentage for an employee
     */
    public static double getTotalUtilizationPercentage(Employee employee, List<Assignment> assignments) {
        return assignments.stream()
                .filter(a -> a.getEmployee().equals(employee))
                .mapToDouble(Assignment::getUtilizationPercentage)
                .sum();
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
        double totalCostInSystemCurrency = CurrencyService.convert(employeeLocalCurrency, CurrencyService.getSystemCurrency(), totalCostInLocalCurrency);
        return totalCostInSystemCurrency;
    }



    /**
     * Get employee contract and projects changes
     */
    public History getEmployeeHistory(Employee employee) throws ExceptionHandler {
        return dao.getEmployeeHistory(employee);
    }

    /**
     * Get all the employees
     */
    public List<Employee> getAllEmployees() throws ExceptionHandler{
        return dao.getAllEmployees();
    }
}
