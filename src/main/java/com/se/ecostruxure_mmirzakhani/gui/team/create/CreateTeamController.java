package com.se.ecostruxure_mmirzakhani.gui.team.create;

import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.AlertHandler;
import com.se.ecostruxure_mmirzakhani.utils.Window;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateTeamController implements IController<Model> {
    @FXML
    private TextField teamName;
    private Model model;
    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    private void onSubmitButton(){

        try {
            model.setTeamName(teamName.getText());
            if (model.createTeam()){
                AlertHandler.displayAlert(ExceptionMessage.SUCCESSFUL.getValue(), Alert.AlertType.INFORMATION);
                closeWindow();
            } else {
                AlertHandler.displayAlert(ExceptionMessage.FAILURE.getValue(), Alert.AlertType.ERROR);
            }
        } catch (ExceptionHandler e) {
            AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onCancelButton(){
        closeWindow();

    }

    private void closeWindow(){
        Window.closeStage((Stage) teamName.getScene().getWindow());
    }
}
