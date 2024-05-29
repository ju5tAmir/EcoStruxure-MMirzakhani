package com.se.ecostruxure_mmirzakhani.gui.project;

import com.se.ecostruxure_mmirzakhani.be.entities.Project;
import com.se.ecostruxure_mmirzakhani.bll.rate.RateService;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.gui.gui_utils.GUIHelper;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.AlertHandler;
import com.se.ecostruxure_mmirzakhani.utils.Window;
import com.se.ecostruxure_mmirzakhani.utils.WindowType;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;

public class ProjectController implements IController<Model> {
    @FXML
    private CheckBox markupCheckBox, gmCheckBox;
    @FXML
    private Slider markupSlider, gmSlider;
    @FXML
    private TextField markupTextField, gmTextField;

    // Projects table attributes
    @FXML
    private TableView<Project> projectTable;
    @FXML
    private TableColumn<Project, String> projectName, projectHourlyRate, projectDailyRate, projectTotalCost;

    // Multipliers table attributes
    @FXML
    private TableView<Project> multipliersTable;
    @FXML
    private TableColumn<Project, String> projectNameM, projectHourlyRateM, projectDailyRateM, projectTotalCostM;

    private Model model;

    @Override
    public void setModel(Model model) {
        this.model = model;

        try {
            setProjectTable();
            initMultipliers();
        } catch (ExceptionHandler e){
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    private void setProjectTable() throws ExceptionHandler {
        projectTable.setItems(model.getAllProjects());

        projectName.setCellValueFactory(cellData -> {
            String projectName = cellData.getValue().getName();

            return new SimpleStringProperty(projectName);
        });

        projectHourlyRate.setCellValueFactory(cellData -> {
            double hourlyRate = model.getHourlyRate(cellData.getValue());

            String formattedString = GUIHelper.currencyFormatter(hourlyRate);

            return new SimpleStringProperty(formattedString);
        });

        projectDailyRate.setCellValueFactory(cellData -> {
            double dailyRate = model.getDailyRate(cellData.getValue());

            String formattedString = GUIHelper.currencyFormatter(dailyRate);

            return new SimpleStringProperty(formattedString);
        });

        projectTotalCost.setCellValueFactory(cellData -> {
            double totalCost = model.getTotalCosts(cellData.getValue());

            String formattedString = GUIHelper.currencyFormatter(totalCost);

            return new SimpleStringProperty(formattedString);
        });
    }



    @FXML
    private void onCreateProject(){
        try {
            Window.createStage(WindowType.CREATE_PROJECT, model, Modality.WINDOW_MODAL, false);
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }








    private double getMultiplier(TextField textField) {
        try {
            double value = Double.parseDouble(textField.getText());
            if (value < 0 || value > 100) {
                throw new IllegalArgumentException("Multiplier must be between 0 and 100.");
            }
            return value / 100;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please enter a number between 0 and 100.");
        }
    }


    private void initMultipliers() {
        // Sync textfield with slider


        markupSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            markupTextField.setText(String.format("%.2f", newValue.doubleValue()));
        }
        });
        gmSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            gmTextField.setText(String.format("%.2f", newValue.doubleValue()));
        }
        });

        markupCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue) {
            markupSlider.setDisable(false);
            markupTextField.setDisable(false);
        } else {
            markupSlider.setDisable(true);
            markupTextField.setDisable(true);
        }
        });

        markupCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue) {
            enableNode(markupSlider);
            enableNode(markupTextField);
        } else {
            disableNode(markupSlider);
            disableNode(markupTextField);
        }
        });

        gmCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue) {
            enableNode(gmSlider);
            enableNode(gmTextField);
        } else {
            disableNode(gmSlider);
            disableNode(gmTextField);
        }
        });
    }

    private void disableNode(Node node) {
        node.setDisable(true);
    }
    private void enableNode(Node node) {
        node.setDisable(false);

    }



