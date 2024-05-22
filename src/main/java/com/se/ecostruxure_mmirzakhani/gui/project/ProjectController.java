package com.se.ecostruxure_mmirzakhani.gui.project;

import com.se.ecostruxure_mmirzakhani.bll.rate.RateService;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.gui.gui_utils.GUIHelper;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;

public class ProjectController implements IController<Model> {
    @FXML
    private TableView<RateService> projectTable;
    @FXML
    private TableColumn<RateService, String> projectName;
    @FXML
    private TableColumn<RateService, String> projectHourlyRate, projectDailyRate, projectTotalCost;

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

            String formattedString = GUIHelper.currencyFormatter(hourlyRate);

            return new SimpleStringProperty(formattedString);
        });

        projectDailyRate.setCellValueFactory(cellData -> {
            double dailyRate = cellData.getValue().getDailyRate();

            String formattedString = GUIHelper.currencyFormatter(dailyRate);

            return new SimpleStringProperty(formattedString);
        });

        projectTotalCost.setCellValueFactory(cellData -> {
            double totalCost = model.getRate(cellData.getValue().getProject()).getTotalCosts();

            String formattedString = GUIHelper.currencyFormatter(totalCost);

            return new SimpleStringProperty(formattedString);
        });
    }

    private void setListeners(){
        // Project table items listener
        projectTable.setOnMouseClicked(event -> {
            if (projectTable.getSelectionModel().getSelectedItem() != null){
                model.setProject(projectTable.getSelectionModel().getSelectedItem().getProject());
                try {
                    Window.createStage(WindowType.PROJECT_MANAGER, model, Modality.WINDOW_MODAL, false);
                } catch (ExceptionHandler e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }
}
