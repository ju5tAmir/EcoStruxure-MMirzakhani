package com.se.ecostruxure_mmirzakhani.utils;

import javafx.scene.control.Alert;

public class AlertHandler {

    public static void displayAlert(String message, Alert.AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
