package com.se.ecostruxure_mmirzakhani.dal;

import com.se.ecostruxure_mmirzakhani.be.*;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;
import com.se.ecostruxure_mmirzakhani.gui.IController;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static java.sql.DriverManager.getConnection;

public class EmployeeDAO {
    // ToDo: These attributes must be removed and replaces in the proper method and fetch these values from DB
    private final List<Employee> employees = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final List<Project> projects = new ArrayList<>();
    private final List<ProjectMember> projectMembers = new ArrayList<>();
    private final HashMap<Project, List<ProjectMember>> projectToMembers = new HashMap<>();

    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public EmployeeDAO() {
        //initMockData();
        dbConnection=new DBConnection();
    }

    /*private void initMockData(){
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
        contract1.setOverhead(false);
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
        ProjectMember pm4 = new ProjectMember(employee2, it, 20);
        projectMembers.add(pm1);projectMembers.add(pm2);projectMembers.add(pm3); projectMembers.add(pm4);

        // List of project members to link
        List<ProjectMember> pmList = new ArrayList<>();
        pmList.add(pm1); pmList.add(pm2); pmList.add(pm3);

        List<ProjectMember> pmList2 = new ArrayList<>();
        pmList2.add(pm4);

        // Update hashmap
        projectToMembers.put(p1, pmList);
        projectToMembers.put(p2, pmList2);


        // Update Total Employees
        employees.add(employee1);
        employees.add(employee2);
    }


    public List<ProjectMember> getProjectMembers(Project project){
        // Retrieve all projects members from db linked to the given project

        return projectToMembers.get(project);
    }

    public HashMap<Project, List<ProjectMember>> getAllProjectsWithTheirMembers(){
        for (Project p: projectToMembers.keySet()){
        }
        return projectToMembers;
    }

    /**
     * Retrieve all the employees

    public List<Employee> getAllEmployees(){
        return employees;
    }*/

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


