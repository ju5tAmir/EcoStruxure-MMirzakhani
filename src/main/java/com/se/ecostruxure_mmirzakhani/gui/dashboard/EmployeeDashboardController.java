package com.se.ecostruxure_mmirzakhani.gui.dashboard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EmployeeDashboardController implements IController{

    @FXML
    private Label dashboardLabel;
    @Override
    public void setModel(Object model) {
        // Here we will implement loading the Employee Table once the Dashboard label is clicked, just like in the website
        dashboardLabel.setOnMouseClicked(event -> {
            System.out.println("Label clicked!");
        });
    }
}
