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

        Label employeeExtraInfo = new Label("Contract info of " + employee.getFirstName() + " " +employee.getLastName()+ ":");
        employeeExtraInfo.setStyle("-fx-font-weight: bold;");
        labels.add(employeeExtraInfo);
        Label annualSalaryLabel = new Label("Annual Salary: " + employee.getContract().getAnnualSalary());
        labels.add(annualSalaryLabel);
        Label fixedAnnualAmountLabel = new Label("Annual Amount: " + employee.getContract().getFixedAnnualAmount());
        labels.add(fixedAnnualAmountLabel);
        Label averageDailyWorkHoursLabel = new Label("Average Daily Work Hours: " + employee.getContract().getAverageDailyWorkHours());
        labels.add(averageDailyWorkHoursLabel);
        Label overheadPercentageLabel = new Label("Overhead Multiplier: " + employee.getContract().getOverheadPercentage());
        labels.add(overheadPercentageLabel);
        Label annualWorkHours = new Label("Annual Working Hours" + employee.getContract().getAnnualWorkHours());
        labels.add(annualSalaryLabel);
        Label utilizationPercentageLabel = new Label("Utilization Percentage: " + employee.getContract().getUtilizationPercentage());
        labels.add(utilizationPercentageLabel);

        // Check if the employee is considered overhead or not
        if (employee.getContract().isOverhead()) {
            Label typeLabel = new Label("Employee Type: Overhead Cost");
            labels.add(typeLabel);
        } else {
            Label typeLabel = new Label("Employee Type: Production Resource");
            labels.add(typeLabel);
        }

        employeeInfo.getItems().setAll(labels);
    }


}
