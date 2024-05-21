package com.se.ecostruxure_mmirzakhani.gui.employee.create;

import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.gui.gui_utils.GUIHelper;
import com.se.ecostruxure_mmirzakhani.model.Model;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class CreateEmployeeController implements IController<Model> {

    @FXML
    private TextField firstName, lastName, emailAddress;

    private Model model;
    @Override
    public void setModel(Model model) {
        this.model = model;

        addFocusListener(firstName);
        addFocusListener(lastName);
        addFocusListener(emailAddress);
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


