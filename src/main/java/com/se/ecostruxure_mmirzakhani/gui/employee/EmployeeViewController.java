package com.se.ecostruxure_mmirzakhani.gui.employee;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.gui.gui_utils.GUIHelper;
import com.se.ecostruxure_mmirzakhani.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeeViewController implements IController<Model> {
    @FXML
    private TableView<Employee> employeesTable;
    @FXML
    private TableColumn<Employee, String> employeeFirstName, employeeLastName, employeeEmail, employeeAnnualSalary, employeeFixedAmount, employeeAnnualWorkHours, employeeAverageDailyWorkHour, employeeOverheadPercentage, employeeType;

    private Model model;
    @Override
    public void setModel(Model model) {
        this.model = model;
        try {
            setEmployeeTable();
        } catch (ExceptionHandler e){
            System.out.println(e.getMessage());
        }
    }


    private void setEmployeeTable() throws ExceptionHandler {
        employeesTable.setItems(model.getAllEmployees());

        employeeFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        employeeLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        employeeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        employeeAnnualSalary.setCellValueFactory(cellData -> {
            double annualSalary = cellData.getValue().getContract().getAnnualSalary();

            String formattedString = GUIHelper.currencyFormatter(model.convertToSystemCurrency(cellData.getValue().getContract().getCurrency(), annualSalary));

            return new SimpleStringProperty(formattedString);
        });
        employeeFixedAmount.setCellValueFactory(cellData -> {
            double fixedAmount = cellData.getValue().getContract().getFixedAnnualAmount();

            String formattedString = GUIHelper.currencyFormatter(model.convertToSystemCurrency(cellData.getValue().getContract().getCurrency(), fixedAmount));

            return new SimpleStringProperty(formattedString);
        });
        employeeAnnualWorkHours.setCellValueFactory(cellData -> {
            double annualWorkHours = cellData.getValue().getContract().getAnnualWorkHours();

            return new SimpleStringProperty(GUIHelper.simpleDoubleFormatter(annualWorkHours));
        });

        employeeAverageDailyWorkHour.setCellValueFactory(cellData -> {
            double averageDailyWorkHour = cellData.getValue().getContract().getAverageDailyWorkHours();

            return new SimpleStringProperty(GUIHelper.simpleDoubleFormatter(averageDailyWorkHour));
        });

        employeeOverheadPercentage.setCellValueFactory(cellData -> {
            double employeeOverheadPercentage = cellData.getValue().getContract().getOverheadPercentage();

            return new SimpleStringProperty(GUIHelper.simpleDoubleFormatter(employeeOverheadPercentage));
        });

        employeeType.setCellValueFactory(cellData -> {
            boolean employeeType = cellData.getValue().getContract().isOverhead();

            String value = employeeType ? "Overhead": "Product Resource";

            return new SimpleStringProperty(value);
        });
    }
}
