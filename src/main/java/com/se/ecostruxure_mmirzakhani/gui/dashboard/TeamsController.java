package com.se.ecostruxure_mmirzakhani.gui.dashboard;

import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.exceptions.AlertHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class TeamsController implements IController<Model> {
    private Model model;
    @FXML
    private Label employeesLabel;
    @FXML
    private Label teamsLabel;
    @FXML
    private TableView teamsTable;
    @FXML
    private TableColumn teamNameColumn;
    @FXML
    private Button createTeam;
    @Override
    public void setModel(Model model) {
        this.model=model;

        employeesLabel.setOnMouseClicked(event -> {
            try {
                Window.createStage(WindowType.EMPLOYEE_DASHBOARD, model, Modality.APPLICATION_MODAL, false);
                Window.closeStage(employeesLabel.getScene());
            } catch (ExceptionHandler e) {
                throw new RuntimeException(e);
            }
        });
        try {teamsLabel.setOnMouseClicked(event -> {
            AlertHandler.displayAlert("Teams Dashboard is already open!", Alert.AlertType.INFORMATION);
        });
            initTeamsTable();
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }

        createTeam.setOnAction(event -> {
            createTeamUI();
        });

    }
    public void createTeamUI() {
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

        // Create a scene and set it to the stage
        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }


    public void initTeamsTable() throws ExceptionHandler {
        teamsTable.setItems(model.getAllTeams());
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
}
