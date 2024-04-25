package com.se.ecostruxure_mmirzakhani.model;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.bll.EmployeeLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

    private EmployeeLogic employeeLogic;

    public Model() {
        this.employeeLogic = new EmployeeLogic();
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
        this.employeeList.setAll(employeeLogic.getEmployees());
    }

    public void createEmployee(Employee employee) {
        employeeLogic.createEmployee(employee);
    }

    public boolean deleteEmployee(Employee employee) {
        return employeeLogic.deleteEmployee(employee);
    }

    public void editEmployee(Employee employee) {
        employeeLogic.editEmployee(employee);
    }
}

