package com.se.ecostruxure_mmirzakhani.gui.project.management;

import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.be.entities.Team;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.gui.gui_utils.GUIHelper;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.AlertHandler;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    @FXML
    private TableView<Team> teamsTable;
    @FXML
    private TableColumn<Team, String> teamName, teamOverheadCosts, teamDirectCosts, teamTotalCosts;

    private Model model;
    @Override
    public void setModel(Model model) {
        this.model = model;
        try {
            setLabels();
            setEmployeeTable();
            setTeamsTable();
        } catch (ExceptionHandler | RuntimeException e){
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void setEmployeeTable() throws ExceptionHandler {
        employeesTable.setItems(model.filter(model.getProject()));

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
            String teamName = cellData.getValue().getEmployeeType().name(); // Gets the enum string value of employee type

            return new SimpleStringProperty(teamName);
        });
    }

    private void setTeamsTable() throws ExceptionHandler {
        // ** Set the items **
        teamsTable.setItems(model.getAllTeams());

        // ** Set the listeners **


        // ** Set the columns **
        teamName.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getName());
        });

        teamOverheadCosts.setCellValueFactory(cellData -> {
            double directCosts = model.getOverheadCosts(model.getProject(), cellData.getValue());

            String stringBeautifier = GUIHelper.currencyFormatter(directCosts);

            return new SimpleStringProperty(stringBeautifier);
        });

        teamDirectCosts.setCellValueFactory(cellData -> {
            double directCosts = model.getDirectCosts(model.getProject(), cellData.getValue());

            String stringBeautifier = GUIHelper.currencyFormatter(directCosts);

            return new SimpleStringProperty(stringBeautifier);
        });

        teamTotalCosts.setCellValueFactory(cellData -> {
            double directCosts = model.getTotalCost(model.getProject(), cellData.getValue());

            String stringBeautifier = GUIHelper.currencyFormatter(directCosts);

            return new SimpleStringProperty(stringBeautifier);
        });

    }


    private void setLabels(){
        projectName.setText(model.getProject().getName());
        countryLabel.setText(model.getProject().getCountry().getValue());;

        totalEmployees.setText(String.valueOf(model.getProjectAssignments(model.getProject()).size()));
        overheadEmployees.setText(String.valueOf(model.getOverheadAssignments(model.getProject()).size()));
        productionEmployees.setText(String.valueOf(model.getProductionResourceAssignments(model.getProject()).size()));

        totalTeams.setText(String.valueOf(model.getProjectTeams(model.getProject()).size()));
        // We use stringFormatter method because we need to show it pretty in the GUI :D
        hourlyRate.setText(GUIHelper.currencyFormatter(model.getHourlyRate(model.getProject())));
        dailyRate.setText(GUIHelper.currencyFormatter(model.getDailyRate(model.getProject())));

        // Costs
        directCosts.setText(GUIHelper.currencyFormatter(model.getDirectCosts(model.getProject())));
        overheadCosts.setText(GUIHelper.currencyFormatter(model.getOverheadCosts(model.getProject())));
        totalCosts.setText(GUIHelper.currencyFormatter(model.getTotalCosts(model.getProject())));

    }
}
