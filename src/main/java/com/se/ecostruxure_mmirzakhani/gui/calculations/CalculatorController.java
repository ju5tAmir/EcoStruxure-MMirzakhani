package com.se.ecostruxure_mmirzakhani.gui.calculations;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class CalculatorController implements IController<Model> {
    private Model model;
    @Override
    public void setModel(Model model) {
        this.model=model;
    }



}
