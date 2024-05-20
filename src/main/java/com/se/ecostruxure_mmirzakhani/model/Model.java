package com.se.ecostruxure_mmirzakhani.model;

import com.se.ecostruxure_mmirzakhani.be.*;
import com.se.ecostruxure_mmirzakhani.be.Currency;
import com.se.ecostruxure_mmirzakhani.bll.*;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.utils.Validate;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.time.LocalDateTime;
import java.util.*;

// ToDo: Lazy loading
public class Model {
    // SimpleObjectProperty attributes to handle the current instance of objects for each category.
    // These properties are updated based on user actions (e.g., CRUD operations) on a specific Employee, Contract, Team, Project, and History object.
    private final SimpleObjectProperty  <Employee>                  employee            = new SimpleObjectProperty<>(new Employee());
    private final SimpleObjectProperty  <Contract>                  contract            = new SimpleObjectProperty<>(new Contract());
    private final SimpleObjectProperty  <Team>                      team                = new SimpleObjectProperty<>(new Team());
    private final SimpleObjectProperty  <Project>                   project             = new SimpleObjectProperty<>(new Project());
    private final SimpleObjectProperty  <ProjectMember>             projectMember       = new SimpleObjectProperty<>(new ProjectMember());
    private final SimpleObjectProperty  <History>                   history             = new SimpleObjectProperty<>(new History());
    private final SimpleObjectProperty <Currency>                   currency            = new SimpleObjectProperty<>(Currency.EUR);     // System default currency is EUR

    private final ObservableList        <Employee>                  employees           = FXCollections.observableArrayList();
    private final ObservableList        <Team>                      teams               = FXCollections.observableArrayList();
    private final ObservableList        <Project>                   projects            = FXCollections.observableArrayList();
    private final ObservableList        <ProjectMember>             projectMembers      = FXCollections.observableArrayList();

    private final ObservableMap         <Project, List<ProjectMember>> projectToMembers  = FXCollections.observableHashMap() ;
    private final ObservableMap         <Employee, List<Project>>   employeeProjects    = FXCollections.observableHashMap();            // All projects for an employee

    private final EmployeeService                                   employeeService     = new EmployeeService(currency.get());
    private final TeamService                                       teamService         = new TeamService(currency.get());
    private final CountryService                                    countryService      = new CountryService(currency.get());


