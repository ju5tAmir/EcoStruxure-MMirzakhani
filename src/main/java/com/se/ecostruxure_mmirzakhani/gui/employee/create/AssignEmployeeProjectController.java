package com.se.ecostruxure_mmirzakhani.gui.employee.create;

import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import javafx.fxml.FXML;

public class AssignEmployeeProjectController implements IController<Model> {
    @FXML
    private Model model;
    @Override
    public void setModel(Model model) {
        this.model = model;
    }
}
