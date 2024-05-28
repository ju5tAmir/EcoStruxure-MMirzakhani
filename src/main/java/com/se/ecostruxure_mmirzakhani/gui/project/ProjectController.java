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

    @FXML
    private TableView<Project> projectTable;
    @FXML
    private TableColumn<Project, String> projectName;
    @FXML
    private TableColumn<Project, String> projectHourlyRate, projectDailyRate, projectTotalCost;

//    @FXML
//    private TableView<Project> projectTable1;
//    @FXML
//    private TableColumn<Project, String> projectName1;
//    @FXML
//    private TableColumn<Project, String> projectHourlyRate1, projectDailyRate1, projectTotalCost1;
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
            return value;
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

    public double hourlyRate(double hourlyRate, double gmPercentage) {
        double gmMultiplier = 1 - (gmPercentage / 100);
        return hourlyRate * gmMultiplier;
    }

    public double dailyRate(double dailyRate, double gmPercentage) {
        double gmMultiplier = 1 - (gmPercentage / 100);
        return dailyRate * gmMultiplier;
    }

    public double hourlyRate(double hourlyRate, double gmPercentage, boolean isMarkup) {
        double gmMultiplier = 1 + (gmPercentage / 100);
        return hourlyRate * gmMultiplier;
    }

    public double dailyRate(double dailyRate, double gmPercentage, boolean isMarkup) {
        double gmMultiplier = 1 + (gmPercentage / 100);
        return dailyRate * gmMultiplier;
    }

//
    @FXML
    private void onCalculate() {
    }
//        projectTable1.setItems(model.getAllRates());
//
//        if (markupCheckBox.isSelected() && gmCheckBox.isSelected()) {
//            projectName1.setCellValueFactory(cellData -> {
//                String projectName = cellData.getValue().getProject().getName();
//
//                return new SimpleStringProperty(projectName);
//            });
//
//            projectHourlyRate1.setCellValueFactory(cellData -> {
//                double markupHourlyRate = hourlyRate(cellData.getValue().getHourlyRate(), getMultiplier(markupTextField), true);
//                double gmHourlyRate = hourlyRate(markupHourlyRate, getMultiplier(gmTextField));
//
//
//                String formattedString = GUIHelper.currencyFormatter(gmHourlyRate);
//
//                return new SimpleStringProperty(formattedString);
//            });
//
//            projectDailyRate1.setCellValueFactory(cellData -> {
//                double markupDailyRate = dailyRate(cellData.getValue().getDailyRate(), getMultiplier(markupTextField), true);
//                double gmDailyRate = dailyRate(markupDailyRate, getMultiplier(gmTextField));
//
//                String formattedString = GUIHelper.currencyFormatter(gmDailyRate);
//
//                return new SimpleStringProperty(formattedString);
//            });
//
//            projectTotalCost1.setCellValueFactory(cellData -> {
//                double markupCost = hourlyRate(model.getRate(cellData.getValue().getProject()).getTotalCosts(), getMultiplier(markupTextField), true);
//                double gmCost = hourlyRate(markupCost, getMultiplier(gmTextField));
//
//                String formattedString = GUIHelper.currencyFormatter(gmCost);
//
//                return new SimpleStringProperty(formattedString);
//            });
//        } else if (gmCheckBox.isSelected()) {
//            projectName1.setCellValueFactory(cellData -> {
//                String projectName = cellData.getValue().getProject().getName();
//
//                return new SimpleStringProperty(projectName);
//            });
//
//            projectHourlyRate1.setCellValueFactory(cellData -> {
//                double gmHourlyRate = hourlyRate(cellData.getValue().getHourlyRate(), getMultiplier(gmTextField));
//
//                String formattedString = GUIHelper.currencyFormatter(gmHourlyRate);
//
//                return new SimpleStringProperty(formattedString);
//            });
//
//            projectDailyRate1.setCellValueFactory(cellData -> {
//                double gmDailyRate = dailyRate(cellData.getValue().getDailyRate(), getMultiplier(gmTextField));
//
//                String formattedString = GUIHelper.currencyFormatter(gmDailyRate);
//
//                return new SimpleStringProperty(formattedString);
//            });
//
//            projectTotalCost1.setCellValueFactory(cellData -> {
//                double gmCost = hourlyRate(model.getRate(cellData.getValue().getProject()).getTotalCosts(), getMultiplier(gmTextField));
//
//                String formattedString = GUIHelper.currencyFormatter(gmCost);
//
//                return new SimpleStringProperty(formattedString);
//            });
//        } else if (markupCheckBox.isSelected()) {
//            projectName1.setCellValueFactory(cellData -> {
//                String projectName = cellData.getValue().getProject().getName();
//
//                return new SimpleStringProperty(projectName);
//            });
//
//            projectHourlyRate1.setCellValueFactory(cellData -> {
//                double gmHourlyRate = hourlyRate(cellData.getValue().getHourlyRate(), getMultiplier(markupTextField), true);
//
//                String formattedString = GUIHelper.currencyFormatter(gmHourlyRate);
//
//                return new SimpleStringProperty(formattedString);
//            });
//
//            projectDailyRate1.setCellValueFactory(cellData -> {
//                double gmDailyRate = dailyRate(cellData.getValue().getDailyRate(), getMultiplier(markupTextField), true);
//
//                String formattedString = GUIHelper.currencyFormatter(gmDailyRate);
//
//                return new SimpleStringProperty(formattedString);
//            });
//
//            projectTotalCost1.setCellValueFactory(cellData -> {
//                double gmCost = hourlyRate(model.getRate(cellData.getValue().getProject()).getTotalCosts(), getMultiplier(markupTextField), true);
//
//                String formattedString = GUIHelper.currencyFormatter(gmCost);
//
//                return new SimpleStringProperty(formattedString);
//            });
//        }
//    }

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
