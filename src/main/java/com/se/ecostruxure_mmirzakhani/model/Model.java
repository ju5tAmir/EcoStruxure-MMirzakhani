package com.se.ecostruxure_mmirzakhani.model;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
    // An employee object to update when creating a new profile
    private final SimpleObjectProperty<Employee> employee = new SimpleObjectProperty<>();

    // List of employee when they grouped based on user request whether country, team or etc.
    private final ObservableList<Employee> employees = FXCollections.observableArrayList();

    // List of contract changes for one employee with same personal details but different contracts
    private final ObservableList<Employee> employeeHistory = FXCollections.observableArrayList();


    /**
     * Constructor
     */
    public Model(){

    }

    // ToDo: Getters and Setters for the above lists and objects
}
