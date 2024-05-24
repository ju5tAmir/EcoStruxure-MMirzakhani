package com.se.ecostruxure_mmirzakhani.model;

import com.se.ecostruxure_mmirzakhani.be.entities.*;
import com.se.ecostruxure_mmirzakhani.be.enums.Currency;
import com.se.ecostruxure_mmirzakhani.bll.assignment.AssignmentService;
import com.se.ecostruxure_mmirzakhani.bll.employee.EmployeeService;
import com.se.ecostruxure_mmirzakhani.bll.project.ProjectService;
import com.se.ecostruxure_mmirzakhani.bll.team.TeamService;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class Model {
    // SimpleObjectProperty attributes to handle the current instance of objects for each category.
    // These properties are updated based on user actions (e.g., CRUD operations) on a specific Employee, Contract, Team, Project, and History object.
    private final SimpleObjectProperty  <Employee>                  employee            = new SimpleObjectProperty<>(new Employee());
    private final SimpleObjectProperty  <Contract>                  contract            = new SimpleObjectProperty<>(new Contract());
    private final SimpleObjectProperty  <Team>                      team                = new SimpleObjectProperty<>(new Team());
    private final SimpleObjectProperty  <Project>                   project             = new SimpleObjectProperty<>(new Project());
    private final SimpleObjectProperty  <Assignment>                assignment          = new SimpleObjectProperty<>(new Assignment());


    private final ObservableList        <Employee>                  employees           = FXCollections.observableArrayList();
    private final ObservableList        <Team>                      teams               = FXCollections.observableArrayList();
    private final ObservableList        <Project>                   projects            = FXCollections.observableArrayList();
    private final ObservableList        <Assignment>                assignments         = FXCollections.observableArrayList();

    private final Currency                                          systemCurrency      = CurrencyService.getSystemCurrency();
    private final EmployeeService                                   employeeService;
    private final TeamService                                       teamService;
    private final ProjectService                                    projectService;
    private final AssignmentService                                 assignmentService;


    // ************************ Constructor ************************
    public Model(){
        this.employeeService    = new EmployeeService();
        this.teamService        = new TeamService();
        this.projectService     = new ProjectService();
        this.assignmentService  = new AssignmentService();
    }


    // ************************ Methods *****************************
    // ************************ Getters *****************************

    /**
     * Get currently working employee object
     */
    public Employee getEmployee() {
        return employee.get();
    }

    /**
     * Get currently working Team object
     */
    public Team getTeam() {
        return team.get();
    }

    /**
     * Get currently working Project object
     */
    public Project getProject() {
        return project.get();
    }

    /**
     * Get currently working Assignment object
     */
    public Assignment getAssignment() {
        return assignment.get();
    }

    /**
     * Get all the employees
     */
    public ObservableList<Employee> getAllEmployees() throws ExceptionHandler {
        setEmployees();
        return employees;
    }

    /**
     * Get all the Teams
     */
    public ObservableList<Team> getAllTeams() throws ExceptionHandler {
        setTeams();
        return teams;
    }

    /**
     * Get all the Projects
     */
    public ObservableList<Project> getAllProjects() throws ExceptionHandler {
        setProjects();
        return projects;
    }

    /**
     * Get all the Assignments
     */
    public ObservableList<Assignment> getAssignments() throws ExceptionHandler {
        setAssignments();
        return assignments;
    }


    /**
     * Get current currency of the system (default EUR)
     */
    public Currency getCurrency(){
        return CurrencyService.getSystemCurrency();
    }



    // ************************ Setters *****************************
    /**
     * Set Employee object
     */
    public void setEmployee(Employee employee){
        this.employee.set(employee);
    }

    /**
     * Set Team object
     */
    private void setTeam(Team team){
        this.team.set(team);
    }

    /**
     * Set Project object
     */
    private void setProject(Project project){
        this.project.set(project);
    }

    /**
     * Set Assignment object
     */
    private void setAssignment(Assignment assignment){
        this.assignment.set(assignment);
    }


    /**
     * Retrieve and updates the latest Employees list
     */
    private void setEmployees() throws ExceptionHandler {
        employees.setAll(employeeService.getAllEmployees());
    }

    /**
     * Retrieve and updates the latest Team list
     */
    private void setTeams() throws ExceptionHandler {
        teams.setAll(teamService.getAllTeams());
    }

    /**
     * Retrieve and updates the latest Project list
     */
    private void setProjects() throws ExceptionHandler {
        projects.setAll(projectService.getAllProjects());
    }

    /**
     * Retrieve and updates the latest Assignment list
     */
    private void setAssignments() throws ExceptionHandler {
        assignments.setAll(assignmentService.getAllAssignments());
    }

//    /**
//     * Set current currency of the system (default EUR)
//     */
//    public void setCurrency(Currency currency){
//        this.currency.set(currency);
//    }

    /**
     * Set Employee's first name
     */
    public void setFirstName(String firstName) throws ExceptionHandler {
        employee.get()
                .setFirstName(firstName);
    }

    /**
     * Set Employee's last name
     */
    public void setLastName(String lastName) throws ExceptionHandler {
        employee.get()
                .setLastName(lastName);
    }

    /**
     * Set Currency for the currently working contract
     */
    public void setContractCurrency(Currency currency){
        this.contract.get().setCurrency(currency);
    }

    /**
     * Set annual salary for the currently working contract
     */
    public void setContractAnnualSalary(double annualSalary){
        this.contract.get().setAnnualSalary(annualSalary);
    }

    /**
     * Set fixed annual amount for the currently working contract
     */
    public void setContractFixedAnnualAmount(double fixedAnnualAmount){
        this.contract.get().setFixedAnnualAmount(fixedAnnualAmount);
    }

    /**
     * Set annual work hours for the currently working contract
     */
    public void setContractAnnualWorkHours(double annualWorkHours){
        this.contract.get().setAnnualWorkHours(annualWorkHours);
    }

    /**
     * Set average daily work hours for the currently working contract
     */
    public void setContractAverageDailyWorkHours(double averageDailyWorkHours){
        this.contract.get().setAverageDailyWorkHours(averageDailyWorkHours);
    }

    /**
     * Set overhead percentage for the currently working contract
     */
    public void setContractOverheadPercentage(double overheadPercentage){
        this.contract.get().setOverheadPercentage(overheadPercentage);
    }

    /**
     * Retrieve latest employee changes for Contract and Projects
     */
    private void setEmployeeHistory(Employee employee) throws ExceptionHandler{
        this.history.set(employeeService.getEmployeeHistory(employee));
    }


    // ************************ Utilities *****************************
    private void updateAllProperties() throws ExceptionHandler{
        setEmployees();
        setProjects();
        setTeams();
    }

    /**
     * Assign the contract to the employee (This is the moment that user clicks on submit contract)
     */
    public void assignContractToEmployee(){
        // Assign this moment as start time
        this.contract.get().setTimeLine(new TimeLine(LocalDateTime.now(), LocalDateTime.MAX));

        // ToDo: Add to database/ if it's ok, then update it here.

        // Assign the currently working contract to the currently working employee
        this.employee.get().setContract(contract.get());
    }



    /**
     * Assign the project (This is the moment that user clicks on add new project)
     */
    public void assignProjectToEmployee(){
        // Assign this moment as start time

        // Validate
        this.projects.add(project.get());
    }

    /**
     * Convert an amount of money from local currency to system preferred currency
     */
    public double getConvertedValue(Currency localCurrency, double amount) {
        return CurrencyService.convert(localCurrency, systemCurrency, amount);
    }

    public void removeAssignment(Assignment assignment) throws ExceptionHandler {
        assignmentService.remove(assignment);
    }


//    // ****************** LAB *******************














}
