package com.se.ecostruxure_mmirzakhani.gui;

import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
//import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements IController, Initializable {
    @FXML
    private Pane pane;
    private final Model model = new Model();

    @Override
    public void setModel(Object model) {

    }

    @FXML
    private void onDashboard() throws ExceptionHandler {
        Window.loadPane(pane, WindowType.DASHBOARD, model);
    }
    @FXML
    private void onEmployee() throws ExceptionHandler {
        Window.loadPane(pane, WindowType.EMPLOYEE_MAIN, model);
    }

    @FXML
    private void onTeam() throws ExceptionHandler {
        Window.loadPane(pane, WindowType.TEAM, model);
    }

    @FXML
    private void onProjects() throws ExceptionHandler {
        Window.loadPane(pane, WindowType.PROJECT_MAIN, model);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            model.setAllRates();
            onDashboard() ;

        } catch (ExceptionHandler e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
