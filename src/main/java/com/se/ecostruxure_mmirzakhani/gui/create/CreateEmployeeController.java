package com.se.ecostruxure_mmirzakhani.gui.create;

import com.se.ecostruxure_mmirzakhani.be.Country;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.exceptions.AlertHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.gui.dashboard.FlagService;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

/**
 * <a href="https://github.com/NilIQW/">Author: NilIQW</a>
 */

public class CreateEmployeeController implements IController<Model> {

    @FXML
    private TextField firstNameField, lastNameField, annualSalaryField, overheadMultiplierField, fixedAnnualAmountField, annualWorkingHoursField, utilizationField;
    @FXML
    private ComboBox<Country> countryCombo;
    @FXML
    private ComboBox<String> teamCombo;
    @FXML
    private ComboBox<String> typeCombo;
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
//        countryCombo.setValue();
        countryCombo.setItems(countries);

        countryCombo.setCellFactory(param -> new ListCell<Country>() {
            @Override
            protected void updateItem(Country country, boolean empty) {
                super.updateItem(country, empty);
                if (empty || country == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    ImageView flagImageView = FlagService.getFlagImageView(country.getCode());
                    if (flagImageView != null) {
                        flagImageView.setFitWidth(20);
                        flagImageView.setFitHeight(20);
                        setGraphic(flagImageView);
                        setText(country.getValue());
                    } else {
                        setText(country.getValue());
                    }
                }
            }
        });

        countryCombo.setOnKeyPressed(event -> {
            if (event.getCode().isLetterKey()) {
                String typedText = event.getText();
                filterCountriesByFirstLetter(typedText);
            }
        });

        typeCombo.getItems().addAll("Production Resource", "Overhead");
        typeCombo.setValue("Choose Type");
    }
    private void filterCountriesByFirstLetter(String letter) {
        ObservableList<Country> countryList = FXCollections.observableArrayList(Country.values());

        //filter the list by the first typed letter
        ObservableList<Country> filteredList = countryList.filtered(country ->
                country.getValue().toLowerCase().startsWith(letter.toLowerCase()));

        countryCombo.setItems(filteredList);
        countryCombo.getSelectionModel().selectFirst();
    }

    @FXML
    private void onSubmitButton(ActionEvent actionEvent) {
        try {
            // Filling the values from user input
            model.setFirstName(firstNameField.getText());
            model.setLastName(lastNameField.getText());
            model.setCountry(countryCombo.getValue());
            model.setTeam("Not Implemented"); // ToDo: Needs to be implemented to show
            model.setAnnualSalary(annualSalaryField.getText());

            // I need to set a country and team manually here because I don't want to mess with this page :D
            // Just for example and prevent error
            // You can implement your methods
            model.setCountry(countryCombo.getValue());
            model.setTeam("AreYouA1or0?"); // Even if you are, try not to be

            // ToDo: Keep going with other fields as needed.
            // ToDo: Don't forget to check for null inputs.

            // Trigger the final action for creating an employee
            model.createEmployee(model.getEmployee());

            // For type choiceBox, the logic should be if the selected value iss Overhead, return true, else false


            // Close the stage if it was successful
            Window.closeStage(firstNameField.getScene());

        } catch (ExceptionHandler e){
            // Throw an alert error ToDo: Add a custom error message :D
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    // ToDo: Method to check for null inputs

}
