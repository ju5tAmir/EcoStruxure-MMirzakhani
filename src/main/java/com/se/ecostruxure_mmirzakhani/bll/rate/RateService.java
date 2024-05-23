package com.se.ecostruxure_mmirzakhani.bll.rate;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Project;
import com.se.ecostruxure_mmirzakhani.be.Assignment;
import com.se.ecostruxure_mmirzakhani.bll.employee.EmployeeService;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;

import java.util.ArrayList;
import java.util.List;

public class RateService implements IRateService{
    private final Project project;
    private final List<Assignment> assignments;

    public RateService(Project project, List<Assignment> assignments){
        this.project = project;
        this.assignments = assignments;
    }


    @Override
    public double getHourlyRate() {
        // Initialize the total rate to accumulate the hourly rates of all employees
        double totalRate = 0;

        // if project doesn't have any member, return 0
        if (assignments == null){
            return 0;
        }

        // Iterate through each project member to calculate the hourly rate for the employee assigned to the project
        for (Assignment assignment : assignments) {
            Employee    employee                        = assignment.getEmployee();
            double      employeeTotalCost               = EmployeeService.getTotalCost(employee);
            double      employeeAnnualWorkHours         = employee.getContract().getAnnualWorkHours();
            double      employeeUtilizationPercentage   = assignment.getUtilizationPercentage(); // Utilization percentage for this project of the employee
            double      employeeEffectiveWorkHours      = employeeAnnualWorkHours * employeeUtilizationPercentage / 100;

            double      employeeRate                    = employeeTotalCost / employeeEffectiveWorkHours;

            totalRate += employeeRate;
        }

        // Return the calculated total cost
        return CurrencyService.doubleFormatter(totalRate);
    }

    @Override
    public double getDailyRate() {
        // Initialize the total rate to accumulate the daily rates of all employees
        double totalRate = 0;

        // Return zero if project doesn't have any member
        if (assignments == null) {
            return 0;
        }

        // Iterate through each project to calculate the daily rate for the employee assigned to the project
        for (Assignment assignment : assignments) {
            Employee    employee                        = assignment.getEmployee();
            double      employeeTotalCost               = EmployeeService.getTotalCost(employee);
            double      employeeAnnualWorkHours         = employee.getContract().getAnnualWorkHours();
            double      employeeAverageDailyWorkHours   = employee.getContract().getAverageDailyWorkHours();
            double      employeeUtilizationPercentage   = assignment.getUtilizationPercentage(); // Utilization percentage for this project of the employee
            double      employeeEffectiveWorkHours      = employeeAnnualWorkHours * employeeUtilizationPercentage / 100;


            double employeeRate = employeeTotalCost / employeeEffectiveWorkHours * employeeAverageDailyWorkHours;

            totalRate += employeeRate;

        }
        return CurrencyService.doubleFormatter(totalRate);
    }

    public Project getProject(){
        return project;
    }

    /**
     * Get direct costs of a project (non-overhead or production resource)
     */
    public double getDirectCosts() {
        // Initialize the total rate to accumulate the hourly rates of all employees
        double totalRate = 0;

        // if project doesn't have any member, return 0
        if (assignments == null) {
            return 0;
        }

        // Iterate through each project member to calculate
        for (Assignment assignment : assignments) {
            Employee employee = assignment.getEmployee();
            // If the employee is NOT considered as overhead resource
            if (!employee.getContract().isOverhead()) {
                double employeeTotalCost = EmployeeService.getTotalCost(employee);
                double employeeUtilizationPercentage = assignment.getUtilizationPercentage() / 100; // Utilization percentage for this project of the employee

                double cost = employeeTotalCost * employeeUtilizationPercentage;
                totalRate += cost;
            }
        }

        // Return the calculated cost as formatted
        return CurrencyService.doubleFormatter(totalRate);
    }

    /**
     * Get cost of overhead employees for the project
     */
    public double getOverheadCosts(){
        // Initialize the total rate to accumulate the hourly rates of all employees
        double totalRate = 0;

        // if project doesn't have any member, return 0
        if (assignments == null){
            return 0;
        }

        // Iterate through each project member to calculate
        for (Assignment assignment : assignments) {
            Employee    employee                        = assignment.getEmployee();
            // If the employee is considered as overhead resource
            if (employee.getContract().isOverhead()) {
                double employeeTotalCost = EmployeeService.getTotalCost(employee);
                double employeeUtilizationPercentage = assignment.getUtilizationPercentage() / 100; // Utilization percentage for this project of the employee

                double cost = employeeTotalCost * employeeUtilizationPercentage;
                totalRate += cost;
            }
        }

        // Return the calculated cost as formatted
        return CurrencyService.doubleFormatter(totalRate);
    }


    /**
     * Get total costs: (direct + overhead) costs
     * @return
     */
    public double getTotalCosts(){
        // Initialize the total rate to accumulate the hourly rates of all employees
        double totalRate = 0;

        // if project doesn't have any member, return 0
        if (assignments == null){
            return 0;
        }

        // Iterate through each project member to calculate
        for (Assignment assignment : assignments) {
            Employee    employee                        = assignment.getEmployee();
            double employeeTotalCost = EmployeeService.getTotalCost(employee);
            double employeeUtilizationPercentage = assignment.getUtilizationPercentage() / 100; // Utilization percentage for this project of the employee

            double cost = employeeTotalCost * employeeUtilizationPercentage;
            totalRate += cost;
        }

        // Return the calculated cost as formatted
        return CurrencyService.doubleFormatter(totalRate);
    }

    public List<Assignment> getOverheadEmployees(){
        List<Assignment> overheadEmployees = new ArrayList<>();

        for (Assignment assignment : assignments){
            if (assignment.getEmployee().getContract().isOverhead()){
                overheadEmployees.add(assignment);
            }
        }
        return overheadEmployees;
    }

    public List<Assignment> getProductionEmployees(){
        List<Assignment> overheadEmployees = new ArrayList<>();

        for (Assignment assignment : assignments){
            if (!assignment.getEmployee().getContract().isOverhead()){
                overheadEmployees.add(assignment);
            }
        }
        return overheadEmployees;
    }

    @Override
    public String toString() {
        return "RateService{" +
                "project=" + project +
                ", projectMembers=" + assignments +
                '}';
    }
}
