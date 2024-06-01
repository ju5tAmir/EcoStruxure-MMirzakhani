package com.se.ecostruxure_mmirzakhani.gui.history;

import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class HistoryController implements IController<Model> {
    @FXML
    private DatePicker fromDate, toDate;
    private Model model;

    @Override
    public void setModel(Model model) {
        this.model = model;


    }


    @FXML
    private void onDailyButton(){

    }

    @FXML
    private void onMonthlyButton(){

    }
}
