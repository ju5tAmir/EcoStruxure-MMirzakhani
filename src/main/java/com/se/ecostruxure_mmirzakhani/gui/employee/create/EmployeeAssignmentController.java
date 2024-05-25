package com.se.ecostruxure_mmirzakhani.gui.employee.create;

import com.se.ecostruxure_mmirzakhani.be.entities.Project;
import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.be.entities.Team;
import com.se.ecostruxure_mmirzakhani.utils.AlertHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.Window;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class EmployeeAssignmentController implements IController<Model> {
    @FXML
    private Menu teamMenu, projectMenu;
    @FXML
    private TextField utilizationPercentage;
    @FXML
    private Model model;
    @Override
    public void setModel(Model model) {
        this.model = model;

        try {
            setProjectMenu();
            setTeamMenu();

        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    private void setTeamMenu() throws ExceptionHandler {

        for (Team team: model.getAllTeams()){
            MenuItem menuItem = new MenuItem(team.getName());
            menuItem.setOnAction(event -> {
                teamMenu.setText(team.getName());
                model.setTeam(team);
            });
            teamMenu.getItems().add(menuItem);
        }

    }

    private void setProjectMenu() throws ExceptionHandler {

        for (Project project: model.getAllProjects()){
            MenuItem menuItem = new MenuItem(project.getName());
            menuItem.setOnAction(event -> {
                projectMenu.setText(project.getName());
                model.setProject(project);
            });
            projectMenu.getItems().add(menuItem);
        }

    }
    @FXML
    private void onAssign(){

        double totalUtil = model.getTotalUtil(model.getEmployee());

        try {
            Assignment assignment = new Assignment();
            assignment.setEmployee(model.getEmployee());
            assignment.setTeam(model.getTeam());
            assignment.setUtilizationPercentage(Double.parseDouble(utilizationPercentage.getText()));

            if (totalUtil + Double.parseDouble(utilizationPercentage.getText()) > 100){
                AlertHandler.displayAlert("Overall utilization percentage cannot exceed 100", Alert.AlertType.ERROR);
                return;
            }
            model.addProjectMemberLinker(model.getProject(), assignment);
            onCancel();
        } catch (RuntimeException e){
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    @FXML
    private void onCancel(){
        Window.closeStage((Stage) utilizationPercentage.getScene().getWindow());
    }
}