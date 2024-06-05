package com.se.ecostruxure_mmirzakhani.bll.rate;

import com.se.ecostruxure_mmirzakhani.be.entities.Employee;
import com.se.ecostruxure_mmirzakhani.be.entities.Project;
import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.be.enums.EmployeeType;
import com.se.ecostruxure_mmirzakhani.bll.employee.EmployeeService;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RateService implements IRateService {
    private final Project project;
    private final List<Assignment> assignments;
    private static final Logger LOGGER = Logger.getLogger(RateService.class.getName());

    public RateService(Project project, List<Assignment> assignments) {
        this.project = project;
        this.assignments = assignments;
    }

    @Override
    public double getHourlyRate() {
        double totalRate = 0;

        if (assignments == null) {
            return 0;
        }

        for (Assignment assignment : assignments) {
            Employee employee = assignment.getEmployee();
            double employeeTotalCost = EmployeeService.getTotalCost(employee);
            double employeeAnnualWorkHours = employee.getContract().getAnnualWorkHours();
            double employeeCostPerHour = employeeTotalCost / employeeAnnualWorkHours;
            double employeeUtilizationPercentage = assignment.getUtilizationPercentage() / 100;

            double employeeRate = employeeCostPerHour * employeeUtilizationPercentage;
            totalRate += employeeRate;
        }

        return formatDouble(totalRate);
    }

    @Override
    public double getDailyRate() {
        double totalRate = 0;

        if (assignments == null) {
            return 0;
        }

        for (Assignment assignment : assignments) {
            Employee employee = assignment.getEmployee();
            double employeeTotalCost = EmployeeService.getTotalCost(employee);
            double employeeAnnualWorkHours = employee.getContract().getAnnualWorkHours();
            double employeeAverageDailyWorkHours = employee.getContract().getAverageDailyWorkHours();
            double employeeCostPerDay = employeeTotalCost / employeeAnnualWorkHours;
            double employeeUtilizationPercentage = assignment.getUtilizationPercentage() / 100;

            double employeeRate = employeeCostPerDay * employeeAverageDailyWorkHours * employeeUtilizationPercentage;
            totalRate += employeeRate;
        }

        return formatDouble(totalRate);
    }

    @Override
    public double getDirectCosts() {
        double totalRate = 0;

        if (assignments == null) {
            return 0;
        }

        for (Assignment assignment : assignments) {
            Employee employee = assignment.getEmployee();
            if (assignment.getEmployeeType() == EmployeeType.PRODUCTION_RESOURCE) {
                double employeeTotalCost = EmployeeService.getTotalCost(employee);
                double employeeUtilizationPercentage = assignment.getUtilizationPercentage() / 100;

                double cost = employeeTotalCost * employeeUtilizationPercentage;
                totalRate += cost;
            }
        }

        return formatDouble(totalRate);
    }

    @Override
    public double getOverheadCosts() {
        double totalRate = 0;

        if (assignments == null) {
            return 0;
        }

        for (Assignment assignment : assignments) {
            Employee employee = assignment.getEmployee();
            if (assignment.getEmployeeType() == EmployeeType.OVERHEAD) {
                double employeeTotalCost = EmployeeService.getTotalCost(employee);
                double employeeUtilizationPercentage = assignment.getUtilizationPercentage() / 100;

                double cost = employeeTotalCost * employeeUtilizationPercentage;
                totalRate += cost;
            }
        }

        return formatDouble(totalRate);
    }

    @Override
    public double getTotalCosts() {
        double totalRate = 0;

        if (assignments == null) {
            return 0;
        }

        for (Assignment assignment : assignments) {
            Employee employee = assignment.getEmployee();
            double employeeTotalCost = EmployeeService.getTotalCost(employee);
            double employeeUtilizationPercentage = assignment.getUtilizationPercentage() / 100;

            double cost = employeeTotalCost * employeeUtilizationPercentage;
            totalRate += cost;
        }

        return formatDouble(totalRate);
    }

    private double formatDouble(double value) {
        try {
            NumberFormat formatter = NumberFormat.getInstance(Locale.US);
            return formatter.parse(formatter.format(value)).doubleValue();
        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, "Error formatting double value: " + value, e);
            throw new RuntimeException("Error formatting double value: " + value, e);
        }
    }

    @Override
    public String toString() {
        return "RateService{" +
                "project=" + project +
                ", assignments=" + assignments +
                '}';
    }
}
