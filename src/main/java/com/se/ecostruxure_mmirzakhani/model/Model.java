package com.se.ecostruxure_mmirzakhani.model;

import com.se.ecostruxure_mmirzakhani.be.entities.*;
import com.se.ecostruxure_mmirzakhani.be.enums.Country;
import com.se.ecostruxure_mmirzakhani.be.enums.Currency;
import com.se.ecostruxure_mmirzakhani.be.enums.EmployeeType;
import com.se.ecostruxure_mmirzakhani.bll.assignment.AssignmentService;
import com.se.ecostruxure_mmirzakhani.bll.employee.EmployeeService;
import com.se.ecostruxure_mmirzakhani.bll.project.ProjectService;
import com.se.ecostruxure_mmirzakhani.bll.rate.RateService;
import com.se.ecostruxure_mmirzakhani.bll.team.TeamService;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;

import com.se.ecostruxure_mmirzakhani.utils.Mapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

public class Model {
    // SimpleObjectProperty attributes to handle the current instance of objects for each category.
    // These properties are updated based on user actions (e.g., CRUD operations) on a specific Employee, Contract, Team, Project, and History object.
    private final SimpleObjectProperty  <Employee>                  employee            = new SimpleObjectProperty<>(new Employee());
    private final SimpleObjectProperty  <Contract>                  contract            = new SimpleObjectProperty<>(new Contract());
    private final SimpleObjectProperty  <Team>                      team                = new SimpleObjectProperty<>(new Team());
    private final SimpleObjectProperty  <Project>                   project             = new SimpleObjectProperty<>(new Project());
    private final SimpleObjectProperty  <Assignment>                assignment          = new SimpleObjectProperty<>(new Assignment());

    // These ObservableLists are contains all objects for each category
    private final ObservableList        <Employee>                  employees           = FXCollections.observableArrayList();
    private final ObservableList        <Team>                      teams               = FXCollections.observableArrayList();
    private final ObservableList        <Project>                   projects            = FXCollections.observableArrayList();
    private final ObservableList        <Assignment>                assignments         = FXCollections.observableArrayList();

    private final FilteredList          <Assignment>                filteredAssignments = new FilteredList<>(assignments);
    private final Currency                                          systemCurrency      = CurrencyService.getSystemCurrency();
    private final EmployeeService                                   employeeService;
    private final TeamService                                       teamService;
    private final ProjectService                                    projectService;
    private final AssignmentService                                 assignmentService;


