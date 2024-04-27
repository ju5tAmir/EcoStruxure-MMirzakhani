package com.se.ecostruxure_mmirzakhani.gui.dashboard;

import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;
import javafx.fxml.FXML;
import javafx.stage.Modality;

public class EmployeeDashboardController implements IController {

    private Model model;

    // ToDo: Implement controller methods such as buttons and other nodes.






    @FXML
    private void onCreateEmployee() {
        try {
            Window.createStage(WindowType.CREATE_EMPLOYEE, model, Modality.APPLICATION_MODAL, false);
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void setModel(Object model) {
        // This method should update model object by retrieving incoming model,
        // but because this controller is the initial controller, it will only
        // instantiate a new model object
        this.model = new Model();

    }
}
