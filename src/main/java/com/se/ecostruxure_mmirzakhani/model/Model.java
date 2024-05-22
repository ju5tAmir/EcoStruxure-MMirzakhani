package com.se.ecostruxure_mmirzakhani.model;

import com.se.ecostruxure_mmirzakhani.be.*;
import com.se.ecostruxure_mmirzakhani.be.Currency;
import com.se.ecostruxure_mmirzakhani.bll.*;
import com.se.ecostruxure_mmirzakhani.bll.rate.RateService;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;
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
    private final ObservableList        <RateService>               rateServices        = FXCollections.observableArrayList();
    private final ObservableList        <ProjectMemberLinker>       projectMemberLinker = FXCollections.observableArrayList();

    private final ObservableList        <ProjectService>            projectServices     = FXCollections.observableArrayList();

    private final ObservableMap         <Project, ObservableList<ProjectMember>> projectToMembers  = FXCollections.observableHashMap() ;

    private final EmployeeService                                   employeeService     = new EmployeeService(currency.get());
    private final TeamService                                       teamService         = new TeamService(currency.get());
    private final CountryService                                    countryService      = new CountryService(currency.get());

    private final ObservableList<RateService> mockRateServices = FXCollections.observableArrayList();

    // ************************ Constructor ************************
    public Model(){
        try {
            setEmployees();
            setProjects();
            setTeams();

            setProjectMembers();

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
    public ObservableList<Employee> getAllEmployees() throws ExceptionHandler {
//        setEmployees();
        return employees;
    }

    /**
     * Get all the Teams
     */
    public List<Team> getAllTeams() throws ExceptionHandler {
//        setTeams();
        return teams;
    }

    /**
     * Get all the Projects
     */
    public ObservableList<Project> getAllProjects() throws ExceptionHandler {
//        setProjects();
        return projects;
    }

//    /**
//     * Get all the employees with their projects as HashMap
//     */
//    public ObservableMap<Employee, List<Project>> getEmployeesProjects() throws ExceptionHandler{
//        setEmployeesProjects();
//        return employeeProjects;
//    }
//
//    /**
//     * Get all the teams with their projects(workers) as HashMap
//     */
//    public ObservableMap<Team, List<Project>> getAllTeamsProjects() throws ExceptionHandler{
//        setTeamsProjects();
//        return teamProjects;
//    }

//    /**
//     * Calculate and return the total cost for a given team based on its projects.
//     * @param team The Team object for which the total cost is to be calculated.
//     * @return The total cost for all projects associated with the given team.
//     */
//    public double getTotalCost(Team team) {
//        // Retrieve the list of projects associated with the given team from the teamProjects map
//        List<Project> projects = teamProjects.get(team);
//
//        // Calculate and return the total cost for the retrieved list of projects using TeamService
//        return teamService.getTotalCost(projects);
//    }

    /**
     * Get current currency of the system (default EUR)
     */
    public Currency getCurrency(){
        return currency.get();
    }
//
//    /**
//     * Get the given team hourly rate
//     */
//    public double getHourlyRate(Team team){
//        // Retrieve the list of projects associated with the given team from the teamProjects map
//        List<Project> projects = teamProjects.get(team);
//
//        return teamService.getHourlyRate(projects);
//    }
//
//    /**
//     * Get the given team daily rat
//     */
//    public double getDailyRate(Team team){
//        // Retrieve the list of projects associated with the given team from the teamProjects map
//        List<Project> projects = teamProjects.get(team);
//
//        return teamService.getDailyRate(projects);
//    }

//
//    /**
//     * Get a list of projects for a given Team
//     */
//    public List<Project> getTeamProjects(Team team) throws ExceptionHandler{
//        setTeamsProjects();
//        return teamProjects.get(team);
//    }

    /**
     * Get a random Team
     */
    public Team getRandomTeam() throws ExceptionHandler{
        return teams.get(new Random().nextInt(teams.size()));
    }

//    /**
//     * Get a list of working projects for a given employee
//     */
//    public List<Project> getEmployeeProjects(Employee employee) throws ExceptionHandler{
//        setEmployeesProjects();
//        return employeeProjects.get(employee);
//    }

    /**
     * Get a random employee
     */
    public Employee getRandomEmployee(){
        return employees.get(new Random().nextInt(employees.size()));
    }

//    /**
//     * Get total utilization percentage for a given employee based on all the working projects
//     */
//    public double getTotalUtilizationPercentage(Employee employee){
//        return employeeService.getTotalUtilization(employeeProjects.get(employee));
//    }

//    /**
//     * Mandatory 3:  As a user I would like to group a day rate or hourly rate based on the following
//     * information
//     * Geography or country
//     */
//    public double getHourlyRate(Country country){
//
//        return countryService.getHourlyRateForCountry(country, projects);
//    }
//    public double getDailyRate(Country country){
//
//        return countryService.getDailyRateForCountry(country, projects);
//    }

    /**
     * Get employee history contains Contract and Projects changes
     */
    public History getEmployeeHistory(Employee employee) throws ExceptionHandler{
        // Set history
        setEmployeeHistory(employee);

        return history.get();
    }

//    /**
//     * Get total hourly rate for teams
//     */
//    public double getTotalHourlyRate(){
//        double rate = 0;
//        for (Team t: teams){
//
//            rate += getHourlyRate(t);
//        }
//        return rate;
//    }
//
//    /**
//     * Get total daily rate for all the teams
//     */
//    public double getTotalDailyRate(){
//        double rate = 0;
//        for (Team t: teams){
//            rate += getDailyRate(t);
//        }
//        return rate;
//    }
//
//


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

//    /**
//     * Retrieve and update all the objects first, then maps all the Employees to their Projects
//     */
//    private void setEmployeesProjects() throws ExceptionHandler{
//        // Map all the employees to their projects
//        HashMap<Employee, List<Project>> employeeListHashMap = Mapper.allEmployeesToProjects(employees, projects);
//
//        for (Employee e: employeeListHashMap.keySet()){
//            employeeProjects.put(e, employeeListHashMap.get(e));
//        }
//    }

//    /**
//     * Update the employeeProjects HashMap with a new item
//     */
//    public void addEmployeeProjects(Employee employee, List<Project> projects){
//        // ToDo: Do it in DB and then if OK then put
//        employeeProjects.put(employee, projects);
//    }
//
//    /**
//     * Retrieve and update all the objects first, then maps all the Teams to their Projects
//     */
//    private void setTeamsProjects() throws ExceptionHandler{
//        // Map all the teams to their projects
//        HashMap<Team, List<Project>> teamListHashMap = Mapper.allTeamsToProjects(teams, projects);
//
//        for (Team t: teamListHashMap.keySet()){
//            teamProjects.put(t, teamListHashMap.get(t));
//        }
//    }

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

//    /**
//     * Set employee object for the currently working project
//     */
//    public void setProjectEmployee(Employee employee){
//        this.project.get().setEmployee(employee);
//    }

//    /**
//     * Set team object for the currently working project
//     */
//    public void setProjectTeam(Team team){
//        this.project.get().setTeam(team);
//    }
//

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

        // Validate
        this.projects.add(project.get());
    }



//    /**
//     * This method will update the teamProjects list with newly created employee or team Projects after their creation.
//     * Without requesting from database.
//     */
//    private void updateTeamProjects() throws ExceptionHandler{
//        for (Project p: projects){
//            this.teamProjects.get(p.getTeam()).add(p);
//        }
//    }

//    // ****************** LAB *******************




    public ObservableList<RateService> getAllRates() {
        setAllRates();
        return rateServices;
    }

    public void setAllRates(){
        rateServices.clear();
        for (Project p: projects){
            this.rateServices.add(new RateService(p, getProjectMembers(p)));
        }
    }

    public ObservableList<ProjectMember> getProjectMembers(Project project){

        // When the project doesn't have any members, it will not return null but an empty observableArrayList, to prevent null pointer
        return projectToMembers.computeIfAbsent(project, k -> FXCollections.observableArrayList());
    }

    /**
     * Links the members of all the projects to their associated project
     */
    public void setProjectMembers() {
        // Map all the projects to their members
        HashMap<Project, List<ProjectMember>> projectToMembersHashMap = Mapper.mapAllTheProjectsToMembers();

        for (Map.Entry<Project, List<ProjectMember>> entry : projectToMembersHashMap.entrySet()) {
            Project project = entry.getKey();
            List<ProjectMember> members = entry.getValue();

            // Create an ObservableList from the List
            ObservableList<ProjectMember> observableMembers = FXCollections.observableArrayList(members);

            // Put the project and its ObservableList of members into the ObservableMap
            projectToMembers.put(project, observableMembers);
        }

    }


    /**
     * Get list of teams associated with a project
     */
    public Set<Team> getProjectTeams(Project project) {
        // First getting project members, then fetching a list of teams associated with that project
        return Mapper.projectToTeamsMapper(projectToMembers.get(project));
    }

    /**
     * Get RateService object for a given project
     */
    public RateService getRate(Project project){
        return new RateService(project, projectToMembers.get(project));
    }


    /**
     * Convert a given amount of money from local currency to system currency
     */
    public double convertToSystemCurrency(Currency currency, double amount){
        return CurrencyService.convert(currency, this.currency.get(), amount);
    }

//    public double getTotalUtilizationPercentage(Employee employee){
//    }

    public List<Project> getEmployeeProjects(Employee employee){
        return employeeService.getAllProjects(employee);
    }

    public ObservableList<Project> getProjects() {
//        projects.clear();
        return projects;
    }

    public void setProjectName(Project project){
        this.project.set(project);
    }

    public void setProjectMemberEmployee(Employee employee){
        this.projectMember.get().setEmployee(employee);
    }

    public void setProjectMemberTeam(Team team){
        this.projectMember.get().setTeam(team);
    }

    public void setProjectMemberUtilizationPercentage(double utilizationPercentage){
        this.projectMember.get().setUtilizationPercentage(utilizationPercentage);
    }

    public void assignProjectMemberToProject(){
        projectToMembers.put(this.project.get(), this.projectMembers);
    }





    public ObservableList<ProjectMemberLinker> getProjectMemberLinker(Employee employee){

        return projectMemberLinker;
    }

    public void setProjectLinker(Employee employee){

        projectMemberLinker.clear();
        for (Project p: projectToMembers.keySet()){
            for(ProjectMember pm: projectToMembers.get(p)){

                if (employee.equals(pm.getEmployee())) {

                    this.projectMemberLinker.add(new ProjectMemberLinker(p, pm));
                }
            }
        }
    }

    public void addProjectMemberLinker(Project project, ProjectMember projectMember) {
        this.projectMemberLinker.add(new ProjectMemberLinker(project, projectMember));

//        System.out.println(projectMembers.getLast());
        if (projectToMembers.containsKey(project)){
            List<ProjectMember> list = projectToMembers.get(project);
            list.add(projectMember);
        }
    }
    public void removeProjectMemberLinker(ProjectMemberLinker projectMemberLinker) {
        this.projectMemberLinker.remove(projectMemberLinker);


        if (projectToMembers.containsKey(projectMemberLinker.getProject())){
            List<ProjectMember> list = projectToMembers.get(projectMemberLinker.getProject());

            list.remove(projectMemberLinker.getProjectMember());
            // Remove the value from the list if it exists
//            list.remove(Integer.valueOf(valueToRemove));
        }

    }

    public void removeValueFromList(Map<String, List<Integer>> map, String key, int valueToRemove) {
        // Check if the map contains the key
        if (map.containsKey(key)) {
            List<Integer> list = map.get(key);
            // Remove the value from the list if it exists
            list.remove(Integer.valueOf(valueToRemove));  // Integer.valueOf is used to ensure it matches the object type Integer
        }
    }
    public double getTotalUtil(Employee employee){
        double total = 0;
        for (ProjectMemberLinker pml: projectMemberLinker){
            if (pml.getProjectMember().getEmployee().equals(employee)){
                total += pml.getProjectMember().getUtilizationPercentage();
            }
        }
        return total;
    }


    /**
     * Create employee object with projects related to it, if it was successful, return true
     */
    public boolean createEmployee() throws ExceptionHandler{
        // Insert the currently working employee into database.
        if (employeeService.create(employee.get(), projects)){
            // If database insert was successful
            employees.add(employee.get());

            // Clear objects to prevent conflicts with the future creations
            clearEmployeeObjects();
        }
        return false;
    }

    public void createProject(){
        ObservableList<ProjectMember> list = FXCollections.observableArrayList();

        projectToMembers.put(project.get(), list);

        projects.add(project.get());

        rateServices.add(new RateService(project.get(), list));
    }


    public ObservableList<RateService> getMockRateServices(){
        return mockRateServices;
    }

   public void setMockRateServices(List<RateService> rateServices){
        this.mockRateServices.setAll(rateServices);
   }

    public double getTotalHourlyRate() {
        double total = 0;
        for (RateService rs: rateServices){
            total += rs.getHourlyRate();
        }
        return total;
    }

    public double getTotalDailyRate() {
        double total = 0;
        for (RateService rs: rateServices){
            total += rs.getDailyRate();
        }
        return total;
    }

    public double getTotalCost() {
        double total = 0;
        for (RateService rs: rateServices){
            total += rs.getTotalCosts();
        }
        return total;
    }
}
