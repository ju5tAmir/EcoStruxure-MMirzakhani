package com.se.ecostruxure_mmirzakhani.gui.employee.update;

import com.se.ecostruxure_mmirzakhani.be.entities.Employee;
import com.se.ecostruxure_mmirzakhani.be.enums.Currency;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.AlertHandler;
import com.se.ecostruxure_mmirzakhani.utils.Window;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateEmployeeController implements IController<Model> {
    @FXML
    private TextField firstName, lastName, emailAddress;

    @FXML
    private TextField annualSalary, fixedAmount, annualWH, dailyWH, overheadPercentage;


    @FXML
    private Menu currencyMenu;
    private Currency selectedCurrency = Currency.EUR;

    private Model model;
    @Override
    public void setModel(Model model) {
        this.model = model;
        initCurrencyButton();
        fillFields();


    }

    private void fillFields(){
        // Current employee which user selected to edit
        Employee employee = model.getEmployee();

        firstName.setText(employee.getFirstName());
        lastName.setText(employee.getLastName());
        emailAddress.setText(employee.getEmail());

        annualSalary.setText(String.valueOf(employee.getContract().getAnnualSalary()));
        fixedAmount.setText(String.valueOf(employee.getContract().getFixedAnnualAmount()));
        annualWH.setText(String.valueOf(employee.getContract().getAnnualWorkHours()));
        dailyWH.setText(String.valueOf(employee.getContract().getAverageDailyWorkHours()));
        overheadPercentage.setText(String.valueOf(employee.getContract().getOverheadPercentage()));

    }
    @FXML
    private void onSubmitButton(){
        try {

            model.setEmployeeFirstName(firstName.getText());
            model.setEmployeeLastName(lastName.getText());
            model.setEmployeeEmail(emailAddress.getText());

            model.setContractAnnualSalary(Double.parseDouble(annualSalary.getText()));
            model.setContractFixedAnnualAmount(Double.parseDouble(fixedAmount.getText()));
            model.setContractAnnualWorkHours(Double.parseDouble(annualWH.getText()));
            model.setContractAverageDailyWorkHours(Double.parseDouble(dailyWH.getText()));
            model.setContractOverheadPercentage(Double.parseDouble(overheadPercentage.getText()));
            model.setContractCurrency(selectedCurrency);

            if (model.updateEmployee()){
                AlertHandler.displayAlert(ExceptionMessage.SUCCESSFUL.getValue(), Alert.AlertType.INFORMATION);
                closeTheWindow();
            } else {
                AlertHandler.displayAlert(ExceptionMessage.FAILURE.getValue(), Alert.AlertType.ERROR);
            }

        } catch (ExceptionHandler | RuntimeException ex) {
            AlertHandler.displayAlert(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onCancelButton(){
        closeTheWindow();
    }

    private void initCurrencyButton(){

        Currency[] currencies = Currency.values();

        for (Currency currency: currencies){
            MenuItem menuItem = new MenuItem(currency.toString());
            menuItem.setOnAction(event -> {
                selectedCurrency = Currency.valueOf(menuItem.getText());
                currencyMenu.setText(menuItem.getText());
            });
            currencyMenu.getItems().add(menuItem);
        }

    }

    private void closeTheWindow(){
        Window.closeStage((Stage) firstName.getScene().getWindow());
    }

}
