package com.se.ecostruxure_mmirzakhani.dal;

import com.se.ecostruxure_mmirzakhani.be.*;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeDAO {
    List<Employee> employees = new ArrayList<>();
    HashMap<Team, List<Employee>> teams = new HashMap<>();

//    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public EmployeeDAO() {

        // Initial Teams
        Team teamIT = new Team(1, "IT Team");
        Team teamHR = new Team(2, "HR Team");
//
//
//        // Initial Employees
//        Employee employee1 = new Employee();
//        employee1.setId(1);
//        employee1.setFirstName("Guss");
//        employee1.setLastName("Frank");
//
//        // Profiles
//        // Profile 1
//        Profile profile1 = new Profile();
//        profile1.setId(1);
//        profile1.setTeam(teamIT);
//        profile1.setCountry(Country.DENMARK);
//        profile1.setCurrency(Currency.DKK);
//        profile1.setAnnualSalary(400_000);       // 400K DKK
//        profile1.setFixedAnnualAmount(20_000);   // 20K  DKK
//        profile1.setAnnualWorkHours(1920);
//        profile1.setAverageDailyWorkHours(8);
//        profile1.setOverhead(true);
//        profile1.setOverheadPercentage(20);
//        profile1.setUtilizationPercentage(50);
//
//        // Profile 2
//        Profile profile2 = new Profile();
//        profile2.setId(2);
//        profile2.setTeam(teamHR);
//        profile2.setCountry(Country.UNITED_STATES);
//        profile2.setRegion(regionEU);
//        profile2.setCurrency(Currency.USD);
//        profile2.setAnnualSalary(60_000);       // 60K USD
//        profile2.setFixedAnnualAmount(5_000);   // 5K  USD
//        profile2.setAnnualWorkHours(1920);
//        profile2.setAverageDailyWorkHours(8);
//        profile2.setOverhead(true);
//        profile2.setOverheadPercentage(20);
//        profile2.setUtilizationPercentage(20);
//
//
//        // ************* 2 ******************
//
//
//        // Initial Employees
//        Employee employee2 = new Employee();
//        employee2.setId(2);
//        employee2.setFirstName("Frank");
//        employee2.setLastName("Vallie");
//
//        // Region
//        Region regionASIA = new Region();
//        regionEU.setId(2);
//        regionEU.setName("ASIA");
//
//        // Profiles
//        // Profile 3
//        Profile profile3 = new Profile();
//        profile3.setId(3);
//        profile3.setTeam(teamIT);
//        profile3.setCountry(Country.SWEDEN);
//        profile3.setRegion(regionEU);
//        profile3.setCurrency(Currency.EUR);
//        profile3.setAnnualSalary(50_000);
//        profile3.setFixedAnnualAmount(2_000);
//        profile3.setAnnualWorkHours(1920);
//        profile3.setAverageDailyWorkHours(8);
//        profile3.setOverhead(true);
//        profile3.setOverheadPercentage(20);
//        profile3.setUtilizationPercentage(80);
//
//
//
//        employee2.addProfile(profile3);
//
//        List<Employee> teamITEmployees = new ArrayList<>();
//        teamITEmployees.add(employee1);
//        teams.put(teamIT, employee1)
//
//        employees.add(employee1);
//        employees.add(employee2);
    }


    public List<Employee> getAllEmployees(){
        return employees;
    }

//    public List<Employee> getTeam(Team team){
//        List<Employee> employeeList = new ArrayList<>();
//        for (Employee employee: employees){
//            for (Profile profile: employee.getProfiles()){
//                if (team.getName().equals(profile.getTeam().getName())) {
//                    employeeList.add(employee);
//                }
//            }
//        }
//        return employeeList;
//    }

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
