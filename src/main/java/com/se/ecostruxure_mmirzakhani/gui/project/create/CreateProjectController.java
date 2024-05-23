package com.se.ecostruxure_mmirzakhani.gui.project.create;

import com.se.ecostruxure_mmirzakhani.be.Country;
import com.se.ecostruxure_mmirzakhani.be.Project;
import com.se.ecostruxure_mmirzakhani.utils.AlertHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.Window;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateProjectController implements IController<Model> {
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
    }

    @FXML
    private void onSubmitButton(){
        try {
            model.setProject(new Project(10, projectName.getText(), selectedCountry));
            model.createProject();
            onCancelButton();
        } catch (RuntimeException r){
            AlertHandler.displayAlert(r.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onCancelButton(){
        Window.closeStage((Stage) projectName.getScene().getWindow());
    }


    private void initCountryMenu(){

        Country[] countries = Country.values();
        countryMenu.getItems().getFirst().setOnAction(e->{
            selectedCountry = Country.DENMARK;
            countryMenu.setText("Denmark");
        });
        for (Country country: countries){
            MenuItem menuItem = new MenuItem(country.toString());
            menuItem.setOnAction(event -> {
                selectedCountry = Country.valueOf(menuItem.getText().toUpperCase().replace(" ", "_"));
                countryMenu.setText(menuItem.getText());
            });
            countryMenu.getItems().add(menuItem);
        }

    }
}
