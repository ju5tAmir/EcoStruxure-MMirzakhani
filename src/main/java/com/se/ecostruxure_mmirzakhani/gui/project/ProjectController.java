package com.se.ecostruxure_mmirzakhani.gui.project;

import com.se.ecostruxure_mmirzakhani.bll.rate.RateService;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;

import java.util.Date;

public class ProjectController implements IController<Model> {
    @FXML
    private TableView<RateService> projectTable;
    @FXML
    private TableColumn<RateService, String> projectName;
    @FXML
    private TableColumn<RateService, Double> projectHourlyRate, projectDailyRate;

    private Model model;

    @Override
    public void setModel(Model model) {
        this.model = model;

        projectTable.setItems(model.getAllRates());

        setColumns();
        setListeners();
    }


    private void setColumns(){
        projectName.setCellValueFactory(cellData -> {
            String projectName = cellData.getValue().getProject().getName();

            return new SimpleStringProperty(projectName);
        });

        projectHourlyRate.setCellValueFactory(cellData -> {
            double hourlyRate = cellData.getValue().getHourlyRate();

            return new SimpleDoubleProperty(hourlyRate).asObject();
        });

        projectDailyRate.setCellValueFactory(cellData -> {
            double dailyRate = cellData.getValue().getDailyRate();

            return new SimpleDoubleProperty(dailyRate).asObject();
        });
    }

    private void setListeners(){
        // Project table items listener
        projectTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                model.setProject(newValue.getProject());
                try {
                    Window.createStage(WindowType.PROJECT_MANAGER, model, Modality.WINDOW_MODAL, false);
                } catch (ExceptionHandler e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }
}
