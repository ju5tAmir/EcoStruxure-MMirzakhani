package com.se.ecostruxure_mmirzakhani.gui.team.view;

import com.se.ecostruxure_mmirzakhani.be.entities.Team;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.AlertHandler;
import com.se.ecostruxure_mmirzakhani.utils.Window;
import com.se.ecostruxure_mmirzakhani.utils.WindowType;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;

public class TeamViewController implements IController<Model> {
    @FXML
    private TableView<Team> teamsTable;
    @FXML
    private TableColumn<Team, String> teamNameColumn;
    private Model model;
    @Override
    public void setModel(Model model) {
        this.model = model;
        setTeamsTable();
    }


    private void setTeamsTable(){

        try {
            teamsTable.setItems(model.getAllTeams());

            teamNameColumn.setCellValueFactory(cellData -> {
                String name = cellData.getValue().getName();

                return new SimpleStringProperty(name);
            });
        } catch (ExceptionHandler e) {
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onCreateTeamButton(){
        try {
            Window.createStage(WindowType.CREATE_TEAM, model, Modality.WINDOW_MODAL, false);
        } catch (ExceptionHandler e) {
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    @FXML
    private void onUpdateTeamButton(){
        if (!teamsTable.getSelectionModel().isEmpty()){
            model.setTeam(teamsTable.getSelectionModel().getSelectedItem());
            try {
                Window.createStage(WindowType.UPDATE_EMPLOYEE, model, Modality.WINDOW_MODAL, false);
            } catch (ExceptionHandler e) {
                throw new RuntimeException(e);
            }

        } else {
            AlertHandler.displayAlert("Please select a team first in order to update.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void onDeleteTeamButton(){
        if (!teamsTable.getSelectionModel().isEmpty()){
            model.setTeam(teamsTable.getSelectionModel().getSelectedItem());

            try {
                if (model.deleteTeam()){
                    AlertHandler.displayAlert(ExceptionMessage.SUCCESSFUL.getValue(), Alert.AlertType.INFORMATION);
                }
            } catch (ExceptionHandler e) {
                AlertHandler.displayAlert(ExceptionMessage.FAILURE.getValue(), Alert.AlertType.ERROR);
            }

        } else {
            AlertHandler.displayAlert("Please select a team first in order to delete.", Alert.AlertType.INFORMATION);
        }
    }
}
