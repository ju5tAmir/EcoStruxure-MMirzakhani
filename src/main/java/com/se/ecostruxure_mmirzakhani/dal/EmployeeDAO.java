package com.se.ecostruxure_mmirzakhani.dal;

import com.se.ecostruxure_mmirzakhani.be.*;
import com.se.ecostruxure_mmirzakhani.gui.IController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class EmployeeDAO {
    // ToDo: These attributes must be removed and replaces in the proper method and fetch these values from DB
    private final List<Employee> employees = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final List<Project> projects = new ArrayList<>();
    private final List<ProjectMember> projectMembers = new ArrayList<>();
    private final HashMap<Project, List<ProjectMember>> projectToMembers = new HashMap<>();

//    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public EmployeeDAO() {
        initMockData();

    }

    private void initMockData(){
        // Initial Teams
        Team it = new Team(1, "IT");
        Team hr = new Team(2, "HR");
        teams.add(it); teams.add(hr);

        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setFirstName("Albert");
        employee1.setLastName("Einstein");

        Contract contract1 = new Contract();
        contract1.setId(1);
        contract1.setCurrency(Currency.USD);
        contract1.setAnnualSalary(80_000);       // 80K USD
        contract1.setFixedAnnualAmount(5_000);   // 5K USD
        contract1.setAnnualWorkHours(2000);
        contract1.setAverageDailyWorkHours(8);
        contract1.setOverhead(true);
        contract1.setOverheadPercentage(20);
        contract1.setTimeLine(new TimeLine(LocalDateTime.now().minusMonths(2), LocalDateTime.MAX));
        employee1.setContract(contract1);

        Project p1 = new Project(1, "Project Alpha", Country.DENMARK);
        Project p2 = new Project(2, "Project Beta", Country.UNITED_STATES);

        projects.add(p1); projects.add(p2);

        // ***** 2 *****
        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setFirstName("Frank");
        employee2.setLastName("Vallie");

        Contract contract2 = new Contract();
        contract2.setId(2);
        contract2.setCurrency(Currency.EUR);
        contract2.setAnnualSalary(50_000);
        contract2.setFixedAnnualAmount(2_000);
        contract2.setAnnualWorkHours(1920);
        contract2.setAverageDailyWorkHours(8);
        contract2.setOverhead(true);
        contract2.setOverheadPercentage(20);
        contract2.setTimeLine(new TimeLine(LocalDateTime.now().minusMonths(10), LocalDateTime.MAX));

        employee2.setContract(contract2);

        Project p3 = new Project(3, "Project Charlie", Country.SWEDEN);
        projects.add(p3);


        // Project Members
        ProjectMember pm1 = new ProjectMember(employee1, it, 20);
        ProjectMember pm2 = new ProjectMember(employee1, hr, 50);
        ProjectMember pm3 = new ProjectMember(employee2, it, 80);
        projectMembers.add(pm1);projectMembers.add(pm2);projectMembers.add(pm3);

        // List of project members to link
        List<ProjectMember> pmList = new ArrayList<>();
        pmList.add(pm1); pmList.add(pm2);

        // Update hashmap
        projectToMembers.put(p1, pmList);

        // Update Total Employees
        employees.add(employee1);
        employees.add(employee2);
    }


    public List<ProjectMember> getProjectMembers(Project project){
        // Retrieve all projects members from db linked to the given project

        return projectToMembers.get(project);
    }

    /**
     * Retrieve all the employees
     */
    public List<Employee> getAllEmployees(){
        return employees;
    }

