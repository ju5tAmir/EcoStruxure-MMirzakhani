package com.se.ecostruxure_mmirzakhani.gui.project.management;

import com.se.ecostruxure_mmirzakhani.be.ProjectMember;
import com.se.ecostruxure_mmirzakhani.bll.rate.RateService;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.gui.gui_utils.GUIHelper;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProjectManagementController implements IController<Model> {

    @FXML
    private Label totalEmployees, overheadEmployees, productionEmployees, totalTeams, hourlyRate, dailyRate, directCosts, overheadCosts, totalCosts;

    @FXML
    private TableView<ProjectMember> employeesTable;
    @FXML
    private TableColumn<ProjectMember, String> employeeFirstName;
    @FXML
    private TableColumn<ProjectMember, String> employeeLastName;
    @FXML
    private TableColumn<ProjectMember, String> employeeTeam;
    @FXML
    private TableColumn<ProjectMember, Double> employeeUtilPercentage;

//    @FXML
//    private TableView<>



    @FXML
    private Label projectName;

    private Model model;
    @Override
    public void setModel(Model model) {
        this.model = model;
        projectName.setText(model.getProject().getName());

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
    }


    private void setLabels(){
        totalEmployees.setText(String.valueOf(model.getProjectMembers(model.getProject()).size()));
        overheadEmployees.setText(String.valueOf(model.getRate(model.getProject()).getOverheadEmployees().size()));
        productionEmployees.setText(String.valueOf(model.getRate(model.getProject()).getProductionEmployees().size()));

        totalTeams.setText(String.valueOf(model.getProjectTeams(model.getProject()).size()));
        // We use stringFormatter method because we need to show it pretty in the GUI :D
        hourlyRate.setText(GUIHelper.formatter(model.getRate(model.getProject()).getHourlyRate()));
        dailyRate.setText(GUIHelper.formatter(model.getRate(model.getProject()).getDailyRate()));

        // Costs
        directCosts.setText(GUIHelper.formatter(model.getRate(model.getProject()).getDirectCosts()));
        overheadCosts.setText(GUIHelper.formatter(model.getRate(model.getProject()).getOverheadCosts()));
        totalCosts.setText(GUIHelper.formatter(model.getRate(model.getProject()).getTotalCosts()));

    }
}