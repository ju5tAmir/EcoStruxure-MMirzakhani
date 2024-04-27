package com.se.ecostruxure_mmirzakhani.gui.dashboard;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.model.Model;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateEmployeeController implements IController {

    private Model model;

    public CreateEmployeeController() {
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

    public void setEmployeeTableView(TableView<Employee> employeeTableView) {
        this.employeeTableView = employeeTableView;
    }

    @FXML
    private void onSubmitButton() {
        // Get selected text from SplitMenuButton
        String selectedCountry = countryChoice.getValue();
        String selectedTeam =  teamChoice.getValue();
        Boolean isPermanent = Boolean.parseBoolean(typeChoice.getValue());

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
        model.addEmployeeToList(newEmployee);

        Scene scene = (Scene) nameField.getScene();
        Stage stage = (Stage) scene.getWindow();
        stage.close();

        System.out.println(newEmployee);
    }

    @FXML
    private void onCancel() {
        Scene scene = (Scene) nameField.getScene();
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }

    @Override
    public void setModel(Object model) {

    }
}