package com.se.ecostruxure_mmirzakhani.model;

import com.se.ecostruxure_mmirzakhani.be.Country;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.bll.EmployeeLogic;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.utils.Validate;
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
        employee.get()
                .setFirstName(firstName);
    }

    /**
     * Set Employee's last name
     */
    public void setLastName(String lastName){
        employee.get()
                .setLastName(lastName);
    }

    /**
     * Set Employee's country
     */
    public void setCountry(Country country) {
        employee.get()
                .setCountry(country);
    }

    /**
     * Set Employee's team name
     */
    public void setTeam(String teamName) {
        employee.get()
                .getTeam()
                .setName(teamName);
    }

    /**
     * Set Employee's annual salary
     */
    public void setAnnualSalary(String annualSalary) throws ExceptionHandler{
        employee.get()
                .getContract()
                .setAnnualSalary(Validate.validateDouble(annualSalary));
    }

    /**
     * Set Employee's fixed annual amount
     */
    public void setFixedAnnualAmount(String fixedAnnualAmount) throws ExceptionHandler {
        employee.get()
                .getContract()
                .setFixedAnnualAmount(Validate.validateDouble(fixedAnnualAmount));
    }

    /**
     * Set Employee's annual work hours
     */
    public void setAnnualWorkHours(String annualWorkHours) throws ExceptionHandler {
        employee.get()
                .getContract()
                .setAnnualWorkHours(Validate.validateDouble(annualWorkHours));
    }

    /**
     * Set Employee's average daily work hours
     */
    public void setAverageDailyWorkHours(String averageDailyWorkHours) throws ExceptionHandler {
        employee.get()
                .getContract()
                .setAverageDailyWorkHours(Validate.validateDouble(averageDailyWorkHours));
    }

    /**
     * Set Employee's overhead percentage
     */
    public void setOverheadPercentage(double overheadPercentage){
        employee.get()
                .getContract()
                .setOverheadPercentage(overheadPercentage);
    }

    /**
     * Set Employee's utilization percentage
     */
    public void setUtilizationPercentage(String utilizationPercentage) throws ExceptionHandler {
        employee.get()
                .getContract()
                .setUtilizationPercentage(Validate.validateDouble(utilizationPercentage));
    }



    /**
     * Set Employee's overhead status
     */
    public void setOverheadStatus(boolean isOverhead){
        employee.get()
                .getContract()
                .setOverhead(isOverhead);
    }




    /**
     * Creates a new employee and adds it to the list of employees.
     *
     * @param employee The employee object to be created and added.
     * @throws ExceptionHandler if an error occurs during the creation process.
     */
    public void createEmployee(Employee employee) throws ExceptionHandler {
        // Adds the newly created employee to the list
        employees.add(logic.createEmployee(employee));
    }


    /**
     * Updates the employee object with the provided employee details.
     *
     * @param employee The employee object containing updated details.
     */
    public void setEmployee(Employee employee){
        this.employee.set(employee);
    }

    /**
     * Retrieves the current working employee object.
     *
     * @return The current employee object.
     */
    public Employee getEmployee(){
        return employee.get();
    }



    // ToDo: Getters and Setters for the above lists and objects

}
