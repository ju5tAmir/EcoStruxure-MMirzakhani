package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;
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


    public void setInfoList(ListView<Label> employeeInfo, Employee employee){
        List<Label> labels = new ArrayList<>();

        Label annualSalaryLabel = new Label("Annual Salary: " + employee.getContract().getAnnualSalary());
        labels.add(annualSalaryLabel);
        Label fixedAnnualAmountLabel = new Label("Annual Amount: " + employee.getContract().getFixedAnnualAmount());
        labels.add(fixedAnnualAmountLabel);
        Label averageDailyWorkHoursLabel = new Label("Average Daily Work Hours: " + employee.getContract().getAverageDailyWorkHours());
        labels.add(averageDailyWorkHoursLabel);
        Label overheadPercentageLabel = new Label("Overhead Multiplier: " + employee.getContract().getOverheadPercentage());
        labels.add(overheadPercentageLabel);
        Label utilizationPercentageLabel = new Label("Utilization Percentage: " + employee.getContract().getUtilizationPercentage());
        labels.add(utilizationPercentageLabel);
        Label typeLabel = new Label("Employee Type: " + employee.getContract().isOverhead());
        labels.add(utilizationPercentageLabel);


        employeeInfo.getItems().setAll(labels);
    }

}
