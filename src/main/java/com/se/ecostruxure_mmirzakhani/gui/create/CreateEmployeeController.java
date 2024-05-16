package com.se.ecostruxure_mmirzakhani.gui.create;

import com.se.ecostruxure_mmirzakhani.be.Country;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Region;
import com.se.ecostruxure_mmirzakhani.be.Team;
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

import java.math.BigDecimal;

// ToDo: Don't forget to check for null inputs.
//       (Optional) Validate if user clicked on a field and left it empty (then shows a little warn), also if they put invalid name, shows an error after Focus moved

public class CreateEmployeeController implements IController<Model> {

    @FXML
    private TextField firstNameField, lastNameField, annualSalaryField, overheadMultiplierField, fixedAnnualAmountField, annualWorkingHoursField, utilizationField, averageDailyHoursField;
    @FXML
    private ComboBox<Country> countryCombo;
    @FXML
    private ComboBox teamCombo;
    @FXML
    private ComboBox<String> typeCombo;
    private Model model;
    private Employee selectedEmployee;

    @Override
    public void setModel(Model model) {
        this.model = model;

        this.model.initEmployee();
        try {
            populateComboBoxes();
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
        selectedEmployee = model.getSelectedEmployee();

    }

    private void populateComboBoxes() throws ExceptionHandler {

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

        teamCombo.getItems().addAll("Team A", "Team B","Team C");
        //teamCombo.setItems(model.getAllTeams());
        //teamCombo.setValue(model.getAllTeams().get(0));
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
        if (model.getSelectedEmployee()==null){
            createEmployee();
        } else {
            updateEmployee();
        }
    }

    private void updateEmployee() {
        if(selectedEmployee!=null) {
            try {
                BigDecimal annualSalary = new BigDecimal(annualSalaryField.getText());
                BigDecimal fixedAnnualAmount = new BigDecimal(fixedAnnualAmountField.getText());
                double annualWorkingHours = Double.parseDouble(annualWorkingHoursField.getText());
                double utilizationPercentage = Double.parseDouble(utilizationField.getText());
                double overheadMultiplier = Double.parseDouble(overheadMultiplierField.getText());
                double dailyAverageWorkHours = Double.parseDouble(averageDailyHoursField.getText());
                String selectedType = typeCombo.getValue();
                // Set the isOverhead value based on the selected type
                boolean isOverhead = selectedType.equals("Overhead");

                selectedEmployee.setFirstName(firstNameField.getText());
                selectedEmployee.setLastName(lastNameField.getText());
                selectedEmployee.setCountry(countryCombo.getValue());
                selectedEmployee.setTeam((Team) teamCombo.getValue());
                selectedEmployee.getContract().setAnnualSalary(annualSalary);
                selectedEmployee.getContract().setFixedAnnualAmount(fixedAnnualAmount);
                selectedEmployee.getContract().setAnnualWorkHours(annualWorkingHours);
                selectedEmployee.getContract().setAverageDailyWorkHours(dailyAverageWorkHours);
                selectedEmployee.getContract().setUtilizationPercentage(utilizationPercentage);
                selectedEmployee.getContract().setOverhead(isOverhead);
                selectedEmployee.getContract().setOverheadPercentage(overheadMultiplier);
                selectedEmployee.setRegion(Region.EUROPE); //Todo : to be implemented through gui
                model.updateEmployee(selectedEmployee);



                // Close the stage if it was successful
                Window.closeStage(firstNameField.getScene());

                AlertHandler.displayAlert("Employee updated successfully.", Alert.AlertType.INFORMATION);
            } catch (ExceptionHandler e) {
                AlertHandler.displayAlert("Error updating employee: " + e.getMessage(), Alert.AlertType.ERROR);
            }}
    }

    // ToDo: Method to check for null inputs

    public void createEmployee(){
        try {
            double annualSalary = Double.parseDouble(annualSalaryField.getText());
            double fixedAnnualAmount = Double.parseDouble(fixedAnnualAmountField.getText());
            double annualWorkingHours = Double.parseDouble(annualWorkingHoursField.getText());
            double utilizationPercentage = Double.parseDouble(utilizationField.getText());
            double overheadMultiplier = Double.parseDouble(overheadMultiplierField.getText());
            double dailyAverageWorkHours = Double.parseDouble(averageDailyHoursField.getText());
            // Filling the values from user input
            model.setFirstName(firstNameField.getText());
            model.setLastName(lastNameField.getText());
            model.setCountry(countryCombo.getValue());
            model.setRegion(Region.EUROPE);    // ToDo: Needs to be implemented correctly
            model.setAverageDailyWorkHours(averageDailyHoursField.getText());
            // model.setOverheadStatus(typeCombo); ToDo: Needs to be fixed -> change DB to accept the type as overhead or production resource, instead of boolean
            //     ToDo: For type choiceBox, the logic should be if the selected value iss Overhead, return true, else false

            model.setTeam(teamCombo.getValue().toString());
            model.setAnnualSalary(annualSalary);
            model.setFixedAnnualAmount(fixedAnnualAmount);
            model.setAnnualWorkHours(annualWorkingHours);
            model.setAverageDailyWorkHours(dailyAverageWorkHours);
            model.setUtilizationPercentage(utilizationPercentage);
            String selectedType = typeCombo.getValue();
            // Set the isOverhead value based on the selected type
            boolean isOverhead = selectedType.equals("Overhead");
            // Set the isOverhead value in the model
            model.setOverhead(isOverhead);
            model.setOverheadPercentage(overheadMultiplier);
            model.createEmployee();

            // Close the stage if it was successful
            Window.closeStage(firstNameField.getScene());

        } catch (NumberFormatException | ExceptionHandler e) {
            AlertHandler.displayAlert("Please enter valid numerical values.", Alert.AlertType.ERROR);
        }

    }

}
