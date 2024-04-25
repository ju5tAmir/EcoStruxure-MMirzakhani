package com.se.ecostruxure_mmirzakhani.gui.dashboard;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.model.Model;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateEmployee {

    private Model model;

    public CreateEmployee() {
        this.model = new Model();
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    private TextField nameField;
    @FXML
    private TextField salaryField;
    @FXML
    private TextField overheadMultiplierField;
    @FXML
    private TextField fixedAnnualAmountField;
    @FXML
    private SplitMenuButton countryField;
    @FXML
    private SplitMenuButton teamField;
    @FXML
    private TextField annualWorkingHoursField;
    @FXML
    private TextField utilizationField;
    @FXML
    private SplitMenuButton employeeTypeField;

    @FXML
    private void onSubmitButton() {
        // Get selected text from SplitMenuButton
        String selectedCountry = countryField.getText();
        String selectedTeam = teamField.getText();
        Boolean isPermanent = Boolean.parseBoolean(employeeTypeField.getText());

        Employee newEmployee = new Employee(
                nameField.getText(),
                Double.parseDouble(salaryField.getText()),
                Double.parseDouble(overheadMultiplierField.getText()),
                Double.parseDouble(fixedAnnualAmountField.getText()),
                selectedCountry,
                selectedTeam,
                Double.parseDouble(annualWorkingHoursField.getText()),
                Double.parseDouble(utilizationField.getText()),
                isPermanent
        );

        this.model.createEmployee(newEmployee);
        this.model.updateEmployeeList();

        Scene scene = (Scene) nameField.getScene();
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }

    @FXML
    private void onCancel() {
        Scene scene = (Scene) nameField.getScene();
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }
}