//
    @FXML
    private void onCalculate() {

        try {
            multipliersTable.setItems(model.getAllProjects());
        } catch (ExceptionHandler e) {
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }

        if (markupCheckBox.isSelected() && gmCheckBox.isSelected()) {
            projectNameM.setCellValueFactory(cellData -> {
                String projectName = cellData.getValue().getName();

                return new SimpleStringProperty(projectName);
            });

            projectHourlyRateM.setCellValueFactory(cellData -> {
                double originalHourlyRate = model.getHourlyRate(cellData.getValue()); // Hourly rate for the project
                double gmApplied = model.applyGrossMarginMultiplier(originalHourlyRate, getMultiplier(gmTextField));
                double markupApplied = model.applyMarkupMultiplier(gmApplied, getMultiplier(markupTextField));

                String formattedString = GUIHelper.currencyFormatter(markupApplied);

                return new SimpleStringProperty(formattedString);
            });

            projectDailyRateM.setCellValueFactory(cellData -> {
                double originalDailyRate = model.getDailyRate(cellData.getValue()); // Daily rate for the project
                double gmApplied = model.applyGrossMarginMultiplier(originalDailyRate, getMultiplier(gmTextField));
                double markupApplied = model.applyMarkupMultiplier(gmApplied, getMultiplier(markupTextField));

                String formattedString = GUIHelper.currencyFormatter(markupApplied);

                return new SimpleStringProperty(formattedString);
            });

            projectTotalCostM.setCellValueFactory(cellData -> {
                double originalTotalCosts = model.getTotalCosts(cellData.getValue()); // Total cost for the project
                double gmApplied = model.applyGrossMarginMultiplier(originalTotalCosts, getMultiplier(gmTextField));
                double markupApplied = model.applyMarkupMultiplier(gmApplied, getMultiplier(markupTextField));

                String formattedString = GUIHelper.currencyFormatter(markupApplied);

                return new SimpleStringProperty(formattedString);
            });
        } else if (gmCheckBox.isSelected()) {
            projectNameM.setCellValueFactory(cellData -> {
                String projectName = cellData.getValue().getName();

                return new SimpleStringProperty(projectName);
            });

            projectHourlyRateM.setCellValueFactory(cellData -> {
                double originalHourlyRate = model.getHourlyRate(cellData.getValue());
                double gmHourlyRate = model.applyGrossMarginMultiplier(originalHourlyRate, getMultiplier(gmTextField));

                String formattedString = GUIHelper.currencyFormatter(gmHourlyRate);

                return new SimpleStringProperty(formattedString);
            });

            projectDailyRateM.setCellValueFactory(cellData -> {
                double originalDailyRate = model.getDailyRate(cellData.getValue());
                double gmDailyRate = model.applyGrossMarginMultiplier(originalDailyRate, getMultiplier(gmTextField));

                String formattedString = GUIHelper.currencyFormatter(gmDailyRate);

                return new SimpleStringProperty(formattedString);
            });

            projectTotalCostM.setCellValueFactory(cellData -> {
                double originalTotalCosts = model.getTotalCosts(cellData.getValue());
                double gmTotalCost = model.applyGrossMarginMultiplier(originalTotalCosts, getMultiplier(gmTextField));

                String formattedString = GUIHelper.currencyFormatter(gmTotalCost);

                return new SimpleStringProperty(formattedString);
            });
        } else if (markupCheckBox.isSelected()) {
            projectNameM.setCellValueFactory(cellData -> {
                String projectName = cellData.getValue().getName();

                return new SimpleStringProperty(projectName);
            });

            projectHourlyRateM.setCellValueFactory(cellData -> {
                double originalHourlyRate = model.getHourlyRate(cellData.getValue());
                double markupHourly = model.applyMarkupMultiplier(originalHourlyRate, getMultiplier(markupTextField));

                String formattedString = GUIHelper.currencyFormatter(markupHourly);

                return new SimpleStringProperty(formattedString);
            });

            projectDailyRateM.setCellValueFactory(cellData -> {
                double originalDailyRate = model.getDailyRate(cellData.getValue());
                double markupDaily = model.applyMarkupMultiplier(originalDailyRate, getMultiplier(markupTextField));

                String formattedString = GUIHelper.currencyFormatter(markupDaily);

                return new SimpleStringProperty(formattedString);
            });

            projectTotalCostM.setCellValueFactory(cellData -> {
                double originalTotalCost = model.getTotalCosts(cellData.getValue());
                double markupTotalCost = model.applyMarkupMultiplier(originalTotalCost, getMultiplier(markupTextField));

                String formattedString = GUIHelper.currencyFormatter(markupTotalCost);

                return new SimpleStringProperty(formattedString);
            });
        }
    }

    @FXML
    private void onReset(){

    }

    @FXML
    private void onUpdateProject(){
        if (projectTable.getSelectionModel().getSelectedItem() != null){
            model.setProject(projectTable.getSelectionModel().getSelectedItem());
            try {
                Window.createStage(WindowType.UPDATE_PROJECT, model, Modality.WINDOW_MODAL, false);
            } catch (ExceptionHandler e) {
                throw new RuntimeException(e);
            }
        } else {
            AlertHandler.displayAlert("Please select a project first in order update.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void onDeleteProject(){
        if (!projectTable.getSelectionModel().isEmpty()){
            try {
                if (model.deleteProject(projectTable.getSelectionModel().getSelectedItem())){
                    AlertHandler.displayAlert(ExceptionMessage.SUCCESSFUL.getValue(), Alert.AlertType.INFORMATION);
                };
            } catch (ExceptionHandler e) {
                AlertHandler.displayAlert(ExceptionMessage.FAILURE.getValue(), Alert.AlertType.ERROR);
            }

        } else {
            AlertHandler.displayAlert("Please select a project first in order delete.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void onViewProject(){
        if (projectTable.getSelectionModel().getSelectedItem() != null){
            model.setProject(projectTable.getSelectionModel().getSelectedItem());
            try {
                Window.createStage(WindowType.PROJECT_MANAGER, model, Modality.WINDOW_MODAL, false);
            } catch (ExceptionHandler e) {
                throw new RuntimeException(e);
            }
        } else {
            AlertHandler.displayAlert("Please select a project first in order view.", Alert.AlertType.INFORMATION);
        }
    }
}
