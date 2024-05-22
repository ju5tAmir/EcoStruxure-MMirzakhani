package com.se.ecostruxure_mmirzakhani.gui.dashboard;

import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.gui.gui_utils.GUIHelper;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements IController<Model> {
    @FXML
    private Label   welcomeLabel;
    @FXML
    private Label   totalTeamsLabel,    totalEmployeesLabel,    totalHourlyRateLabel,   totalDailyRateLabel,    totalCostLabel;
    @FXML
    private Label   teamGrowthLabel,    employeesGrowthLabel,   hourlyRateGrowthLabel,  dailyRateGrowthLabel,   totalCostGrowthLabel;
    @FXML
    private VBox    table, tableItems;

    private final CustomTable customTable = new CustomTable();
    private Model model;

    // ToDo: Currency sign
    @Override
    public void setModel(Model model) {
        this.model = model;
        updateLabel(welcomeLabel,           "Welcome, User!");
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
            updateLabel(totalTeamsLabel,            String.valueOf(model.getProjects().size()));
            updateLabel(totalEmployeesLabel,        String.valueOf(model.getAllEmployees().size()));
            updateLabel(totalHourlyRateLabel, GUIHelper.currencyFormatter(model.getTotalHourlyRate()));
            updateLabel(totalDailyRateLabel,    GUIHelper.currencyFormatter(model.getTotalDailyRate()));
            updateLabel(totalCostLabel,         GUIHelper.currencyFormatter(model.getTotalCost()));
        } catch (ExceptionHandler e){
            throw new RuntimeException(e);
        }
    }


}
