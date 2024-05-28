package com.se.ecostruxure_mmirzakhani.gui.employee.create;

import com.se.ecostruxure_mmirzakhani.be.entities.Contract;
import com.se.ecostruxure_mmirzakhani.be.entities.Employee;
import com.se.ecostruxure_mmirzakhani.be.enums.Currency;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;
import com.se.ecostruxure_mmirzakhani.utils.AlertHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.gui.gui_utils.GUIHelper;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.Window;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CreateEmployeeController implements IController<Model> {

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

        addFocusListener(firstName);
        addFocusListener(lastName);
        addFocusListener(emailAddress);

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

            if (model.createEmployee()){
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



    private void addFocusListener(TextField textField) {
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue){
                try {
                    addListener(textField);
                    validate(textField);
                } catch (RuntimeException e){
                    displayErrorFlag(textField);
                }
            }
        });
    }

    // Add various listeners to the text changes
    private void addListener(TextField textField){
        addTextListener(textField);
        addSceneListener(textField);

    }


    private void addTextListener(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                try {
                    validate(textField);
                } catch (RuntimeException e){
                    displayErrorFlag(textField);
                }
            }
        });
    }

    /**
     * Removes focus from a node when user clicked on anywhere else than on the specific node IF
     */
    private void addSceneListener(Node node){
        // If user clicked on anywhere on the scene but nodes
        node.getScene().setOnMousePressed(event -> {
            if (!node.equals(event.getSource())) {
                try {
                    validate((TextField) node);
                } catch (RuntimeException e){
                    displayErrorFlag(node);
                    node.getScene().getRoot().requestFocus();
                }
            }
        });
    }

    private void displaySuccessFlag(Node node){
        removeErrorFlags(node);
        node.getStyleClass().add("success");

    }

    private void displayErrorFlag(Node node){
        removeSuccessFlag(node);
        node.getStyleClass().add("red-focus");
        node.getStyleClass().add("error");
    }

    private void removeErrorFlags(Node node){
        node.getStyleClass().removeIf(style -> style.equals("red-focus"));
        node.getStyleClass().removeIf(style -> style.equals("error"));
    }

    private void removeSuccessFlag(Node node){
        node.getStyleClass().removeIf(style -> style.equals("success"));
    }

    private void validate(TextField textField) throws RuntimeException {
        if (textField == firstName){
            GUIHelper.validateName(textField.getText());
        } else if (textField == lastName) {
            GUIHelper.validateName(textField.getText());
        } else if (textField == emailAddress) {
            GUIHelper.validateEmail(textField.getText());
        }

        displaySuccessFlag(textField);
    }

    private void closeTheWindow(){
        Window.closeStage((Stage) firstName.getScene().getWindow());
    }
}


