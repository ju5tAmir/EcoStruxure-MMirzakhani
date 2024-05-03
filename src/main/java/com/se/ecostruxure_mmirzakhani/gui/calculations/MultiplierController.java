package com.se.ecostruxure_mmirzakhani.gui.calculations;

import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class MultiplierController implements IController<Model> {
    private Model model;

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    private TextField markupTextField;
    @FXML
    private TextField gmTextField;

    @FXML
    public void initTextFields() {

        setNumericValidation(markupTextField);
        setNumericValidation(gmTextField);
        double Markup = model.setMarkup();
        double GM = model.setGM();
    }

    private void setNumericValidation(TextField textField) {

        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                try {
                    double value = Double.parseDouble(textField.getText());
                    if (value < 0 || value > 100) {
                        showError("The value must be between 0 and 100.");
                        textField.requestFocus();
                    }
                } catch (NumberFormatException e) {
                    showError("The value must be between 0 and 100.");
                    textField.requestFocus();
                }
            }
        });
    }

    @FXML
    private void onSave() {
        try {
            double markup = Double.parseDouble(markupTextField.getText());
            double gm = Double.parseDouble(gmTextField.getText());

            if (markup < 0 || markup > 100 || gm < 0 || gm > 100) {
                showAlert("Values must be between 0 and 100.", Alert.AlertType.WARNING);
                return;
            }

            showAlert("Values saved successfully.", Alert.AlertType.INFORMATION);

        } catch (NumberFormatException e) {
            showAlert("Please enter valid numeric values.", Alert.AlertType.ERROR);
        }
    }


    @FXML
    private void onCancel() {

        Scene scene = (Scene) markupTextField.getScene();
        Stage stage = (Stage) scene.getWindow();
        stage.close();
    }



    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.show();
    }


    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


