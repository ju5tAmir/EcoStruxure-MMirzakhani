package com.se.ecostruxure_mmirzakhani.gui.employee;

import com.se.ecostruxure_mmirzakhani.be.Country;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Project;
import com.se.ecostruxure_mmirzakhani.be.ProjectMemberLinker;
import com.se.ecostruxure_mmirzakhani.exceptions.AlertHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.gui.gui_utils.GUIHelper;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

public class EmployeeViewController implements IController<Model> {
    @FXML
    private TableView<Employee> employeesTable;
    @FXML
    private TableColumn<Employee, String> employeeFirstName, employeeLastName, employeeEmail, employeeAnnualSalary, employeeFixedAmount, employeeAnnualWorkHours, employeeAverageDailyWorkHour, employeeOverheadPercentage, employeeType;
    @FXML
    private TableView<ProjectMemberLinker> projectsTable;

    @FXML
    private TableColumn<ProjectMemberLinker, String> projectName;
    @FXML
    private TableColumn<ProjectMemberLinker, String> projectCountry;
    @FXML
    private TableColumn<ProjectMemberLinker, String> projectTeam;
    @FXML
    private TableColumn<ProjectMemberLinker, String> utilizationPercentage;

    private Model model;
    @Override
    public void setModel(Model model) {
        this.model = model;
        try {
            Platform.runLater( () -> employeesTable.getScene().getRoot().requestFocus() );
            setEmployeeTable();
            setProjectsTable();
        } catch (ExceptionHandler e){
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void setProjectsTable() throws ExceptionHandler {
        projectsTable.setItems(model.getProjectMemberLinker(model.getEmployee()));


        projectName.setCellValueFactory(cellData -> {

            String name = cellData.getValue().getProject().getName();

            return new SimpleStringProperty(name);
        });
        projectCountry.setCellValueFactory(cellData -> {
            String name = cellData.getValue().getProject().getCountry().toString();

            return new SimpleStringProperty(name);
        });


        projectTeam.setCellValueFactory(cellData -> {
            String name = cellData.getValue().getProjectMember().getTeam().getName();

            return new SimpleStringProperty(name);
        });

        utilizationPercentage.setCellValueFactory(cellData -> {
            String name = String.valueOf(cellData.getValue().getProjectMember().getUtilizationPercentage());

            return new SimpleStringProperty(name);
        });

    }

    private void setEmployeeTable() throws ExceptionHandler {
        employeesTable.setItems(model.getAllEmployees());


        employeesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                model.setEmployee(newValue);
                try {
                    setProjectsTable();
                } catch (ExceptionHandler e) {
                    throw new RuntimeException(e);
                }
            }

        });

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

    @FXML
    private void onCreateButton(){
        try {
            Window.createStage(WindowType.CREATE_EMPLOYEE, model, Modality.WINDOW_MODAL, false);
        } catch (ExceptionHandler exceptionHandler){
            AlertHandler.displayAlert(exceptionHandler.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void onAssignButton(ActionEvent actionEvent) {

        if (!employeesTable.getSelectionModel().isEmpty()){
            try {
                Window.createStage(WindowType.ASSIGN_EMPLOYEE_PROJECT, model, Modality.WINDOW_MODAL, false);
            } catch (ExceptionHandler e) {
                throw new RuntimeException(e);
            }

        } else {
            AlertHandler.displayAlert("Please select an employee first in order to assign project.", Alert.AlertType.INFORMATION);
        }
    }
}