    // ************************ Constructor ************************
    public Model(){
        try {
            setEmployees();
            setProjects();
            setTeams();
            setEmployeesProjects();
            setTeamsProjects();
            updateRates();
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
     * Get all the employees
     */
    public List<Employee> getAllEmployees() throws ExceptionHandler {
        setEmployees();
        return employees;
    }

    /**
     * Get all the Teams
     */
    public List<Team> getAllTeams() throws ExceptionHandler {
        setTeams();
        return teams;
    }

    /**
     * Get all the Projects
     */
    public List<Project> getAllProjects() throws ExceptionHandler {
        setProjects();
        return projects;
    }

    /**
     * Get all the employees with their projects as HashMap
     */
    public ObservableMap<Employee, List<Project>> getEmployeesProjects() throws ExceptionHandler{
        setEmployeesProjects();
        return employeeProjects;
    }

    /**
     * Get all the teams with their projects(workers) as HashMap
     */
    public ObservableMap<Team, List<Project>> getAllTeamsProjects() throws ExceptionHandler{
        setTeamsProjects();
        return teamProjects;
    }

    /**
     * Calculate and return the total cost for a given team based on its projects.
     * @param team The Team object for which the total cost is to be calculated.
     * @return The total cost for all projects associated with the given team.
     */
    public double getTotalCost(Team team) {
        // Retrieve the list of projects associated with the given team from the teamProjects map
        List<Project> projects = teamProjects.get(team);

        // Calculate and return the total cost for the retrieved list of projects using TeamService
        return teamService.getTotalCost(projects);
    }

    /**
     * Get current currency of the system (default EUR)
     */
    public Currency getCurrency(){
        return currency.get();
    }

    /**
     * Get the given team hourly rate
     */
    public double getHourlyRate(Team team){
        // Retrieve the list of projects associated with the given team from the teamProjects map
        List<Project> projects = teamProjects.get(team);

        return teamService.getHourlyRate(projects);
    }

    /**
     * Get the given team daily rat
     */
    public double getDailyRate(Team team){
        // Retrieve the list of projects associated with the given team from the teamProjects map
        List<Project> projects = teamProjects.get(team);

        return teamService.getDailyRate(projects);
    }


    /**
     * Get a list of projects for a given Team
     */
    public List<Project> getTeamProjects(Team team) throws ExceptionHandler{
        setTeamsProjects();
        return teamProjects.get(team);
    }

    /**
     * Get a random Team
     */
    public Team getRandomTeam() throws ExceptionHandler{
        return teams.get(new Random().nextInt(teams.size()));
    }

    /**
     * Get a list of working projects for a given employee
     */
    public List<Project> getEmployeeProjects(Employee employee) throws ExceptionHandler{
        setEmployeesProjects();
        return employeeProjects.get(employee);
    }

    /**
     * Get a random employee
     */
    public Employee getRandomEmployee(){
        return employees.get(new Random().nextInt(employees.size()));
    }

    /**
     * Get total utilization percentage for a given employee based on all the working projects
     */
    public double getTotalUtilizationPercentage(Employee employee){
        return employeeService.getTotalUtilization(employeeProjects.get(employee));
    }

    /**
     * Mandatory 3:  As a user I would like to group a day rate or hourly rate based on the following
     * information
     * Geography or country
     */
    public double getHourlyRate(Country country){

        return countryService.getHourlyRateForCountry(country, projects);
    }
    public double getDailyRate(Country country){

        return countryService.getDailyRateForCountry(country, projects);
    }

    /**
     * Get employee history contains Contract and Projects changes
     */
    public History getEmployeeHistory(Employee employee) throws ExceptionHandler{
        // Set history
        setEmployeeHistory(employee);

        return history.get();
    }

    /**
     * Get total hourly rate for teams
     */
    public double getTotalHourlyRate(){
        double rate = 0;
        for (Team t: teams){

            rate += getHourlyRate(t);
        }
        return rate;
    }

    /**
     * Get total daily rate for all the teams
     */
    public double getTotalDailyRate(){
        double rate = 0;
        for (Team t: teams){
            rate += getDailyRate(t);
        }
        return rate;
    }




    // ************************ Setters *****************************
    /**
     * Set Team object
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
        projects.setAll(employeeService.getAllProjects());
    }

    /**
     * Retrieve and update all the objects first, then maps all the Employees to their Projects
     */
    private void setEmployeesProjects() throws ExceptionHandler{
        // Map all the employees to their projects
        HashMap<Employee, List<Project>> employeeListHashMap = Mapper.allEmployeesToProjects(employees, projects);

        for (Employee e: employeeListHashMap.keySet()){
            employeeProjects.put(e, employeeListHashMap.get(e));
        }
    }

    /**
     * Update the employeeProjects HashMap with a new item
     */
    public void addEmployeeProjects(Employee employee, List<Project> projects){
        // ToDo: Do it in DB and then if OK then put
        employeeProjects.put(employee, projects);
    }

    /**
     * Retrieve and update all the objects first, then maps all the Teams to their Projects
     */
    private void setTeamsProjects() throws ExceptionHandler{
        // Map all the teams to their projects
        HashMap<Team, List<Project>> teamListHashMap = Mapper.allTeamsToProjects(teams, projects);

        for (Team t: teamListHashMap.keySet()){
            teamProjects.put(t, teamListHashMap.get(t));
        }
    }

    /**
     * Set current currency of the system (default EUR)
     */
    public void setCurrency(Currency currency){
        this.currency.set(currency);
    }

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
     * Set Country for the currently working contract
     */
    public void setContractCountry(Country country){
        this.contract.get().setCountry(country);
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

    // ToDo: private void setOverallUtilization

    /**
     * Set overhead percentage for the currently working contract
     */
    public void setContractOverheadPercentage(double overheadPercentage){
        this.contract.get().setOverheadPercentage(overheadPercentage);
    }

    /**
     * Set overhead status for the currently working contract
     */
    public void setContractOverheadStatus(boolean isOverhead){
        this.contract.get().setOverhead(isOverhead);
    }

    /**
     * Set employee object for the currently working project
     */
    public void setProjectEmployee(Employee employee){
        this.project.get().setEmployee(employee);
    }

    /**
     * Set team object for the currently working project
     */
    public void setProjectTeam(Team team){
        this.project.get().setTeam(team);
    }

    /**
     * Set utilization percentage for the currently working project
     */
    public void setProjectUtilizationPercentage(double utilizationPercentage){
        this.project.get().setUtilizationPercentage(utilizationPercentage);
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

    private void clearEmployeeObjects(){
        this.employee.set(new Employee());
        this.project.set(new Project());
        this.team.set(new Team());
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
        this.project.get().setTimeLine(new TimeLine(LocalDateTime.now(), LocalDateTime.MAX));

        // Validate
        this.projects.add(project.get());
    }

    /**
     * Create employee object with projects related to it, if it was successful, return true
     */
    public boolean createEmployee() throws ExceptionHandler{
        // Insert the currently working employee into database.
        if (employeeService.create(employee.get(), projects)){
            // If database insert was successful
            employees.add(employee.get());

            // Update the HashMap
            employeeProjects.put(employee.get(), projects);

            updateTeamProjects();

            // Clear objects to prevent conflicts with the future creations
            clearEmployeeObjects();
        }
        return false;
    }

    /**
     * This method will update the teamProjects list with newly created employee or team Projects after their creation.
     * Without requesting from database.
     */
    private void updateTeamProjects() throws ExceptionHandler{
        for (Project p: projects){
            this.teamProjects.get(p.getTeam()).add(p);
        }
    }

    // ****************** LAB *******************


    /**
     * Get total cost for all the teams
     */
    public double getTotalCost(){
        double totalCost = 0;
        for (Team t: teams){
            totalCost += getTotalCost(t);
        }
        return totalCost;
    }


    private void updateRates() {
        for (Team t: teams){
            t.setHourlyRate(getHourlyRate(t));
            t.setDailyRate(getDailyRate(t));
            t.setTotalCost(getTotalCost(t));
        }
    }








//
//    public void setContract(Contract contract){
//        this.employee.get().setContract(contract);
//    }
//
//
//
//    /**
//     * Set Employee's country
//     */
//    public void setCountry(Country country) {
//        employee.get()
//                .setCountry(country);
//    }
//
//    /**
//     * Set Employee's region
//     */
//    public void setRegion(Region region){
//        employee.get().setRegion(region);
//    }
//
//
//    /**
//     * Set Employee's team name
//     */
//    public void setTeam(String teamName) {
//        employee.get()
//                .getTeam()
//                .setName(teamName);
//    }






//    /**
//     * Retrieves all teams along with their respective employees.
//     *
//     * @return A HashMap containing teams as keys and their employees as values.
//     * @throws ExceptionHandler if an error occurs during retrieval.
//     */
//    public HashMap<Team, List<Employee>> getAllTeams() throws ExceptionHandler {
//        // Calls a method to update the teams data
//        setTeams();
//        // Returns the updated teams data
//        return teams.get();
//    }

//    /**
//     * Retrieves and updates the list of teams from the database.
//     *
//     * @throws ExceptionHandler if an error occurs during retrieval.
//     */
//    public void setTeams() throws ExceptionHandler {
//        // Retrieves the latest list of teams from the logic layer and sets it
//        teams.set(logic.getAllTeams());
//    }
//
//    public void addEmployeeToTeam()


//    /**
//     * Set Employee's first name
//     */
//    public void setFirstName(String firstName) throws ExceptionHandler {
//        employee.get()
//                .setFirstName(Validate.validateName(firstName));
//    }
//
//    /**
//     * Set Employee's last name
//     */
//    public void setLastName(String lastName) throws ExceptionHandler {
//        employee.get()
//                .setLastName(Validate.validateName(lastName));
//    }
//
//    public void setContract(Contract contract){
//        this.employee.get().setContract(contract);
//    }
//
//
//
//    /**
//     * Set Employee's country
//     */
//    public void setCountry(Country country) {
//        employee.get()
//                .setCountry(country);
//    }
//
//    /**
//     * Set Employee's region
//     */
//    public void setRegion(Region region){
//        employee.get().setRegion(region);
//    }
//
//
//    /**
//     * Set Employee's team name
//     */
//    public void setTeam(String teamName) {
//        employee.get()
//                .getTeam()
//                .setName(teamName);
//    }
//
//    public void setAnnualSalary(double annualSalary){
//        employee.get().getContract().setAnnualSalary(annualSalary);
//    }
//    public void setFixedAnnualAmount(double fixedAnnualAmount){
//        employee.get().getContract().setFixedAnnualAmount(fixedAnnualAmount);
//    }
//    public void setAnnualWorkHours(double annualWorkHours){
//        employee.get().getContract().setAnnualWorkHours(annualWorkHours);
//    }
//    public void setAverageDailyWorkHours(double averageDailyWorkHours) {
//        employee.get().getContract().setAverageDailyWorkHours(averageDailyWorkHours);
//    }
//    public void setOverhead(boolean overhead) {
//        employee.get().getContract().setOverhead(overhead);
//    }
//    public void setOverheadPercentage(double overheadPercentage) {
//        employee.get().getContract().setOverheadPercentage(overheadPercentage);
//    }
//    public void setUtilizationPercentage(double utilizationPercentage) {
//        employee.get().getContract().setUtilizationPercentage(utilizationPercentage);
//    }
//    public void setMarkupPercentage(double markupPercentage) {
//        employee.get().getContract().setMarkupPercentage(markupPercentage);
//    }
//    public void setGrossMarginPercentage(double grossMarginPercentage) {
//        employee.get().getContract().setGrossMarginPercentage(grossMarginPercentage);
//    }
//    public void setHourlyRate(double hourlyRate) {
//        employee.get().getContract().setHourlyRate(hourlyRate);
//    }
//    public void setDailyRate(double dailyRate) {
//        employee.get().getContract().setDailyRate(dailyRate);
//    }
//
//
//
//
//
//    /**
//     * Set Employee's annual salary
//     */
//    public void setAnnualSalary(String annualSalary) throws ExceptionHandler{
//        employee.get()
//                .getContract()
//                .setAnnualSalary(Validate.validateDouble(annualSalary));
//    }
//
//    /**
//     * Set Employee's fixed annual amount
//     */
//    public void setFixedAnnualAmount(String fixedAnnualAmount) throws ExceptionHandler {
//        employee.get()
//                .getContract()
//                .setFixedAnnualAmount(Validate.validateDouble(fixedAnnualAmount));
//    }
//
//    /**
//     * Set Employee's annual work hours
//     */
//    public void setAnnualWorkHours(String annualWorkHours) throws ExceptionHandler {
//        employee.get()
//                .getContract()
//                .setAnnualWorkHours(Validate.validateDouble(annualWorkHours));
//    }
//
//    /**
//     * Set Employee's average daily work hours
//     */
//    public void setAverageDailyWorkHours(String averageDailyWorkHours) throws ExceptionHandler {
//        employee.get()
//                .getContract()
//                .setAverageDailyWorkHours(Validate.validateDouble(averageDailyWorkHours));
//    }
//
//    /**
//     * Set Employee's overhead percentage
//     */
//    public void setOverheadPercentage(String overheadPercentage) throws ExceptionHandler {
//        employee.get()
//                .getContract()
//                .setOverheadPercentage(Validate.validateDouble(overheadPercentage));
//    }
//
//    /**
//     * Set Employee's utilization percentage
//     */
//    public void setUtilizationPercentage(String utilizationPercentage) throws ExceptionHandler {
//        employee.get()
//                .getContract()
//                .setUtilizationPercentage(Validate.validateDouble(utilizationPercentage));
//    }
//
//    /**
//     * Set Employee's overhead status
//     */
//    public void setOverheadStatus(boolean isOverhead){
//        employee.get()
//                .getContract()
//                .setOverhead(isOverhead);
//    }
//
//    /**
//     * Creates a new employee and adds it to the list of employees.
//     *
//     * @param employee The employee object to be created and added.
//     * @throws ExceptionHandler if an error occurs during the creation process.
//     */
//    public void createEmployee() throws ExceptionHandler {
//        // ToDo: You need to send employee details to the bottom layers and if succeeded, create otherwise throw an exception from bottom layer
//        // Now It's just a demo how it can change the table view
//        employees.add(employee.get());
//        logic.createEmployee(employee.get());
//    }
//
//
//    /**
//     * Updates the employee object with the provided employee details.
//     *
//     * @param employee The employee object containing updated details.
//     */
//    public void setEmployee(Employee employee){
//        this.employee.set(employee);
//    }
//
//    /**
//     * Retrieves the current working employee object.
//     *
//     * @return The current employee object.
//     */
//    public Employee getEmployee(){
//        return employee.get();
//    }
//
//    /**
//     * @return Employee's hourly rate
//     */
//    public double getHourlyRate() throws ExceptionHandler{
//        updateRates();
//        return employee.get()
//                .getContract()
//                .getHourlyRate();
//    }
//
//    /**
//     * @return Employee's daily rate
//     */
//    public double getDailyRate() throws ExceptionHandler{
//        updateRates();
//        return employee.get()
//                .getContract()
//                .getDailyRate();
//    }
//
//    /**
//     * Method to update the Employee object with the latest daily and hourly rate
//     */
//    private void updateRates() throws ExceptionHandler{
//        logic.updateRates(employee.get());
//    }
//
//    public void createTeam(Team team) throws ExceptionHandler, SQLException {
//        teamLogic.createTeam(team);
//        teams.add(team);
//    }
//    public void updateTeam(Team team) throws ExceptionHandler, SQLException {
//        teamLogic.updateTeam(team);
//    }
//    public boolean deleteTeam(int id) throws ExceptionHandler, SQLException {
//        teamLogic.deleteTeam(id);
//        return true;
//    }
//
//    public void updateEmployee(Employee employee) throws ExceptionHandler {
//       logic.updateEmployee(employee);
//    }
//
//
//    public List<String> getTeams(){
//        List<String> teams = new ArrayList<>();
//        for (Employee e: employees){
//            teams.add(e.getTeam().getName());
//        }
//        return teams;
//    }

}
