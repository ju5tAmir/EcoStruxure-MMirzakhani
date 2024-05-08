package com.se.ecostruxure_mmirzakhani.gui.team;

import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.exceptions.AlertHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TeamController implements IController<Model> {

    private Model model;
    @FXML
    private Label employeesLabel;
    @FXML
    private Label teamsLabel;
    @FXML
    private TableView<Team> teamsTable;
    @FXML
    private TableColumn teamNameColumn;
    @FXML
    private Button createTeamButton;
    @FXML
    private Button editTeamButton;
    @FXML
    private Button deleteTeamButton;
    @Override
    public void setModel(Model model) {
        this.model = model;
        try {
            initTeamsTable();
            actionEvents();
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public void actionEvents() throws ExceptionHandler {

        createTeamButton.setOnAction(event -> {
            createTeamUI();
        });

        editTeamButton.setOnAction(event -> {
            createTeamUI();
        });
        deleteTeamButton.setOnAction(event -> {
            deleteTeam();
        });
    }
    public void createTeamUI() {
        Team selectedTeam = teamsTable.getSelectionModel().getSelectedItem();
        // Create a new stage
        Stage stage = new Stage();
        stage.setTitle("Create Team");

        // Create a VBox to hold the UI elements
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        // Create a label for the name field
        Label nameLabel = new Label("Team Name:");

        // Create a text field for the name input
        TextField nameField = new TextField();

        if(selectedTeam==null) {
            // Create a button to create the team
            Button createButton = new Button("Create Team");
            createButton.setOnAction(event -> {
                String teamName = nameField.getText();
                if (!teamName.isEmpty()) {
                    try {
                        Team newTeam = new Team();
                        newTeam.setName(teamName);
                        // Call your createTeam method passing the newTeam object
                        model.createTeam(newTeam);
                        // Close the stage after creating the team
                        stage.close();
                    } catch (ExceptionHandler | SQLException e) {
                        // Handle exceptions
                        e.printStackTrace(); // Or display an alert
                    }
                } else {
                    // Display an alert if the name field is empty
                    AlertHandler.displayAlert("Please enter a team name.", Alert.AlertType.WARNING);
                }
            });
            // Add UI elements to the VBox
            vbox.getChildren().addAll(nameLabel, nameField, createButton);
        } else {
            Button saveButton = new Button("Save");
            saveButton.setOnAction(event -> {
                String newTeamName = nameField.getText();
                if(!newTeamName.isEmpty()){
                    // Here you should set the ID of the selected team
                    selectedTeam.setId(selectedTeam.getId()); // This line should be added
                    selectedTeam.setName(newTeamName);
                    try {
                        model.updateTeam(selectedTeam);
                        teamsTable.refresh();
                        stage.close();
                    } catch (ExceptionHandler e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            vbox.getChildren().addAll(nameLabel, nameField, saveButton);

        }

        // Create a scene and set it to the stage
        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }


    public void initTeamsTable() throws ExceptionHandler {
        teamsTable.setItems(model.getAllTeams());
        teamsTable.getSelectionModel().clearSelection();
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    }
    public void deleteTeam(){
        Team selectedTeam = teamsTable.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Deletion");
            confirmationAlert.setHeaderText("Are you sure you want to delete the selected team?");
            confirmationAlert.setContentText("This action cannot be undone.");

            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        boolean deleted = model.deleteTeam(selectedTeam.getId());
                        if (deleted) {
                            AlertHandler.displayAlert("Team deleted successfully.", Alert.AlertType.INFORMATION);
                            // Optionally, refresh the teams table after deletion
                            initTeamsTable();
                        } else {
                            AlertHandler.displayAlert("Failed to delete team.", Alert.AlertType.ERROR);
                        }
                    } catch (ExceptionHandler | SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } else {
            AlertHandler.displayAlert("Please select a team to delete.", Alert.AlertType.WARNING);
        }
    }


}
