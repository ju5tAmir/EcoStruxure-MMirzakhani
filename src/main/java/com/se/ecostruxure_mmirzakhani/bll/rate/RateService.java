package com.se.ecostruxure_mmirzakhani.bll.rate;

import com.se.ecostruxure_mmirzakhani.be.entities.Employee;
import com.se.ecostruxure_mmirzakhani.be.entities.Project;
import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.be.enums.EmployeeType;
import com.se.ecostruxure_mmirzakhani.bll.employee.EmployeeService;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;

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
            double      employeeTotalCost               = EmployeeService.getTotalCost(employee); // Employee total cost after being converted from local currency of contract to the system currency
            double      employeeAnnualWorkHours         = employee.getContract().getAnnualWorkHours();
            double      employeeCostPerHour             = employeeTotalCost / employeeAnnualWorkHours;
            double      employeeUtilizationPercentage   = assignment.getUtilizationPercentage() / 100 ;

            double      employeeRate                    = employeeCostPerHour * employeeUtilizationPercentage;

            totalRate += employeeRate;
        }

        // Return the calculated total hourly cost
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
            double      employeeCostPerDay             = employeeTotalCost / employeeAnnualWorkHours;
            double      employeeUtilizationPercentage   = assignment.getUtilizationPercentage() / 100; // Utilization percentage for this project of the employee


            double employeeRate = employeeCostPerDay * employeeAverageDailyWorkHours * employeeUtilizationPercentage;

            totalRate += employeeRate;

        }
        return CurrencyService.doubleFormatter(totalRate);
    }

    /**
     * Get direct costs of a project (non-overhead or production resource)
     */
    @Override
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
            if (assignment.getEmployeeType() == EmployeeType.PRODUCTION_RESOURCE) {
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
    @Override
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
            if (assignment.getEmployeeType() == EmployeeType.OVERHEAD) {
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
     */
    @Override
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



    @Override
    public String toString() {
        return "RateService{" +
                "project=" + project +
                ", assignments=" + assignments +
                '}';
    }
}
