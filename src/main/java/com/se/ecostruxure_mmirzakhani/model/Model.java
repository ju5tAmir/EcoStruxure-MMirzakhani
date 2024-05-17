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
    private final SimpleObjectProperty<EmployeeTeam> employeeTeam = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<EmployeeTeamGeography> employeeTeamGeography = new SimpleObjectProperty<>();

    // List of employees when they are grouped based on user request whether all of them or based on a filter like country, team, or etc.
    private final ObservableList<Employee> employees = FXCollections.observableArrayList();
    private final ObservableList<Team> teams = FXCollections.observableArrayList();
    private final ObservableList<EmployeeTeam> employeeTeams = FXCollections.observableArrayList();
    private final ObservableList<Geography> geographies = FXCollections.observableArrayList();

    // List of contract changes for one employee with the same personal details but different contracts
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
    public Model() {
        initEmployee();
    }

    /**
     * Init an empty employee object with default values to update it later
     */
    public void initEmployee() {
        Employee e = new Employee();
        Contract c = new Contract();
        e.setContract(c);

        employee.set(e);
    }

    /**
     * Retrieve all the employees
     * @return List of employees as ObservableList
     */
    public ObservableList<Employee> getAllEmployees() throws ExceptionHandler, SQLException {
        setEmployees();
        return employees;
    }

    /**
     * Retrieve all the employees from the logic layer and update the ObservableList
     */
    private void setEmployees() throws ExceptionHandler, SQLException {
        employees.setAll(logic.getAllEmployees());
    }

    public ObservableList<Team> getAllTeams() throws ExceptionHandler, SQLException {
        setTeams();
        return teams;
    }

    public void setTeams() throws ExceptionHandler, SQLException {
        teams.setAll(teamLogic.getAllTeams());
    }

    public ObservableList<EmployeeTeam> getAllEmployeeTeams() throws ExceptionHandler, SQLException {
        setEmployeeTeams();
        return employeeTeams;
    }

    public void setEmployeeTeams() throws ExceptionHandler, SQLException {
        employeeTeams.setAll(logic.getAllEmployeeTeams());
    }

    public ObservableList<Geography> getAllGeographies() throws ExceptionHandler, SQLException {
        setGeographies();
        return geographies;
    }

    public void setGeographies() throws ExceptionHandler, SQLException {
        geographies.setAll(logic.getAllGeographies());
    }

    /**
     * Set Employee's first name
     */
    public void setFirstName(String firstName) throws ExceptionHandler {
        employee.get().setFirstName(Validate.validateName(firstName));
    }

    /**
     * Set Employee's last name
     */
    public void setLastName(String lastName) throws ExceptionHandler {
        employee.get().setLastName(Validate.validateName(lastName));
    }

    /**
     * Set Employee's contract properties
     */
    public void setAnnualSalary(double annualSalary) {
        employee.get().getContract().setAnnualSalary(annualSalary);
    }

    public void setFixedAnnualAmount(double fixedAnnualAmount) {
        employee.get().getContract().setFixedAnnualAmount(fixedAnnualAmount);
    }

    public void setAnnualWorkHours(double annualWorkHours) {
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
     * Creates a new employee and adds it to the list of employees.
     *
     * @param employee The employee object to be created and added.
     * @throws ExceptionHandler if an error occurs during the creation process.
     */
    public void createEmployee() throws ExceptionHandler, SQLException {
        employees.add(employee.get());
        logic.createEmployee(employee.get());
    }

    /**
     * Updates the employee object with the provided employee details.
     *
     * @param employee The employee object containing updated details.
     */
    public void setEmployee(Employee employee) {
        this.employee.set(employee);
    }

    /**
     * Retrieves the current working employee object.
     *
     * @return The current employee object.
     */
    public Employee getEmployee() {
        return employee.get();
    }

    /**
     * @return Employee's hourly rate
     */
    public double getHourlyRate() throws ExceptionHandler {
        updateRates();
        return employee.get().getContract().getHourlyRate();
    }

    /**
     * @return Employee's daily rate
     */
    public double getDailyRate() throws ExceptionHandler {
        updateRates();
        return employee.get().getContract().getDailyRate();
    }

    /**
     * Method to update the Employee object with the latest daily and hourly rate
     */
    private void updateRates() throws ExceptionHandler {
        logic.updateRates(employee.get());
    }

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

    public void updateEmployee(Employee employee) throws ExceptionHandler, SQLException {
        logic.updateEmployee(employee);
    }

    public List<String> getTeams() {
        List<String> teamNames = new ArrayList<>();
        for (EmployeeTeam et : employeeTeams) {
            teamNames.add(et.getTeam().getName());
        }
        return teamNames;
    }
}