    //Todo: handle all exceptions
    public boolean createEmployee(Employee employee) {
        String userSql = "INSERT INTO Users (first_name, last_name, email) VALUES (?, ?, ?)";
        String contractSql = "INSERT INTO Contracts (annual_salary, fixed_annual_amount, annual_work_hours, average_daily_work_hours, overhead_percentage, is_overhead, currency, valid_from, valid_until) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String employeeSql = "INSERT INTO Employees (id, contract_id) VALUES (?, ?)";

        try (Connection conn = dbConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            try (PreparedStatement userStmt = conn.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement contractStmt = conn.prepareStatement(contractSql, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement employeeStmt = conn.prepareStatement(employeeSql)) {

                // Insert into Users table
                userStmt.setString(1, employee.getFirstName());
                userStmt.setString(2, employee.getLastName());
                userStmt.setString(3, employee.getEmail());
                userStmt.executeUpdate();

                ResultSet userRs = userStmt.getGeneratedKeys();
                if (userRs.next()) {
                    employee.setId(userRs.getInt(1));
                }

                // Insert into Contracts table
                Contract contract = employee.getContract();
                contractStmt.setDouble(1, contract.getAnnualSalary());
                contractStmt.setDouble(2, contract.getFixedAnnualAmount());
                contractStmt.setDouble(3, contract.getAnnualWorkHours());
                contractStmt.setDouble(4, contract.getAverageDailyWorkHours());
                contractStmt.setDouble(5, contract.getOverheadPercentage());
                contractStmt.setBoolean(6, contract.isOverhead());
                contractStmt.setString(7, contract.getCurrency().toString());
                contractStmt.setTimestamp(8, Timestamp.valueOf(contract.getTimeLine().getValidFrom()));
                contractStmt.setTimestamp(9, Timestamp.valueOf(contract.getTimeLine().getValidUntil()));
                contractStmt.executeUpdate();

                ResultSet contractRs = contractStmt.getGeneratedKeys();
                if (contractRs.next()) {
                    contract.setId(contractRs.getInt(1));
                }

                // Insert into Employees table
                employeeStmt.setInt(1, employee.getId());
                employeeStmt.setInt(2, contract.getId());
                employeeStmt.executeUpdate();

                conn.commit(); // Commit transaction
                return true; // Success
            } catch (SQLException e) {
                conn.rollback(); // Rollback transaction on error
                throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), e.getMessage());
            } finally {
                conn.setAutoCommit(true); // Reset auto-commit to default state
            }
        } catch (ExceptionHandler | SQLException e) {
            e.printStackTrace(); // Or log the exception
            return false; // Failure
        }
    }

    public boolean updateEmployee(Employee employee) {
        String userSql = "UPDATE Users SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
        String contractSql = "UPDATE Contracts SET annual_salary = ?, fixed_annual_amount = ?, annual_work_hours = ?, average_daily_work_hours = ?, overhead_percentage = ?, is_overhead = ?, currency = ?, valid_from = ?, valid_until = ? WHERE id = ?";
        String employeeSql = "UPDATE Employees SET contract_id = ? WHERE id = ?";

        try (Connection conn = dbConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            try (PreparedStatement userStmt = conn.prepareStatement(userSql);
                 PreparedStatement contractStmt = conn.prepareStatement(contractSql);
                 PreparedStatement employeeStmt = conn.prepareStatement(employeeSql)) {

                // Update Users table
                userStmt.setString(1, employee.getFirstName());
                userStmt.setString(2, employee.getLastName());
                userStmt.setString(3, employee.getEmail());
                userStmt.setInt(4, employee.getId());
                userStmt.executeUpdate();

                // Update Contracts table
                Contract contract = employee.getContract();
                contractStmt.setDouble(1, contract.getAnnualSalary());
                contractStmt.setDouble(2, contract.getFixedAnnualAmount());
                contractStmt.setDouble(3, contract.getAnnualWorkHours());
                contractStmt.setDouble(4, contract.getAverageDailyWorkHours());
                contractStmt.setDouble(5, contract.getOverheadPercentage());
                contractStmt.setBoolean(6, contract.isOverhead());
                contractStmt.setString(7, contract.getCurrency().toString());
                contractStmt.setTimestamp(8, Timestamp.valueOf(contract.getTimeLine().getValidFrom()));
                contractStmt.setTimestamp(9, Timestamp.valueOf(contract.getTimeLine().getValidUntil()));
                contractStmt.setInt(10, contract.getId());
                contractStmt.executeUpdate();

                // Update Employees table
                employeeStmt.setInt(1, contract.getId());
                employeeStmt.setInt(2, employee.getId());
                employeeStmt.executeUpdate();

                conn.commit(); // Commit transaction
                return true; // Success
            } catch (SQLException e) {
                conn.rollback(); // Rollback transaction on error
                throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), e.getMessage());
            } finally {
                conn.setAutoCommit(true); // Reset auto-commit to default state
            }
        } catch (ExceptionHandler | SQLException e) {
            e.printStackTrace(); // Or log the exception
            return false; // Failure
        }
    }

    public List<Employee> getEmployeesByTeam(int teamId) throws ExceptionHandler {
        List<Employee> employeesByTeam = new ArrayList<>();
        String getEmployees = "SELECT * FROM Employees WHERE team_id = ?";

        try (Connection conn = dbConnection.getConnection()) {
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            try (PreparedStatement stmt = conn.prepareStatement(getEmployees)) {
                stmt.setInt(1, teamId);

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setId(rs.getInt("id"));
                    employee.setFirstName(rs.getString("first_name"));
                    employee.setLastName(rs.getString("last_name"));
                    employee.setEmail(rs.getString("email"));

                    Contract contract = new Contract();
                    contract.setId(rs.getInt("contract_id"));
                    contract.setAnnualSalary(rs.getDouble("annual_salary"));
                    contract.setFixedAnnualAmount(rs.getDouble("fixed_annual_amount"));
                    contract.setAnnualWorkHours(rs.getDouble("annual_work_hours"));
                    contract.setAverageDailyWorkHours(rs.getDouble("average_daily_work_hours"));
                    contract.setOverheadPercentage(rs.getDouble("overhead_percentage"));
                    contract.setOverhead(rs.getBoolean("is_overhead"));
                    contract.setCurrency(Currency.valueOf(rs.getString("currency")));
                    contract.setTimeLine(new TimeLine(rs.getTimestamp("valid_from").toLocalDateTime(), rs.getTimestamp("valid_until").toLocalDateTime()));

                    employee.setContract(contract);
                    employeesByTeam.add(employee);
                }

                rs.close();
            } catch (SQLException e) {
                conn.rollback();
                throw new ExceptionHandler(e.getMessage());
            }
        } catch (SQLException ex) {
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), ex.getMessage());
        }

        return employeesByTeam;
    }
    public boolean deleteEmployee(int id) throws ExceptionHandler {
        String userSql = "DELETE FROM Users WHERE id = ?";
        String contractSql = "DELETE FROM Contracts WHERE id = ?";
        String employeeSql = "DELETE FROM Employees WHERE id = ?";

        try (Connection conn = dbConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            try (PreparedStatement employeeStmt = conn.prepareStatement(employeeSql);
                 PreparedStatement userStmt = conn.prepareStatement(userSql);
                 PreparedStatement contractStmt = conn.prepareStatement(contractSql)) {

                // Delete from Employees table
                employeeStmt.setInt(1, id);
                employeeStmt.executeUpdate();

                // To delete from Users table, delete the user associated with the employee
                userStmt.setInt(1, id);
                userStmt.executeUpdate();

                // To delete from Contracts table, find the contract ID associated with the employee
                int contractId = getContractIdByEmployeeId(id);
                if (contractId != -1) {
                    contractStmt.setInt(1, contractId);
                    contractStmt.executeUpdate();
                }

                conn.commit(); // Commit transaction
                return true; // Success
            } catch (SQLException e) {
                conn.rollback(); // Rollback transaction on error
                throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), e.getMessage());
            } finally {
                conn.setAutoCommit(true); // Reset auto-commit to default state
            }
        } catch (ExceptionHandler | SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }
    }

    public List<Employee> getAllEmployees() throws ExceptionHandler {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT e.id, u.first_name, u.last_name, u.email, " +
                "c.id AS contract_id, c.annual_salary, c.fixed_annual_amount, c.annual_work_hours, " +
                "c.average_daily_work_hours, c.overhead_percentage, c.is_overhead, c.currency, " +
                "c.valid_from, c.valid_until " +
                "FROM Employees e " +
                "JOIN Users u ON e.id = u.id " +
                "JOIN Contracts c ON e.contract_id = c.id";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setEmail(rs.getString("email"));

                Contract contract = new Contract();
                contract.setId(rs.getInt("contract_id"));
                contract.setAnnualSalary(rs.getDouble("annual_salary"));
                contract.setFixedAnnualAmount(rs.getDouble("fixed_annual_amount"));
                contract.setAnnualWorkHours(rs.getDouble("annual_work_hours"));
                contract.setAverageDailyWorkHours(rs.getDouble("average_daily_work_hours"));
                contract.setOverheadPercentage(rs.getDouble("overhead_percentage"));
                contract.setOverhead(rs.getBoolean("is_overhead"));
                contract.setCurrency(Currency.valueOf(rs.getString("currency")));
                contract.setTimeLine(new TimeLine(rs.getTimestamp("valid_from").toLocalDateTime(), rs.getTimestamp("valid_until").toLocalDateTime()));

                employee.setContract(contract);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception
        } catch (ExceptionHandler e) {
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }
        return employees;
    }
    private int getContractIdByEmployeeId(int employeeId) throws SQLException, ExceptionHandler {
        String sql = "SELECT contract_id FROM Employees WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("contract_id");
            }
            return -1; // Return -1 if no contract found for the given employee
        } catch (ExceptionHandler e) {
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), e.getMessage());

        }
    }





    /*public History getEmployeeHistory(Employee employee){
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
    }*/

    public History getEmployeeHistory(Employee employee) throws ExceptionHandler {
        List<Contract> listOfContracts = new ArrayList<>();
        String sql = "SELECT * FROM Contracts WHERE employee_id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employee.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Contract contract = new Contract();
                contract.setId(rs.getInt("id"));
                contract.setAnnualSalary(rs.getDouble("annual_salary"));
                contract.setFixedAnnualAmount(rs.getDouble("fixed_annual_amount"));
                contract.setAnnualWorkHours(rs.getDouble("annual_work_hours"));
                contract.setAverageDailyWorkHours(rs.getDouble("average_daily_work_hours"));
                contract.setOverheadPercentage(rs.getDouble("overhead_percentage"));
                contract.setOverhead(rs.getBoolean("is_overhead"));
                contract.setCurrency(Currency.valueOf(rs.getString("currency")));

                // Set contract timeline
                LocalDateTime validFrom = rs.getTimestamp("valid_from").toLocalDateTime();
                LocalDateTime validUntil = rs.getTimestamp("valid_until").toLocalDateTime();
                contract.setTimeLine(new TimeLine(validFrom, validUntil));

                listOfContracts.add(contract);
            }
        } catch (SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), e.getMessage());
        }

        // Construct history object
        History history = new History();
        history.setContracts(listOfContracts);

        return history;
    }


}
