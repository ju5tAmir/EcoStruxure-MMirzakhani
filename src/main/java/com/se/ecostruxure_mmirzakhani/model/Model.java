package com.se.ecostruxure_mmirzakhani.model;

import com.se.ecostruxure_mmirzakhani.be.Country;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.bll.EmployeeLogic;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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
        initEmployee();

    }

    /**
     * Init an empty employee object with default values to update it later
     */
    private void initEmployee(){
        Employee e = new Employee();
        Team t = new Team();
        e.setTeam(t);
        employee.set(e);
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

    /**
     * Set Employee's first name
     */
    public void setFirstName(String firstName){
        this.employee.get().setFirstName(firstName);
    }

    /**
     * Set Employee's last name
     */
    public void setLastName(String lastName){
        this.employee.get().setLastName(lastName);
    }

    /**
     * Set Employee's country
     */
    public void setCountry(Country country) {
        employee.get().setCountry(country);
    }

    /**
     * Set Employee's team name
     */
    public void setTeam(String teamName) {
        employee.get().getTeam().setName(teamName);
    }

    /**
     * Create employee
     */
    public void createEmployee() throws ExceptionHandler {
        // ToDo: You need to send employee details to the bottom layers and if succeeded, create otherwise throw an exception from bottom layer
        // Now It's just a demo how it can change the table view
        employees.add(employee.get());
    }

    public double getHourlyRate(Employee employee) throws ExceptionHandler {
        return logic.calculateHourlyRate(employee);
    }

    public double getDailyRate(Employee employee) throws ExceptionHandler {
        return logic.calculateDailyRate(employee);
    }
    // ToDo: Getters and Setters for the above lists and objects


}
