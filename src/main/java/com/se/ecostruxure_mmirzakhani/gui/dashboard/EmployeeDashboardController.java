package com.se.ecostruxure_mmirzakhani.gui.dashboard;
import com.se.ecostruxure_mmirzakhani.be.Profile;
import com.se.ecostruxure_mmirzakhani.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.lang.Double;
import java.io.IOException;

public class EmployeeDashboardController implements IController{
    @FXML
    private TableView<Profile> profileTable;
    @FXML
    private TableView<Profile, String> profileName;
    @FXML
    private TableView<Profile, Double> profileAnnualSalary;
    @FXML
    private TableView<Profile, Double> profileOverheadMultiplier;
    @FXML
    private TableView<Profile, Double> profileFixedAnnualAmount;
    @FXML
    private TableView<Profile, String> profileCountry;
    @FXML
    private TableView<Profile, String> profileTeam;
    @FXML
    private TableView<Profile, Double> profileAnnualWorkingHours;
    @FXML
    private TableView<Profile, Double> profileUtilization;
    @FXML
    private TableView<Profile, Boolean> profileType;

    private ObservableList<Profile> profiles = FXCollections.observableArrayList();
    private Model model;

    @Override
    public void setModel(Object model) {
    }

    @FXML
    private void onCreateProfile() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        CreateProfile createProfile = loader.getController();
        createProfile.setModel(this.model);

    }
    @FXML
    private void onEditProfile(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        //EditProfile editProfile = loader.getController();
        //editProfile.setProfileToEdit(profileToEdit);
    }

    @FXML
    public void calculateHourlyRate(ActionEvent actionEvent) throws IOException{

    }

    @FXML
    public void calculateDailyRate(ActionEvent actionEvent) throws IOException{

    }
}
