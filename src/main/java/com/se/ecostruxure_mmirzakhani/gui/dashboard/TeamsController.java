package com.se.ecostruxure_mmirzakhani.gui.dashboard;

import com.se.ecostruxure_mmirzakhani.exceptions.AlertHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class TeamsController implements IController<Model> {
    private Model model;
    @FXML
    private Label employeesLabel;
    @FXML
    private Label teamsLabel;
    @Override
    public void setModel(Model model) {
        this.model=model;

        employeesLabel.setOnMouseClicked(event -> {
            try {
                Window.createStage(WindowType.EMPLOYEE_DASHBOARD);
                Window.closeStage(employeesLabel.getScene());
            } catch (ExceptionHandler e) {
                throw new RuntimeException(e);
            }
        });
        teamsLabel.setOnMouseClicked(event -> {
            AlertHandler.displayAlert("Teams Dashboard is already open!", Alert.AlertType.INFORMATION);
        });

    }
}
