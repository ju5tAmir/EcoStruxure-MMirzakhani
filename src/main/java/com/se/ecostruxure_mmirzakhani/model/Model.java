package com.se.ecostruxure_mmirzakhani.model;

import com.se.ecostruxure_mmirzakhani.be.*;
import com.se.ecostruxure_mmirzakhani.bll.EmployeeService;
import com.se.ecostruxure_mmirzakhani.bll.Mapper;
import com.se.ecostruxure_mmirzakhani.bll.TeamLogic;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.utils.Validate;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.List;

public class Model {
    private final SimpleObjectProperty  <Employee>                  employee            = new SimpleObjectProperty<>(new Employee());
    private final SimpleObjectProperty  <Team>                      team                = new SimpleObjectProperty<>(new Team());
    private final SimpleObjectProperty  <Project>                   project             = new SimpleObjectProperty<>(new Project());
    private final ObservableList        <Employee>                  employees           = FXCollections.observableArrayList();
    private final ObservableList        <Team>                      teams               = FXCollections.observableArrayList();
    private final ObservableList        <Project>                   projects            = FXCollections.observableArrayList();
    private final ObservableMap         <Employee, List<Project>>   employeeProjects    = FXCollections.observableHashMap();  // All projects for an employee
    private final ObservableMap         <Team,     List<Project>>   teamProjects        = FXCollections.observableHashMap();          // All projects for a team
    private final EmployeeService                                   logic               = new EmployeeService();
    private final TeamLogic                                         teamLogic           = new TeamLogic();

    // ************************ Constructor ************************
    public Model(){

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
        employees.setAll(logic.getAllEmployees());
    }

    /**
     * Retrieve and updates the latest Team list
     */
    private void setTeams() throws ExceptionHandler {
        teams.setAll(logic.getAllTeams());
    }

    /**
     * Retrieve and updates the latest Project list
     */
    private void setProjects() throws ExceptionHandler {
        projects.setAll(logic.getAllProjects());
    }

    /**
     * Retrieve and update all the objects first, then maps all the Employees to their Projects
     */
    private void setEmployeesProjects() throws ExceptionHandler{
        updateAllProperties();

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

    // ************************ Utilities *****************************
    private void updateAllProperties() throws ExceptionHandler{
        setEmployees();
        setTeams();
        setProjects();
    }



    // ****************** LAB *******************








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
