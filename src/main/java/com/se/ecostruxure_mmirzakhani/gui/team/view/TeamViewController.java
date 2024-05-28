package com.se.ecostruxure_mmirzakhani.gui.team.view;

import com.se.ecostruxure_mmirzakhani.be.entities.Team;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.AlertHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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


    }


    @FXML
    private void onUpdateTeamButton(){

    }

    @FXML
    private void onDeleteTeamButton(){


    }
}
