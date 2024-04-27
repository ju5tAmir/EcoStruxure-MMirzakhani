package com.se.ecostruxure_mmirzakhani.gui.create;

import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;

public class CreateEmployeeController implements IController<Model> {

    private Model model;

    @Override
    public void setModel(Model model) {
        this.model = model;
    }
}
