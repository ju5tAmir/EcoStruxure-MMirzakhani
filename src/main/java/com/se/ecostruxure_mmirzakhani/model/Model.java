package com.se.ecostruxure_mmirzakhani.model;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.bll.Logic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

    private Logic logic;

    public Model() {
        this.logic = new Logic();
    }

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    public ObservableList<Employee> getEmployees() {
        updateEmployeeList();
        return employeeList;
    }

    public void addEmployeeToList(Employee employee) {
        employeeList.add(employee);
    }

    public void updateEmployeeList() {
        this.employeeList.setAll(logic.getEmployees());
    }

    public void createEmployee(Employee employee) {
        logic.createEmployee(employee);
    }

    public boolean deleteEmployee(Employee employee) {
        return logic.deleteEmployee(employee);
    }

    public void editEmployee(Employee employee) {
        logic.editEmployee(employee);
    }
}

