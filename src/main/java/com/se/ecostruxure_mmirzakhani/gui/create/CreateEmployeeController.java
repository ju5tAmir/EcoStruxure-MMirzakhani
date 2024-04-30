package com.se.ecostruxure_mmirzakhani.gui.create;

import com.se.ecostruxure_mmirzakhani.be.Country;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.exceptions.AlertHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * <a href="https://github.com/NilIQW/">Author: NilIQW</a>
 */

public class CreateEmployeeController implements IController<Model> {

    @FXML
    private TextField firstNameField, lastNameField;
    @FXML
    private TextField overheadMultiplierField;
    @FXML
    private TextField fixedAnnualAmountField;
    @FXML
    private ChoiceBox countryChoice;
    @FXML
    private ChoiceBox<String> teamChoice;
    @FXML
    private TextField annualWorkingHoursField;
    @FXML
    private TextField utilizationField;
    @FXML
    private ChoiceBox<String> typeChoice;
    @FXML
    private TableView<Employee> employeeTableView;

    private Model model;

    @Override
    public void setModel(Model model) {
        this.model = model;
        populateChoiceBoxes();
    }

    private void populateChoiceBoxes() {
        ObservableList<Country> countries = FXCollections.observableArrayList(Country.values());
        countryChoice.setItems(countries);
        countryChoice.setValue("Choose Country");

        typeChoice.getItems().addAll("Production Resource", "Overhead");
        typeChoice.setValue("Choose Type");
    }

    @FXML
    private void onSubmitButton(ActionEvent actionEvent) {
        try {
            // Filling the values from user input
            model.setFirstName(firstNameField.getText());
            model.setLastName(lastNameField.getText());

            countryChoice.getValue();
            // I need to set a country and team manually here because I don't want to mess with this page :D
            // Just for example and prevent error
            // You can implement your methods
            model.setCountry(Country.NORTH_KOREA);
            model.setTeam("AreYouA1or0?"); // Even if you are, try not to be

            // ToDo: Keep going with other fields as needed.
            // ToDo: Don't forget to check for null inputs.

            // Trigger the final action for creating an employee


            // For type choiceBox, the logic should be if the selected value iss Overhead, return true, else false
            model.createEmployee();

            // Close the stage if it was successful
            Window.closeStage(firstNameField.getScene());

        } catch (ExceptionHandler e){
            // Throw an alert error ToDo: Add a custom error message :D
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    // ToDo: Method to check for null inputs

}
