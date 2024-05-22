package com.se.ecostruxure_mmirzakhani.gui.employee.create;

import com.se.ecostruxure_mmirzakhani.be.*;
import com.se.ecostruxure_mmirzakhani.bll.ProjectService;
import com.se.ecostruxure_mmirzakhani.exceptions.AlertHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.gui.gui_utils.GUIHelper;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

public class CreateEmployeeController implements IController<Model> {

    @FXML
    private TextField firstName, lastName, emailAddress;
    @FXML
    private MenuButton currencyMenu;


    private Model model;
    @Override
    public void setModel(Model model) {
        this.model = model;

        Employee emp1 = new Employee();
        model.setEmployee(emp1);

        initCurrencyButton();

        addFocusListener(firstName);
        addFocusListener(lastName);
        addFocusListener(emailAddress);

    }



    @FXML
    private void onAssignButton(){

//        Project p = new Project();
//        p.setName("HHH");
//        p.setCountry(Country.COLOMBIA);
//
//        ProjectMember pm = new ProjectMember();
//        pm.setTeam(new Team(10, "Dev"));
//        pm.setEmployee(new Employee());
//        pm.setUtilizationPercentage(80);
//        model.addProjectMemberLinker(p, pm);
        try{
            Window.createStage(WindowType.ASSIGN_EMPLOYEE_PROJECT, model, Modality.WINDOW_MODAL, false);
        } catch (ExceptionHandler e){
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @FXML
    private void onSubmitButton(){

    }

    private void initCurrencyButton(){

        Currency[] currencies = Currency.values();

        for (Currency currency: currencies){
            MenuItem menuItem = new MenuItem(currency.toString());
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

    }


