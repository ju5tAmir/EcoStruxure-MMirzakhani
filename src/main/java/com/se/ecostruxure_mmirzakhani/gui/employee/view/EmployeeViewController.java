package com.se.ecostruxure_mmirzakhani.gui.employee.view;

import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.be.entities.Employee;
import com.se.ecostruxure_mmirzakhani.be.enums.Currency;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;
import com.se.ecostruxure_mmirzakhani.utils.AlertHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.gui.gui_utils.GUIHelper;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.Window;
import com.se.ecostruxure_mmirzakhani.utils.WindowType;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmployeeViewController implements IController<Model> {
    @FXML
    private TableView<Employee> employeesTable;
    @FXML
    private TableColumn<Employee, String> employeeFirstName, employeeLastName, employeeEmail, employeeAnnualSalary, employeeFixedAmount, employeeAnnualWorkHours, employeeAverageDailyWorkHour, employeeOverheadPercentage;
    @FXML
    private TableView<Assignment> assignmentTable;
    @FXML
    private TableColumn<Assignment, String> projectName;
    @FXML
    private TableColumn<Assignment, String> projectCountry;
    @FXML
    private TableColumn<Assignment, String> projectTeam;
    @FXML
    private TableColumn<Assignment, String> utilizationPercentage;

    private Model model;
    @Override
    public void setModel(Model model) {
        this.model = model;
        try {

            setEmployeeTable();
        } catch (ExceptionHandler e){
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void setEmployeeTable() throws ExceptionHandler {
        // Remove the employee's table initial focus
        Platform.runLater( () -> employeesTable.getScene().getRoot().requestFocus() );

        employeesTable.setItems(model.getAllEmployees());


        employeesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                model.setEmployee(newValue);
                try {
                    setAssignmentTable();
                } catch (ExceptionHandler e) {
                    throw new RuntimeException(e);
                }
            }

        });

        employeeFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        employeeLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        employeeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        employeeAnnualSalary.setCellValueFactory(cellData -> {
            double      annualSalary            = cellData.getValue().getContract().getAnnualSalary();    // In local currency
            Currency    localCurrency           = cellData.getValue().getContract().getCurrency();        // Local currency index
            double      convertedAnnualSalary   = model.getConvertedValue(localCurrency, annualSalary);   // Converted value

            String      formattedString         = GUIHelper.currencyFormatter(convertedAnnualSalary);

            return new SimpleStringProperty(formattedString);
        });
        employeeFixedAmount.setCellValueFactory(cellData -> {
            double      fixedAmount             = cellData.getValue().getContract().getFixedAnnualAmount();
            Currency    localCurrency           = cellData.getValue().getContract().getCurrency();
            double      convertedFixedAmount    = model.getConvertedValue(localCurrency, fixedAmount);

            String formattedString              = GUIHelper.currencyFormatter(convertedFixedAmount);

            return new SimpleStringProperty(formattedString);
        });
        employeeAnnualWorkHours.setCellValueFactory(cellData -> {
            double annualWorkHours              = cellData.getValue().getContract().getAnnualWorkHours();

            return new SimpleStringProperty(GUIHelper.simpleDoubleFormatter(annualWorkHours));
        });

        employeeAverageDailyWorkHour.setCellValueFactory(cellData -> {
            double averageDailyWorkHour         = cellData.getValue().getContract().getAverageDailyWorkHours();

            return new SimpleStringProperty(GUIHelper.simpleDoubleFormatter(averageDailyWorkHour));
        });

        employeeOverheadPercentage.setCellValueFactory(cellData -> {
            double employeeOverheadPercentage   = cellData.getValue().getContract().getOverheadPercentage();

            return new SimpleStringProperty(GUIHelper.simpleDoubleFormatter(employeeOverheadPercentage));
        });

    }



    private void setAssignmentTable() throws ExceptionHandler {
        assignmentTable.setItems(model.getAssignments());

        System.out.println(model.getAssignments());

        projectName.setCellValueFactory(cellData -> {

            String name = cellData.getValue().getProject().getName();

            return new SimpleStringProperty(name);
        });
        projectCountry.setCellValueFactory(cellData -> {
            String name = cellData.getValue().getProject().getCountry().name();

            return new SimpleStringProperty(name);
        });


        projectTeam.setCellValueFactory(cellData -> {
            String name = cellData.getValue().getTeam().getName();

            return new SimpleStringProperty(name);
        });

        utilizationPercentage.setCellValueFactory(cellData -> {
            String name = String.valueOf(cellData.getValue().getUtilizationPercentage());

            return new SimpleStringProperty(name);
        });

    }

    @FXML
    private void onCreateButton(){
        try {
            Window.createStage(WindowType.CREATE_EMPLOYEE, model, Modality.WINDOW_MODAL, false);
        } catch (ExceptionHandler exceptionHandler){
            AlertHandler.displayAlert(exceptionHandler.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onRemoveButton() throws ExceptionHandler {
        if (!assignmentTable.getSelectionModel().isEmpty()){
            if (model.removeAssignment(assignmentTable.getSelectionModel().getSelectedItem())){
                AlertHandler.displayAlert(ExceptionMessage.SUCCESSFUL.getValue(), Alert.AlertType.INFORMATION);

            } else {
                AlertHandler.displayAlert(ExceptionMessage.FAILURE.getValue(), Alert.AlertType.ERROR);
            }


        } else {
            AlertHandler.displayAlert("Please select an assignment first in order to remove it.", Alert.AlertType.INFORMATION);
        }
    }
    public void onAssignButton(ActionEvent actionEvent) {

        if (!employeesTable.getSelectionModel().isEmpty()){
            try {
                Window.createStage(WindowType.ASSIGNMENT, model, Modality.WINDOW_MODAL, false);
            } catch (ExceptionHandler e) {
                throw new RuntimeException(e);
            }

        } else {
            AlertHandler.displayAlert("Please select an employee first in order to assign project.", Alert.AlertType.INFORMATION);
        }
    }
}
