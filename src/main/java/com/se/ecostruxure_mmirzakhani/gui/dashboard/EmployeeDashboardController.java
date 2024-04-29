package com.se.ecostruxure_mmirzakhani.gui.dashboard;

import com.se.ecostruxure_mmirzakhani.be.Country;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.exceptions.AlertHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

/**
 * <a href="https://github.com/NilIQW/">Author: NilIQW</a>
 */
public class EmployeeDashboardController implements IController {

    @FXML
    private TableView<Employee> employeeTableView;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, String> employeeCountry;
    @FXML
    private TableColumn<Employee, String> employeeTeam;
    @FXML
    private ListView<Label> employeeInfoList;
    @FXML
    private ChoiceBox countryChoiceBox;

    private Model model;

    // ToDo: Implement controller methods such as buttons and other nodes.

    @FXML
    private void onCreateEmployee() {
        try {
            Window.createStage(WindowType.CREATE_EMPLOYEE, model, Modality.APPLICATION_MODAL, false);
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void setModel(Object model) {
        // This method should update model object by retrieving incoming model,
        // but because this controller is the initial controller, it will only
        // instantiate a new model object
        this.model = new Model();

        try {
            initEmployeesTable();
            initEmployeeColumns();


        } catch (ExceptionHandler exceptionHandler){
            AlertHandler.displayAlert(exceptionHandler.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Initializing the Employees table
     */
    private void initEmployeesTable() throws ExceptionHandler{
        employeeTableView.setItems(model.getAllEmployees());
    }

    /**
     * Initializing columns for the Employees table
     */
    private void initEmployeeColumns(){
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

        employeeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue!=null){
                model.setInfoList(employeeInfoList, newValue);
            }
        } );

        ObservableList<Country> countryList = FXCollections.observableArrayList(Country.values());
        countryChoiceBox.setItems(countryList);
        countryChoiceBox.setValue("Group by country");
    }


}
