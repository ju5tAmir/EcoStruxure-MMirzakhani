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

import java.util.Arrays;
import java.util.List;

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
    private ComboBox filterComboBox;
    @FXML
    private ComboBox teamComboBox;

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
            populateChoicebox();



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


        // sets a listener to update the listview based on the selected Employee
        employeeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue!=null){
                model.setInfoList(employeeInfoList, newValue);
            }
        } );


    }

    public void populateChoicebox() {
        ObservableList<Country> countryList = FXCollections.observableArrayList(Country.values());

        filterComboBox.getItems().addAll("Country", "Team");

        // Initially set the value to "All"
        filterComboBox.setValue("All");

        teamComboBox.setVisible(false);

        filterComboBox.setOnAction(event -> {
                String selectedOption = filterComboBox.getValue().toString();
                if (selectedOption.equals("Country")) {
                    teamComboBox.setVisible(true);
                    teamComboBox.setValue("Select Country");
                    teamComboBox.getItems().addAll(countryList);

                } else if (selectedOption.equals("Team")) {
                    teamComboBox.setVisible(true);
                    teamComboBox.setValue("Select Team");
                    teamComboBox.getItems().setAll("Production", "Management");
                }

            });

        teamComboBox.setOnKeyPressed(event -> {
            if (event.getCode().isLetterKey()) {
                String typedText = event.getText();
                filterCountriesByFirstLetter(typedText);
            }
        });

        }
    private void filterCountriesByFirstLetter(String letter) {

        //get the list of countries from the enum and change it to observable
        ObservableList<Country> countryList = FXCollections.observableArrayList(Country.values());

        //filter the list by the first typed letter
        ObservableList<Country> filteredList = countryList.filtered(country ->
                country.getValue().toLowerCase().startsWith(letter.toLowerCase()));

        teamComboBox.setItems(filteredList);
        teamComboBox.getSelectionModel().selectFirst();
    }


}
