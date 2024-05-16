package com.se.ecostruxure_mmirzakhani.gui.employee;

import com.se.ecostruxure_mmirzakhani.be.Country;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.exceptions.AlertHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController implements IController<Model> {
    @FXML
    private TableView<Employee> employeeTableView;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, String> employeeCountry;
    @FXML
    private TableColumn<Employee, String> employeeTeam, hourlyRateColumn, dailyRateColumn;
    @FXML
    private ListView<Label> employeeInfoList;

    private Model model;

    /**
     * Initializing the Employees table
     */
    private void initEmployeesTable() throws ExceptionHandler {
        employeeTableView.setItems(model.getAllEmployees());
        employeeTableView.getSelectionModel().clearSelection();
    }

    /**
     * Initializing columns for the Employees table
     */
    private void initEmployeeColumns() throws ExceptionHandler {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        employeeCountry.setCellValueFactory(cellData -> {
            // Retrieve the country enum value from the Employee object
            Country country = cellData.getValue().getCountry();

            // Return the string representation
            return new SimpleStringProperty(country.getValue());
        });
        employeeTeam.setCellValueFactory(cellData -> {
            // Retrieve the team object from the employee
            Team team = cellData.getValue().getTeam();

            // Return the string representation
            return new SimpleStringProperty(team.getName());
        });

        dailyRateColumn.setCellValueFactory(cellData -> {
            // Retrieve the daily rate
            BigDecimal dailyRate = cellData.getValue().getContract().getDailyRate();

            // Return the string representation with 2 decimal float number
            return new SimpleStringProperty(String.format("%.2f", dailyRate));
        });
        hourlyRateColumn.setCellValueFactory(cellData -> {
            // Retrieve the hourly rate
            BigDecimal hourlyRate = cellData.getValue().getContract().getHourlyRate();

            // Return the string representation with 2 decimal float number
            return new SimpleStringProperty(String.format("%.2f", hourlyRate));
        });

        // sets a listener to update the listview based on the selected Employee
        employeeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue != null) {
                    // Updates the employee object in the model with the selected employee from the table
                    model.setEmployee(newValue);

                    List<Label> labels = new ArrayList<>();

                    Label employeeExtraInfo = new Label("Contract info of " + model.getEmployee().getFirstName() + " " + model.getEmployee().getLastName() + ":");
                    employeeExtraInfo.setStyle("-fx-font-weight: bold;");
                    labels.add(employeeExtraInfo);
                    Label annualSalaryLabel = new Label("Annual Salary: " + model.getEmployee().getContract().getAnnualSalary());
                    labels.add(annualSalaryLabel);
                    Label fixedAnnualAmountLabel = new Label("Annual Amount: " + model.getEmployee().getContract().getFixedAnnualAmount());
                    labels.add(fixedAnnualAmountLabel);
                    Label averageDailyWorkHoursLabel = new Label("Average Daily Work Hours: " + model.getEmployee().getContract().getAverageDailyWorkHours());
                    labels.add(averageDailyWorkHoursLabel);
                    Label overheadPercentageLabel = new Label("Overhead Multiplier: " + model.getEmployee().getContract().getOverheadPercentage());
                    labels.add(overheadPercentageLabel);
                    Label annualWorkHours = new Label("Annual Working Hours: " + model.getEmployee().getContract().getAnnualWorkHours());
                    labels.add(annualWorkHours);
                    Label utilizationPercentageLabel = new Label("Utilization Percentage: " + model.getEmployee().getContract().getUtilizationPercentage());
                    labels.add(utilizationPercentageLabel);


                    // Check if the employee is considered overhead or not
                    if (model.getEmployee().getContract().isOverhead()) {
                        Label typeLabel = new Label("Employee Type: Overhead Cost");
                        labels.add(typeLabel);
                    } else {
                        Label typeLabel = new Label("Employee Type: Production Resource");
                        labels.add(typeLabel);
                    }

                    Label lineSeparator = new Label("-------------------");
                    labels.add(lineSeparator);

                    Label dailyRate = new Label("Daily Rate: " + String.format("%.2f", model.getDailyRate()));
                    labels.add(dailyRate);

                    Label hourlyRate = new Label("Hourly Rate: " + String.format("%.2f", model.getHourlyRate()));
                    labels.add(hourlyRate);

                    employeeInfoList.getItems().setAll(labels);
                }

            } catch (ExceptionHandler e) {
                AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
            }});
    }



    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void editEmployee(ActionEvent actionEvent) {
        Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
        model.setSelectedEmployee(selectedEmployee);
        try {
            Window.createStage(WindowType.CREATE_EMPLOYEE, model, Modality.APPLICATION_MODAL, false);
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEmployee(ActionEvent actionEvent) {
    }

    @FXML
    private void onCreateEmployee() {
        try {
            Window.createStage(WindowType.CREATE_EMPLOYEE, model, Modality.APPLICATION_MODAL, false);
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void setModel(Model model) {
        this.model = model;
        try {
            initEmployeesTable();
            initEmployeeColumns();

        } catch (ExceptionHandler exceptionHandler) {
            AlertHandler.displayAlert(exceptionHandler.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
