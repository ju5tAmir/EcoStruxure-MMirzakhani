package com.se.ecostruxure_mmirzakhani.gui.dashboard;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.be.Employee;

public class EditEmployee {

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

    private Model model;
    private Employee employeeToEdit;

    public EditEmployee() {
        // Default constructor
    }

    public Employee getEmployeeToEdit() {
        return employeeToEdit;
    }

    public void setEmployeeToEdit(Employee employeeToEdit) {
        this.employeeToEdit = employeeToEdit;
    }

    @FXML
    public void initialize() {
        nameField.setText(employeeToEdit.getName());
        salaryField.setText(employeeToEdit.getAnnualSalary().toString());
        overheadMultiplierField.setText(employeeToEdit.getOverheadMultiplier().toString());
        fixedAnnualAmountField.setText(employeeToEdit.getFixedAnnualAmount().toString());
        countryField.setText(employeeToEdit.getCountry());
        teamField.setText(employeeToEdit.getTeam());
        annualWorkingHoursField.setText(employeeToEdit.getAnnualWorkingHours().toString());
        utilizationField.setText(employeeToEdit.getUtilization().toString());
        employeeTypeField.setText(employeeToEdit.getEmployeeType().toString());
    }

    @FXML
    private void onUpdateButton() {
        employeeToEdit.setName(nameField.getText());
        employeeToEdit.setAnnualSalary(Double.parseDouble(salaryField.getText()));
        employeeToEdit.setOverheadMultiplier(Double.parseDouble(overheadMultiplierField.getText()));
        employeeToEdit.setFixedAnnualAmount(Double.parseDouble(fixedAnnualAmountField.getText()));
        employeeToEdit.setCountry(countryField.getText());
        employeeToEdit.setTeam(teamField.getText());
        employeeToEdit.setAnnualWorkingHours(Double.parseDouble(annualWorkingHoursField.getText()));
        employeeToEdit.setUtilization(Double.parseDouble(utilizationField.getText()));
        employeeToEdit.setEmployeeType(Boolean.parseBoolean(employeeTypeField.getText()));

        model.updateEmployeeList(); // Updates the list after modification
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