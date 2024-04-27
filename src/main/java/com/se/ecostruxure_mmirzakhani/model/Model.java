package com.se.ecostruxure_mmirzakhani.model;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.bll.EmployeeLogic;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
    // An employee object to update when creating a new profile
    private final SimpleObjectProperty<Employee> employee = new SimpleObjectProperty<>();

    // List of employee when they grouped based on user request whether all of them or based on a filter like country, team or etc.
    private final ObservableList<Employee> employees = FXCollections.observableArrayList();

    // List of contract changes for one employee with same personal details but different contracts
    private final ObservableList<Employee> employeeHistory = FXCollections.observableArrayList();
    private final EmployeeLogic logic = new EmployeeLogic();

    /**
     * Constructor
     */
    public Model(){

    }

    /**
     * Retrieve all the employees
     * @return List of employees as ObservableList
     */
    public ObservableList<Employee> getAllEmployees() throws ExceptionHandler {
        setEmployees();
        return employees;
    }

    /**
     * Retrieve all the employees from the logic layer and update the ObservableList
     */
    private void setEmployees() throws ExceptionHandler {
        employees.setAll(logic.getAllEmployees());
    }



    // ToDo: Getters and Setters for the above lists and objects
}