//    /**
//     * Retrieve Employees of a requested Team
//     */
//    public List<Project> getTeamProjects(Team team){
//        List<Project> projectList = new ArrayList<>();
//
//        for (Project project: projects){
//            if (project.getTeam().equals(team)){
//                projectList.add(project);
//            }
//        }
//        return projectList;
//    }

    /**
     * Retrieve all the Teams
     */
    public List<Team> getAllTeams(){
        return teams;
    }

    public List<Project> getAllProjects(){
        return projects;
    }

    public boolean createEmployee(Employee employee, List<Project> projects) {
        // Create Employee + Update employee object ID

        // Add Projects    + Update project objects ID

        return true;
    }

    public History getEmployeeHistory(Employee employee){
        // Fake History ToDo: Must be replaced with real history from db

        Contract c1 =new Contract();
        c1.setAnnualSalary(50000);
        c1.setId(3);
        c1.setTimeLine(new TimeLine(LocalDateTime.now().minusYears(4), LocalDateTime.now().minusYears(3)));


        Contract c2 = new Contract();
        c2.setAnnualSalary(80000);
        c2.setTimeLine(new TimeLine(LocalDateTime.now().minusYears(2), LocalDateTime.now().minusYears(1)));


        Contract c3 = new Contract();
        c3.setAnnualSalary(99000);
        c3.setTimeLine(new TimeLine(LocalDateTime.now().minusYears(1), LocalDateTime.MAX));


        // ToDo: Don't forget to get also the latest item for Contract or Project which will not be in the History table in DB.
        List<Contract> listOfContracts = new ArrayList<>();

        listOfContracts.add(c1); listOfContracts.add(c2); listOfContracts.add(c3);

        History history = new History();
        history.setContracts(listOfContracts);



        return history;


    }



}
//
//    public boolean createEmployee(Employee employee) throws ExceptionHandler {
//        // Insert query to Employee table
//        String insertEmployee = "INSERT INTO Employees(FirstName, LastName, Region, Country) VALUES (?,?,?,?)";
//        // Insert query for connection an employee to a team (if possible)
//        String insertTeam = "INSERT INTO Team(TeamName, EmployeeID) VALUES (?,?)";
//        // Insert contract information
//        String insertContract = "INSERT INTO Contract(EmployeeID, AnnualSalary, FixedAnnualAmount, AnnualWorkHours, AverageDailyWorkHours, OverheadPercentage, UtilizationPercentage, MarkupPercentage, GrossMarginPercentage, IsOverhead) VALUES (?,?,?,?,?,?,?,?,?,?)";
//
//        try (Connection conn = dbConnection.getConnection()) {
//            // Starting a transaction
//            conn.setAutoCommit(false);
//            // Protect from update/delete for this row
//            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
//
//            // Statements
//            try (PreparedStatement employeeStatement = conn.prepareStatement(insertEmployee, Statement.RETURN_GENERATED_KEYS);
//                 PreparedStatement teamStatement = conn.prepareStatement(insertTeam);
//                 PreparedStatement contractStatement = conn.prepareStatement(insertContract)) {
//
//                // Insert employee information
//                employeeStatement.setString(1, employee.getFirstName());
//                employeeStatement.setString(2, employee.getLastName());
//                employeeStatement.setString(3, employee.getRegion().getName());
//                employeeStatement.setString(4, employee.getCountry().getValue());
//
//                employeeStatement.executeUpdate();
//
//                // Retrieve the generated id for the employee
//                try (ResultSet rs = employeeStatement.getGeneratedKeys()) {
//                    if (rs.next()) {
//                        employee.setId(rs.getInt(1));
//                    } else {
//                        throw new ExceptionHandler(ExceptionMessage.KEY_GENERATION_FAILURE.getValue());
//                    }
//                }
//
//                // Insert team details if employee is part of a team
//                if (employee.getTeam() != null) {
//                    teamStatement.setString(1, employee.getTeam().getName());
//                    teamStatement.setInt(2, employee.getId());
//                    teamStatement.addBatch();
//                }
//
//                // Insert contract information
//                contractStatement.setInt(1, employee.getId());
//                contractStatement.setDouble(2, employee.getContract().getAnnualSalary());
//                contractStatement.setDouble(3, employee.getContract().getFixedAnnualAmount());
//                contractStatement.setDouble(4, employee.getContract().getAnnualWorkHours());
//                contractStatement.setDouble(5, employee.getContract().getAverageDailyWorkHours());
//                contractStatement.setDouble(6, employee.getContract().getOverheadPercentage());
//                contractStatement.setDouble(7, employee.getContract().getUtilizationPercentage());
//                contractStatement.setDouble(8, employee.getContract().getMarkupPercentage());
//                contractStatement.setDouble(9, employee.getContract().getGrossMarginPercentage());
//                contractStatement.setBoolean(10, employee.getContract().isOverhead());
//                contractStatement.addBatch();
//
//                // Execute batches
//                employeeStatement.executeBatch();
//                teamStatement.executeBatch();
//                contractStatement.executeBatch();
//
//                // Commit transaction
//                conn.commit();
//            } catch (SQLException e) {
//                // Rollback if something went wrong
//                conn.rollback();
//                throw new ExceptionHandler(ExceptionMessage.EMPLOYEE_INSERT_FAILED.getValue(), e.getMessage());
//            }
//            return true;
//        } catch (SQLException ex) {
//
//            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), ex.getMessage());
//        }
//    }
//
//    public Employee getEmployee(int id) throws ExceptionHandler{
//        // Employee object to update
//        Employee employee = new Employee();
//
//        // SQL Query to fetch an employee by id
//        String getEmployeeQuery = "SELECT Employees.*, T.*, C.* " +
//                "FROM Employees " +
//                "JOIN dbo.Team T " +
//                "ON Employees.EmployeeID = T.EmployeeID " +
//                "JOIN dbo.Contract C ON " +
//                "Employees.EmployeeID = C.EmployeeID " +
//                "WHERE dbo.Employees.EmployeeID = ?";
//
//        try (Connection conn = dbConnection.getConnection();
//             PreparedStatement statement = conn.prepareStatement(getEmployeeQuery, Statement.RETURN_GENERATED_KEYS)){
//            statement.setInt(1, id);
//
//            // Results
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()){
//                // Set employee properties
//                employee.setFirstName(rs.getString("FirstName"));
//                employee.setLastName(rs.getString("LastName"));
//                employee.setRegion(Region.valueOf(rs.getString("Region").toUpperCase()));
//                employee.setCountry(Country.valueOf(rs.getString("Country").toUpperCase()));
//
//                // Set team properties
//                Team team = new Team();
//                team.setId(rs.getInt("TeamID"));
//                team.setName(rs.getString("TeamName"));
//                employee.setTeam(team);
//
//
//                // Set contract properties
//                Profile contract = new Profile();
//                contract.setAnnualSalary(rs.getDouble("AnnualSalary"));
//                contract.setAnnualWorkHours(rs.getDouble("AnnualWorkHours"));
//                contract.setAverageDailyWorkHours(rs.getDouble("AverageDailyWorkHours"));
//                contract.setFixedAnnualAmount(rs.getDouble("FixedAnnualAmount"));
//                contract.setOverheadPercentage(rs.getDouble("OverheadPercentage"));
//                contract.setUtilizationPercentage(rs.getDouble("UtilizationPercentage"));
//                contract.setMarkupPercentage(rs.getDouble("MarkupPercentage"));
//                contract.setGrossMarginPercentage(rs.getDouble("GrossMarginPercentage"));
//                contract.setOverhead(rs.getBoolean("IsOverhead"));
//                contract.setValidFrom(rs.getTimestamp("SysStartTime").toLocalDateTime());
//                contract.setValidUntil(rs.getTimestamp("SysEndTime").toLocalDateTime());
//
//                employee.setContract(contract);
//            }
//            return employee;
//
//        } catch (SQLException e){
//            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), e.getMessage());
//        }
//    }
//
//    public List<Employee> getAllEmployees() throws ExceptionHandler {
//        List<Employee> employees = new ArrayList<>();
//
//        // SQL Query to fetch all employees
//        String getAllEmployeesQuery = "SELECT Employees.*, T.*, C.* " +
//                "FROM Employees " +
//                "JOIN dbo.Team T " +
//                "ON Employees.EmployeeID = T.EmployeeID " +
//                "JOIN dbo.Contract C ON " +
//                "Employees.EmployeeID = C.EmployeeID";
//
//        try (Connection conn = dbConnection.getConnection();
//             PreparedStatement statement = conn.prepareStatement(getAllEmployeesQuery)) {
//
//            // Results
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                Employee employee = new Employee();
//
//                // Set employee properties
//                employee.setFirstName(rs.getString("FirstName"));
//                employee.setLastName(rs.getString("LastName"));
//                try {
//                    employee.setRegion(Region.valueOf(rs.getString("Region").toUpperCase()));
//                    employee.setCountry(Country.valueOf(rs.getString("Country").toUpperCase()));
//                } catch (RuntimeException e){
//                    employee.setRegion(Region.EUROPE);
//                    employee.setCountry(Country.DENMARK);
//                }
//
//                // Set team properties
//                Team team = new Team();
//                team.setId(rs.getInt("TeamID"));
//                team.setName(rs.getString("TeamName"));
//                employee.setTeam(team);
//
//                // Set contract properties
//                Profile contract = new Profile();
//                contract.setAnnualSalary(rs.getDouble("AnnualSalary"));
//                contract.setAnnualWorkHours(rs.getDouble("AnnualWorkHours"));
//                contract.setAverageDailyWorkHours(rs.getDouble("AverageDailyWorkHours"));
//                contract.setFixedAnnualAmount(rs.getDouble("FixedAnnualAmount"));
//                contract.setOverheadPercentage(rs.getDouble("OverheadPercentage"));
//                contract.setUtilizationPercentage(rs.getDouble("UtilizationPercentage"));
//                contract.setMarkupPercentage(rs.getDouble("MarkupPercentage"));
//                contract.setGrossMarginPercentage(rs.getDouble("GrossMarginPercentage"));
//                contract.setOverhead(rs.getBoolean("IsOverhead"));
//                contract.setValidFrom(rs.getTimestamp("SysStartTime").toLocalDateTime());
//                contract.setValidUntil(rs.getTimestamp("SysEndTime").toLocalDateTime());
//
//                employee.setContract(contract);
//
//                employees.add(employee);
//            }
//            return employees;
//
//        } catch (SQLException e) {
//            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), e.getMessage());
//        }
//    }
//
//    public boolean updateEmployee(Employee employee) throws ExceptionHandler {
//        String updateEmployee = "UPDATE Employees SET FirstName = ?, LastName = ?, Region = ?, Country = ? WHERE EmployeeID = ?";
//        String updateTeam = "UPDATE Team SET TeamName = ? WHERE EmployeeID = ?";
//        String updateContract = "UPDATE Contract SET AnnualSalary = ?, FixedAnnualAmount = ?, AnnualWorkHours = ?, AverageDailyWorkHours = ?, OverheadPercentage = ?, UtilizationPercentage = ?, MarkupPercentage = ?, GrossMarginPercentage = ?, IsOverhead = ? WHERE EmployeeID = ?";
//
//        try (Connection conn = dbConnection.getConnection()) {
//            conn.setAutoCommit(false);
//            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
//
//            try (PreparedStatement employeeStmt = conn.prepareStatement(updateEmployee);
//                 PreparedStatement teamStmt = conn.prepareStatement(updateTeam);
//                 PreparedStatement contractStmt = conn.prepareStatement(updateContract)) {
//
//                // Set parameters for updating employee
//                employeeStmt.setString(1, employee.getFirstName());
//                employeeStmt.setString(2, employee.getLastName());
//                employeeStmt.setString(3, employee.getRegion().getName());
//                employeeStmt.setString(4, employee.getCountry().getValue());
//                employeeStmt.setInt(5, employee.getId());
//                employeeStmt.executeUpdate();
//
//                // Set parameters for updating team
//                if (employee.getTeam() != null) {
//                    teamStmt.setString(1, employee.getTeam().getName());
//                    teamStmt.setInt(2, employee.getId());
//                    teamStmt.executeUpdate();
//                }
//
//                // Set parameters for updating contract
//                Profile contract = employee.getContract();
//                contractStmt.setDouble(1, contract.getAnnualSalary());
//                contractStmt.setDouble(2, contract.getFixedAnnualAmount());
//                contractStmt.setDouble(3, contract.getAnnualWorkHours());
//                contractStmt.setDouble(4, contract.getAverageDailyWorkHours());
//                contractStmt.setDouble(5, contract.getOverheadPercentage());
//                contractStmt.setDouble(6, contract.getUtilizationPercentage());
//                contractStmt.setDouble(7, contract.getMarkupPercentage());
//                contractStmt.setDouble(8, contract.getGrossMarginPercentage());
//                contractStmt.setBoolean(9, contract.isOverhead());
//                contractStmt.setInt(10, employee.getId());
//                contractStmt.executeUpdate();
//
//                // Commit the transaction
//                conn.commit();
//                return true;
//            } catch (SQLException e) {
//                // Rollback the transaction in case of SQL exception
//                conn.rollback();
//                throw new ExceptionHandler("Failed to update employee: " + e.getMessage());
//            }
//        } catch (SQLException ex) {
//            throw new ExceptionHandler("Database connection failure: " + ex.getMessage());
//        }
//    }
//
//
//    public boolean deleteEmployee(int employeeId) throws ExceptionHandler {
//        String deleteContract = "DELETE FROM Contract WHERE EmployeeID = ?";
//        String deleteTeam = "DELETE FROM Team WHERE EmployeeID = ?";
//        String deleteEmployee = "DELETE FROM Employees WHERE EmployeeID = ?";
//
//        try (Connection conn = dbConnection.getConnection()) {
//            conn.setAutoCommit(false);
//            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
//
//            try (PreparedStatement contractStmt = conn.prepareStatement(deleteContract);
//                 PreparedStatement teamStmt = conn.prepareStatement(deleteTeam);
//                 PreparedStatement employeeStmt = conn.prepareStatement(deleteEmployee)) {
//
//                contractStmt.setInt(1, employeeId);
//                contractStmt.executeUpdate();
//
//                teamStmt.setInt(1, employeeId);
//                teamStmt.executeUpdate();
//
//                employeeStmt.setInt(1, employeeId);
//                employeeStmt.executeUpdate();
//
//                conn.commit();
//                return true;
//            } catch (SQLException e) {
//                conn.rollback();
//                throw new ExceptionHandler(e.getMessage());
//            }
//        } catch (SQLException ex) {
//            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), ex.getMessage());
//        }
//    }
//}
