package com.se.ecostruxure_mmirzakhani.model;

import com.se.ecostruxure_mmirzakhani.be.*;
import com.se.ecostruxure_mmirzakhani.bll.EmployeeLogic;
import com.se.ecostruxure_mmirzakhani.bll.TeamLogic;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.utils.Validate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model {
    // An employee object to update when creating a new profile
    private final SimpleObjectProperty<Employee> employee = new SimpleObjectProperty<>();

    // List of employee when they grouped based on user request whether all of them or based on a filter like country, team or etc.
    private final ObservableList<Employee> employees = FXCollections.observableArrayList();
    private final ObservableList<Team> teams = FXCollections.observableArrayList();


    // List of contract changes for one employee with same personal details but different contracts
    private final ObservableList<Employee> employeeHistory = FXCollections.observableArrayList();
    private final EmployeeLogic logic = new EmployeeLogic();
    private final TeamLogic teamLogic = new TeamLogic();

    private Employee selectedEmployee;
    // Getter and setter for selectedEmployee
    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    /**
     * Constructor
     */
    public Model(){
        initEmployee();

    }

    /**
     * Init an empty employee object with default values to update it later
     */
    public void initEmployee(){
        Employee e = new Employee();
        Team t = new Team();
        Contract c = new Contract();
        e.setTeam(t);
        e.setContract(c);

        Contract contract = new Contract();
        e.setContract(contract);

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
    public ObservableList<Team> getAllTeams() throws ExceptionHandler {
        setTeams();
        return teams;
    }
    public void setTeams() throws ExceptionHandler {
        teams.setAll(teamLogic.getAllTeams());
    }



    /**
     * Set Employee's first name
     */
    public void setFirstName(String firstName) throws ExceptionHandler {
        employee.get()
                .setFirstName(Validate.validateName(firstName));
    }

    /**
     * Set Employee's last name
     */
    public void setLastName(String lastName) throws ExceptionHandler {
        employee.get()
                .setLastName(Validate.validateName(lastName));
    }

    /**
     * Set Employee's country
     */
    public void setCountry(Country country) {
        employee.get()
                .setCountry(country);
    }

    /**
     * Set Employee's region
     */
    public void setRegion(Region region){
        employee.get().setRegion(region);
    }


    /**
     * Set Employee's team name
     */
    public void setTeam(String teamName) {
        employee.get()
                .getTeam()
                .setName(teamName);
    }

    public void setAnnualSalary(double annualSalary){
        employee.get().getContract().setAnnualSalary(annualSalary);
    }
    public void setFixedAnnualAmount(double fixedAnnualAmount){
        employee.get().getContract().setFixedAnnualAmount(fixedAnnualAmount);
    }
    public void setAnnualWorkHours(double annualWorkHours){
        employee.get().getContract().setAnnualWorkHours(annualWorkHours);
    }
    public void setAverageDailyWorkHours(double averageDailyWorkHours) {
        employee.get().getContract().setAverageDailyWorkHours(averageDailyWorkHours);
    }
    public void setOverhead(boolean overhead) {
        employee.get().getContract().setOverhead(overhead);
    }
    public void setOverheadPercentage(double overheadPercentage) {
        employee.get().getContract().setOverheadPercentage(overheadPercentage);
    }
    public void setUtilizationPercentage(double utilizationPercentage) {
        employee.get().getContract().setUtilizationPercentage(utilizationPercentage);
    }
    public void setMarkupPercentage(double markupPercentage) {
        employee.get().getContract().setMarkupPercentage(markupPercentage);
    }
    public void setGrossMarginPercentage(double grossMarginPercentage) {
        employee.get().getContract().setGrossMarginPercentage(grossMarginPercentage);
    }
    public void setHourlyRate(double hourlyRate) {
        employee.get().getContract().setHourlyRate(hourlyRate);
    }
    public void setDailyRate(double dailyRate) {
        employee.get().getContract().setDailyRate(dailyRate);
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
    public void setOverheadPercentage(String overheadPercentage) throws ExceptionHandler {
        employee.get()
                .getContract()
                .setOverheadPercentage(Validate.validateDouble(overheadPercentage));
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
    public void createEmployee() throws ExceptionHandler {
        // ToDo: You need to send employee details to the bottom layers and if succeeded, create otherwise throw an exception from bottom layer
        // Now It's just a demo how it can change the table view
        employees.add(employee.get());
        logic.createEmployee(employee.get());
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

    /**
     * @return Employee's hourly rate
     */
    public double getHourlyRate() throws ExceptionHandler{
        updateRates();
        return employee.get()
                .getContract()
                .getHourlyRate();
    }

    /**
     * @return Employee's daily rate
     */
    public double getDailyRate() throws ExceptionHandler{
        updateRates();
        return employee.get()
                .getContract()
                .getDailyRate();
    }

    /**
     * Method to update the Employee object with the latest daily and hourly rate
     */
    private void updateRates() throws ExceptionHandler{
        logic.updateRates(employee.get());
    }


    // ToDo: Getters and Setters for the above lists and objects



        // ToDo: Getters and Setters for the above lists and objects

    public void createTeam(Team team) throws ExceptionHandler, SQLException {
        teamLogic.createTeam(team);
        teams.add(team);
    }
    public void updateTeam(Team team) throws ExceptionHandler, SQLException {
        teamLogic.updateTeam(team);
    }
    public boolean deleteTeam(int id) throws ExceptionHandler, SQLException {
        teamLogic.deleteTeam(id);
        return true;
    }

    public void updateEmployee(Employee employee) throws ExceptionHandler {
       logic.updateEmployee(employee);
    }


    public List<String> getTeams(){
        List<String> teams = new ArrayList<>();
        for (Employee e: employees){
            teams.add(e.getTeam().getName());
        }
        return teams;
    }

}
