package com.se.ecostruxure_mmirzakhani.gui.project.update;

import com.se.ecostruxure_mmirzakhani.be.enums.Country;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.AlertHandler;
import com.se.ecostruxure_mmirzakhani.utils.Window;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateProjectController implements IController<Model> {
    @FXML
    private TextField projectName;

    @FXML
    private Menu countryMenu;
    private Model model;
    private Country selectedCountry;
    @Override
    public void setModel(Model model) {
        this.model = model;

        initCountryMenu();
        fillFields();
    }

    private void initCountryMenu() throws RuntimeException {

        // Iterate over countries to create a list for the country menu
        for (Country country: Country.values()){
            MenuItem menuItem = new MenuItem(country.name());
            menuItem.setOnAction(event -> {
                selectedCountry = Country.valueOf(menuItem.getText());
                countryMenu.setText(menuItem.getText());
            });
            countryMenu.getItems().add(menuItem);
        }

    }

    private void fillFields(){
        projectName.setText(model.getProject().getName());


    }

    @FXML
    private void onSubmitButton() throws RuntimeException{
        try {
            model.setProjectName(projectName.getText());
            model.setProjectCountry(selectedCountry);
            if (model.updateProject()){
                AlertHandler.displayAlert(ExceptionMessage.SUCCESSFUL.getValue(), Alert.AlertType.INFORMATION);
            } else {
                AlertHandler.displayAlert(ExceptionMessage.FAILURE.getValue(), Alert.AlertType.ERROR);
            }

        } catch (RuntimeException | ExceptionHandler r){
            AlertHandler.displayAlert(r.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onCancelButton(){
        Window.closeStage((Stage) projectName.getScene().getWindow());
    }

}
