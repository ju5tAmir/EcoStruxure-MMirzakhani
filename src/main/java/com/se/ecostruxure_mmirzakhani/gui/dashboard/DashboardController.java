package com.se.ecostruxure_mmirzakhani.gui.dashboard;

import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.gui.gui_utils.GUIHelper;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.AlertHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;



public class DashboardController implements IController<Model> {
    @FXML
    private Label   welcomeLabel;
    @FXML
    private Label   totalTeamsLabel,    totalEmployeesLabel,    totalHourlyRateLabel,   totalDailyRateLabel,    totalCostLabel;
    @FXML
    private Label   teamGrowthLabel,    employeesGrowthLabel,   hourlyRateGrowthLabel,  dailyRateGrowthLabel,   totalCostGrowthLabel;
    @FXML
    private VBox    table, tableItems;

    private Model model;

    @Override
    public void setModel(Model model) {
        this.model = model;
        updateLabel(welcomeLabel, "Welcome, User!"); // As program grows, it should be changes with username
        updateTotalValues();

    }

    /**
     * Update a label with provided text
     */
    private void updateLabel(Label label, String text){
        label.setText(text);
    }

    private void updateTotalValues(){
        try {
            updateLabel(totalTeamsLabel,            String.valueOf(model.getAllProjects().size()));
            updateLabel(totalEmployeesLabel,        String.valueOf(model.getAllEmployees().size()));
            updateLabel(totalHourlyRateLabel,       GUIHelper.currencyFormatter(model.getTotalHourlyRate()));
            updateLabel(totalDailyRateLabel,        GUIHelper.currencyFormatter(model.getTotalDailyRate()));
            updateLabel(totalCostLabel,             GUIHelper.currencyFormatter(model.getTotalCosts()));
        } catch (ExceptionHandler e){
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }


}
