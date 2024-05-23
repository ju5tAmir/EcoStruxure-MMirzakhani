package com.se.ecostruxure_mmirzakhani.gui.project.management;

import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.gui.gui_utils.GUIHelper;
import com.se.ecostruxure_mmirzakhani.model.Model;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProjectManagementController implements IController<Model> {

    @FXML
    private Label projectName, countryLabel, totalEmployees, overheadEmployees, productionEmployees, totalTeams, hourlyRate, dailyRate, directCosts, overheadCosts, totalCosts;

    @FXML
    private TableView<Assignment> employeesTable;
    @FXML
    private TableColumn<Assignment, String> employeeFirstName;
    @FXML
    private TableColumn<Assignment, String> employeeLastName;
    @FXML
    private TableColumn<Assignment, String> employeeTeam;
    @FXML
    private TableColumn<Assignment, Double> employeeUtilPercentage;
    @FXML
    private TableColumn<Assignment, String> employeeType;


    private Model model;
    @Override
    public void setModel(Model model) {
        this.model = model;

        setLabels();
        setEmployeeTable();

    }

    private void setEmployeeTable(){
        employeesTable.setItems(model.getProjectMembers(model.getProject()));

        employeeFirstName.setCellValueFactory(cellData -> {
            String firstName = cellData.getValue().getEmployee().getFirstName();

            return new SimpleStringProperty(firstName);
        });

        employeeLastName.setCellValueFactory(cellData -> {
            String lastName = cellData.getValue().getEmployee().getLastName();

            return new SimpleStringProperty(lastName);
        });

        employeeTeam.setCellValueFactory(cellData -> {
            String teamName = cellData.getValue().getTeam().getName();

            return new SimpleStringProperty(teamName);
        });

        employeeUtilPercentage.setCellValueFactory(cellData -> {
            double utilizationPercentage = cellData.getValue().getUtilizationPercentage();

            return new SimpleDoubleProperty(utilizationPercentage).asObject();
        });

        employeeType.setCellValueFactory(cellData -> {
            String teamName = cellData.getValue().getEmployee().getContract().isOverhead()? "Overhead": "Product Resource";

            return new SimpleStringProperty(teamName);
        });
    }


    private void setLabels(){
        projectName.setText(model.getProject().getName());
        countryLabel.setText(model.getProject().getCountry().getValue());;

        totalEmployees.setText(String.valueOf(model.getProjectMembers(model.getProject()).size()));
        overheadEmployees.setText(String.valueOf(model.getRate(model.getProject()).getOverheadEmployees().size()));
        productionEmployees.setText(String.valueOf(model.getRate(model.getProject()).getProductionEmployees().size()));

        totalTeams.setText(String.valueOf(model.getProjectTeams(model.getProject()).size()));
        // We use stringFormatter method because we need to show it pretty in the GUI :D
        hourlyRate.setText(GUIHelper.currencyFormatter(model.getRate(model.getProject()).getHourlyRate()));
        dailyRate.setText(GUIHelper.currencyFormatter(model.getRate(model.getProject()).getDailyRate()));

        // Costs
        directCosts.setText(GUIHelper.currencyFormatter(model.getRate(model.getProject()).getDirectCosts()));
        overheadCosts.setText(GUIHelper.currencyFormatter(model.getRate(model.getProject()).getOverheadCosts()));
        totalCosts.setText(GUIHelper.currencyFormatter(model.getRate(model.getProject()).getTotalCosts()));

    }
}
