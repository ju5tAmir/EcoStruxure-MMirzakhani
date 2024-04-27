package com.se.ecostruxure_mmirzakhani.gui.create;

import com.se.ecostruxure_mmirzakhani.be.Country;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.exceptions.AlertHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
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
    private ChoiceBox<String> countryChoice;
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
    }

    @FXML
    private void onSubmitButton(ActionEvent actionEvent) {
        try {
            model.setFirstName(firstNameField.getText());
            model.setLastName(lastNameField.getText());

            // I need to set a country and team manually here because I don't want to mess with this page :D
            // Just for example and prevent error
            // You can implement your methods
            model.setCountry(Country.NORTH_KOREA);
            model.setTeam("AreYouA1or0?"); // Even if you are, try not to be


            model.createEmployee();

            // Close the stage if it was successful
            Window.closeStage(firstNameField.getScene());

        } catch (ExceptionHandler e){
            // Throw an alert error ToDo: Add a custom error message :D
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    // ToDo: Check for null inputs

}