    // ************************ Constructor ************************
    public Model() {
        try {
            this.employeeService = new EmployeeService();
            this.teamService = new TeamService();
            this.projectService = new ProjectService();
            this.assignmentService = new AssignmentService();

            updateAllProperties();
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
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

    /**
     * Get hourly rate of a project
     */
    public double getHourlyRate(Project project){
        // List of assignments related to the provided project
        List<Assignment> assignmentList = Mapper.projectAssignmentMapper(project, assignments);

        return new RateService(project, assignmentList).getHourlyRate();
    }



    /**
     * Get daily rate of a project
     */
    public double getDailyRate(Project project){
        // List of assignments related to the provided project
        List<Assignment> assignmentList = Mapper.projectAssignmentMapper(project, assignments);

        return new RateService(project, assignmentList).getDailyRate();
    }

    /**
     * Get direct costs of a project (production resource employees)
     */
    public double getDirectCosts(Project project) {
        // List of assignments related to the provided project
        List<Assignment> assignmentList = Mapper.projectAssignmentMapper(project, assignments);

        return new RateService(project, assignmentList).getDirectCosts();
    }

    /**
     * Get overhead costs of a project (overhead employees)
     */
    public double getOverheadCosts(Project project) {
        // List of assignments related to the provided project
        List<Assignment> assignmentList = Mapper.projectAssignmentMapper(project, assignments);

        return new RateService(project, assignmentList).getOverheadCosts();
    }

    /**
     * Get total costs for a project
     */
    public double getTotalCosts(Project project){
        // List of assignments related to the provided project
        List<Assignment> assignmentList = Mapper.projectAssignmentMapper(project, assignments);

        return new RateService(project, assignmentList).getTotalCosts();
    }

    /**
     * Get overall hourly rates of all the projects
     */
    public double getTotalHourlyRate(){
        return projects.stream()
                .mapToDouble(this::getHourlyRate)
                .sum();
    }

    /**
     * Get overall daily rates of all the projects
     */
    public double getTotalDailyRate(){
        return projects.stream()
                .mapToDouble(this::getDailyRate)
                .sum();
    }


    /**
     * Get total costs of all the projects
     */
    public double getTotalCosts(){
        return projects.stream()
                .mapToDouble(this::getTotalCosts)
                .sum();
    }

    /**
     * Calculate total utilization percentage for an employee
     */
    public double getTotalUtils(Employee employee){
        return EmployeeService.getTotalUtilizationPercentage(employee, assignments);
    }


    /**
     * Get list of assignments for a project
     */
    public List<Assignment> getProjectAssignments(Project project) {
        return Mapper.projectAssignmentMapper(project, assignments);
    }


    /**
     * Get the list of teams for a project
     */
    public List<Team> getProjectTeams(Project project) {
        return Mapper.projectTeamMapper(project, assignments);
    }

    /**
     * List of assignments which their linked employees are overhead
     */
    public List<Assignment> getOverheadAssignments(Project project) {
        return ProjectService.projectOverheadAssignments(project, assignments);
    }

    /**
     * List of assignments which their linked employees are considered as production resource
     */
    public List<Assignment> getProductionResourceAssignments(Project project) {
        return ProjectService.projectProductResourceAssignments(project, assignments);
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
    public void setTeam(Team team){
        this.team.set(team);
    }

    /**
     * Set Project object
     */
    public void setProject(Project project){
        this.project.set(project);
    }

    /**
     * Set the currently working project name
     */
    public void setProjectName(String name){
        this.project.get().setName(name);
    }

    /**
     * Set the currently working project country
     */
    public void setProjectCountry(Country country){
        this.project.get().setCountry(country);
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
    public void setEmployeeFirstName(String firstName) throws ExceptionHandler {
        employee.get()
                .setFirstName(firstName);
    }

    /**
     * Set Employee's last name
     */
    public void setEmployeeLastName(String lastName) throws ExceptionHandler {
        employee.get()
                .setLastName(lastName);
    }

    /**
     * Set Employee's email
     */
    public void setEmployeeEmail(String email) throws ExceptionHandler {
        employee.get()
                .setEmail(email);
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

//    /**
//     * Retrieve latest employee changes for Contract and Projects
//     */
//    private void setEmployeeHistory(Employee employee) throws ExceptionHandler{
//        this.history.set(employeeService.getEmployeeHistory(employee));
//    }


    // ************************ Utilities *****************************
    private void updateAllProperties() throws ExceptionHandler{
        setEmployees();
        setProjects();
        setTeams();
        setAssignments();
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
     * Assign the assignment (This is the moment that user clicks on add new project)
     */
    public boolean assignAssignmentToEmployee() throws ExceptionHandler{
        List<Assignment> assignmentList = Mapper.employeeAssignmentMapper(employee.get(), assignments);

        // Validating utilization percentage for new assignment
        assignmentService.checkIllegalUtilization(this.assignment.get().getUtilizationPercentage(), assignmentList);

        // if no exception occurred, insert the assignment to the database
        if (assignmentService.create(this.assignment.get())){
            // Update the list of assignments without reloading from database
            this.assignments.add(assignment.get());

            return true;
        };

        return false;

    }

    /**
     * Convert an amount of money from local currency to system preferred currency
     */
    public double getConvertedValue(Currency localCurrency, double amount) {
        return CurrencyService.convert(localCurrency, systemCurrency, amount);
    }

    /**
     * Removes an assignment from an employee (employee object is inside the assignment object)
     */
    public boolean deleteAssignment(Assignment assignment) throws ExceptionHandler {
        // If succeed
        if (assignmentService.delete(assignment)){
            // Remove the assignment from the assignments list
            assignments.remove(assignment);

            return true;
        }

        // If failed
        return false;
    }

    /**
     * Creates a project based on the currently working project object
     * @throws ExceptionHandler if something goes wrong in the creation process.
     */
    public boolean createProject() throws ExceptionHandler{
        // If succeed to create
        if (projectService.create(this.project.get())) {
            // Adds the project to the list
            projects.add(project.get());

            return true;
        }

        // If failed to create
        return false;
    }


    /**
     * Create employee based on the currently working object
     */
    public boolean createEmployee() throws ExceptionHandler{
        this.employee.get().setContract(this.contract.get()); // Setting currently filled contract to the currently working employee

        // If succeed to create
        if (employeeService.create(employee.get())){
            // Employee creation was successful, add new object to the employees list without reloading it from database
            // However, ID of contract and employee object has been updated after creation in database
            employees.add(employee.get());

            return true;
        }

        // If failed to create
        return false;
    }

    public void setAssignmentEmployee(Employee employee) {
        this.assignment.get().setEmployee(employee);
    }

    public void setAssignmentProject(Project project) {
        this.assignment.get().setProject(project);
    }

    public void setAssignmentTeam(Team team) {
        this.assignment.get().setTeam(team);
    }

    public void setAssignmentEmployeeType(EmployeeType employeeType) {
        this.assignment.get().setEmployeeType(employeeType);
    }

    public void setAssignmentUtilizationPercentage(double amount) {
        this.assignment.get().setUtilizationPercentage(amount);
    }

    /**
     * Delete a given employee from database and memory, also assignments related to that employee will be deleted
     */
    public boolean deleteEmployee(Employee employee) throws ExceptionHandler{
        // Validate deleting from database
        if (employeeService.delete(employee)){
            // List of assignments related to the given employee in order to delete
            List<Assignment> assignmentList = Mapper.employeeAssignmentMapper(employee, assignments);

            for (Assignment a: assignmentList){
                deleteAssignment(a); // remove assignment from db
                assignments.remove(a); // remove assignment from memory
            }

            employees.remove(employee); // remove employee object from the list

            return true;
        }
        return false;
    }



//    // ****************** LAB *******************


    /**
     * Filter assignments based on employee in order to show in the table
     */
    public FilteredList<Assignment> filter(Employee employee) {
        filteredAssignments.setPredicate(
                new Predicate<Assignment>() {
                    @Override
                    public boolean test(Assignment assignment) {
                        return assignment.getEmployee().equals(employee);
                    }
                }
        );
        return filteredAssignments;
    }

    /**
     * Filter assignments based on employee in order to show in the project
     */
    public FilteredList<Assignment> filter(Project project) {
        filteredAssignments.setPredicate(
                new Predicate<Assignment>() {
                    @Override
                    public boolean test(Assignment assignment) {
                        return assignment.getProject().equals(project);
                    }
                }
        );
        return filteredAssignments;
    }

    public boolean deleteProject(Project project) throws ExceptionHandler{
        if (projectService.delete(project)){
            projects.remove(project);

            return true;
        }

        return false;
    }

    public boolean updateEmployee() throws ExceptionHandler{
        // Set the updated contract to the employee
        employee.get().setContract(contract.get());


        if (employeeService.update(employee.get())){

            // Updating the new object in the observableList
            for (int i = 0; i < employees.size(); i++){
                if (employees.get(i).getId() == employee.get().getId()){
                    employees.set(i, employee.get());
                }
            }
            return true;
        }

        return false;
    }

    public boolean updateProject() throws ExceptionHandler {
        if (projectService.update(project.get())){

            // Updating the object within the observableList
            for (int i = 0; i < projects.size(); i++){
                if (projects.get(i).getId() == project.get().getId()){
                    projects.set(i, project.get());
                }
            }

            // If everything went well
            return true;
        }

        // If failed
        return false;
    }

    public boolean deleteTeam() throws ExceptionHandler{
        if (teamService.delete(team.get())){

            // Remove the team from cached list
            teams.remove(team.get());

            // If everything went well
            return true;
        }

        // If failed
        return false;
    }

    public void setTeamName(String name){
        this.team.get().setName(name);
    }

    public boolean createTeam() throws ExceptionHandler{
        // If succeed to create updates the team ID from the db
        if (teamService.create(team.get())){
            // Update the list of teams
            teams.add(team.get());

            return true;
        }

        // If failed to create
        return false;
    }

    public boolean updateTeam() throws ExceptionHandler{
        if (teamService.update(team.get())){

            // Updating the object within the observableList
            for (int i = 0; i < teams.size(); i++){
                if (teams.get(i).getId() == team.get().getId()){
                    teams.set(i, team.get());
                }
            }

            // If everything went well
            return true;
        }

        // If failed
        return false;
    }
}
