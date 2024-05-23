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
        dbConnection = new DBConnection();
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
    } */

    /**
     * Retrieve Employees of a requested Team
     */
    /*public List<Project> getTeamProjects(Team team){
        List<Project> projectList = new ArrayList<>();

        for (Project project: projects){
            if (project.getTeam().equals(team)){
                projectList.add(project);
            }
        }
        return projectList;
    }   */


    //Todo: handle all exceptions
    public boolean createEmployee(Employee employee) throws ExceptionHandler {
        String insertEmployeeSql = "INSERT INTO Employees (FirstName, LastName, Email) VALUES (?, ?, ?)";
        String insertContractSql = "INSERT INTO Contract (EmployeeID, AnnualSalary, FixedAnnualAmount, AnnualWorkHours, AverageDailyWorkHours, OverheadPercentage, Currency) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement insertEmployeeStmt = conn.prepareStatement(insertEmployeeSql, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement insertContractStmt = conn.prepareStatement(insertContractSql)) {

                // Insert into Employees
                insertEmployeeStmt.setString(1, employee.getFirstName());
                insertEmployeeStmt.setString(2, employee.getLastName());
                insertEmployeeStmt.setString(3, employee.getEmail());
                insertEmployeeStmt.executeUpdate();

                // Retrieve the generated EmployeeID
                ResultSet rs = insertEmployeeStmt.getGeneratedKeys();
                if (rs.next()) {
                    int employeeId = rs.getInt(1);
                    employee.setId(employeeId);
                } else {
                    throw new SQLException("Creating employee failed, no ID obtained.");
                }

                // Insert into Contract
                Contract contract = employee.getContract();
                if (contract != null) {
                    insertContractStmt.setInt(1, employee.getId());
                    insertContractStmt.setDouble(2, contract.getAnnualSalary());
                    insertContractStmt.setDouble(3, contract.getFixedAnnualAmount());
                    insertContractStmt.setDouble(4, contract.getAnnualWorkHours());
                    insertContractStmt.setDouble(5, contract.getAverageDailyWorkHours());
                    insertContractStmt.setDouble(6, contract.getOverheadPercentage());
                    insertContractStmt.setString(7, contract.getCurrency().name());
                    insertContractStmt.executeUpdate();
                }

                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEmployee(Employee employee) throws ExceptionHandler {
        String updateEmployeeSql = "UPDATE Employees SET FirstName = ?, LastName = ?, Email = ? WHERE EmployeeID = ?";
        String updateContractSql = "UPDATE Contract SET AnnualSalary = ?, FixedAnnualAmount = ?, AnnualWorkHours = ?, AverageDailyWorkHours = ?, OverheadPercentage = ?, Currency = ? WHERE EmployeeID = ?";

        try (Connection conn = dbConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement updateEmployeeStmt = conn.prepareStatement(updateEmployeeSql);
                 PreparedStatement updateContractStmt = conn.prepareStatement(updateContractSql)) {

                // Update Employees
                updateEmployeeStmt.setString(1, employee.getFirstName());
                updateEmployeeStmt.setString(2, employee.getLastName());
                updateEmployeeStmt.setString(3, employee.getEmail());
                updateEmployeeStmt.setInt(4, employee.getId());
                updateEmployeeStmt.executeUpdate();

                // Update Contract
                Contract contract = employee.getContract();
                if (contract != null) {
                    updateContractStmt.setDouble(1, contract.getAnnualSalary());
                    updateContractStmt.setDouble(2, contract.getFixedAnnualAmount());
                    updateContractStmt.setDouble(3, contract.getAnnualWorkHours());
                    updateContractStmt.setDouble(4, contract.getAverageDailyWorkHours());
                    updateContractStmt.setDouble(5, contract.getOverheadPercentage());
                    updateContractStmt.setString(6, contract.getCurrency().name());
                    updateContractStmt.setInt(7, employee.getId());
                    updateContractStmt.executeUpdate();
                }

                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Employee> getAllEmployees() throws ExceptionHandler {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT e.EmployeeID, e.FirstName, e.LastName, e.Email, " +
                "c.ContractID, c.AnnualSalary, c.FixedAnnualAmount, c.AnnualWorkHours, " +
                "c.AverageDailyWorkHours, c.OverheadPercentage, c.Currency " +
                "FROM Employees e " +
                "INNER JOIN Contract c ON e.EmployeeID = c.EmployeeID";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("EmployeeID"));
                employee.setFirstName(rs.getString("FirstName"));
                employee.setLastName(rs.getString("LastName"));
                employee.setEmail(rs.getString("Email"));

                Contract contract = new Contract();
                contract.setId(rs.getInt("ContractID"));
                contract.setAnnualSalary(rs.getFloat("AnnualSalary"));
                contract.setFixedAnnualAmount(rs.getFloat("FixedAnnualAmount"));
                contract.setAnnualWorkHours(rs.getFloat("AnnualWorkHours"));
                contract.setAverageDailyWorkHours(rs.getFloat("AverageDailyWorkHours"));
                contract.setOverheadPercentage(rs.getFloat("OverheadPercentage"));
                contract.setCurrency(Currency.valueOf(rs.getString("Currency")));

                employee.setContract(contract);

                employees.add(employee);
            }
        } catch (Exception e) {
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }

        return employees;
    }

    public boolean deleteEmployee(int employeeId) throws ExceptionHandler {
        String deleteAssignmentSql = "DELETE FROM Assignment WHERE EmployeeID = ?";
        String deleteContractSql = "DELETE FROM Contract WHERE EmployeeID = ?";
        String deleteEmployeeSql = "DELETE FROM Employees WHERE EmployeeID = ?";

        try (Connection conn = dbConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement deleteAssignmentStmt = conn.prepareStatement(deleteAssignmentSql);
                 PreparedStatement deleteContractStmt = conn.prepareStatement(deleteContractSql);
                 PreparedStatement deleteEmployeeStmt = conn.prepareStatement(deleteEmployeeSql)) {

                // Delete from Assignment
                deleteAssignmentStmt.setInt(1, employeeId);
                deleteAssignmentStmt.executeUpdate();

                // Delete from Contract
                deleteContractStmt.setInt(1, employeeId);
                deleteContractStmt.executeUpdate();

                // Delete from Employees
                deleteEmployeeStmt.setInt(1, employeeId);
                deleteEmployeeStmt.executeUpdate();

                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ExceptionHandler e) {
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }
    }

    public List<Employee> getEmployeesByTeam(int teamId) throws ExceptionHandler {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT e.EmployeeID, e.FirstName, e.LastName, e.Email, " +
                "c.ContractID, c.AnnualSalary, c.FixedAnnualAmount, c.AnnualWorkHours, " +
                "c.AverageDailyWorkHours, c.OverheadPercentage, c.Currency " +
                "FROM Employees e " +
                "JOIN Assignment a ON e.EmployeeID = a.EmployeeID " +
                "JOIN Contract c ON e.EmployeeID = c.EmployeeID " +
                "WHERE a.TeamID = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teamId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setId(rs.getInt("EmployeeID"));
                    employee.setFirstName(rs.getString("FirstName"));
                    employee.setLastName(rs.getString("LastName"));
                    employee.setEmail(rs.getString("Email"));

                    Contract contract = new Contract();
                    contract.setId(rs.getInt("ContractID"));
                    contract.setAnnualSalary(rs.getDouble("AnnualSalary"));
                    contract.setFixedAnnualAmount(rs.getDouble("FixedAnnualAmount"));
                    contract.setAnnualWorkHours(rs.getDouble("AnnualWorkHours"));
                    contract.setAverageDailyWorkHours(rs.getDouble("AverageDailyWorkHours"));
                    contract.setOverheadPercentage(rs.getDouble("OverheadPercentage"));
                    contract.setCurrency(Currency.valueOf(rs.getString("Currency")));

                    employee.setContract(contract);

                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }

        return employees;
    }


    public History getEmployeeHistory(Employee employee) throws ExceptionHandler {
        List<Contract> contracts = new ArrayList<>();
        List<Project> projects = new ArrayList<>();
        String contractSql = "SELECT ContractID, AnnualSalary, FromDate, ToDate " +
                "FROM Contract " +
                "WHERE EmployeeID = ?";
        String projectSql = "SELECT ProjectID, ProjectName, Country " +
                "FROM Assignment AS a " +
                "JOIN Projects AS p ON a.ProjectID = p.ProjectID " +
                "WHERE a.EmployeeID = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement contractStmt = conn.prepareStatement(contractSql);
             PreparedStatement projectStmt = conn.prepareStatement(projectSql)) {

            // Retrieve contract history
            contractStmt.setInt(1, employee.getId());
            try (ResultSet rs = contractStmt.executeQuery()) {
                while (rs.next()) {
                    Contract contract = new Contract();
                    contract.setId(rs.getInt("ContractID"));
                    contract.setAnnualSalary(rs.getDouble("AnnualSalary"));

                    // Construct the time line from FromDate to ToDate
                    LocalDateTime fromDate = rs.getTimestamp("FromDate").toLocalDateTime();
                    LocalDateTime toDate = rs.getTimestamp("ToDate").toLocalDateTime();
                    TimeLine timeLine = new TimeLine(fromDate, toDate);
                    contract.setTimeLine(timeLine);

                    contracts.add(contract);
                }
            }

            // Retrieve project history
            projectStmt.setInt(1, employee.getId());
            try (ResultSet rs = projectStmt.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project();
                    project.setId(rs.getInt("ProjectID"));
                    project.setName(rs.getString("ProjectName"));
                    project.setCountry(Country.valueOf(rs.getString("Country")));
                    projects.add(project);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }

        // ToDo: Don't forget to get also the latest item for Contract or Project which will not be in the History table in DB.
        // For this ToDo: I have implemented a second version of getEmployeeHistory below
        History history = new History();
        history.setContracts(contracts);
        history.setProjects(projects);

        return history;
    }

        /*public History getEmployeeHistory(Employee employee) throws ExceptionHandler {
            List<Contract> contracts = new ArrayList<>();
            List<Project> projects = new ArrayList<>();
            String contractSql = "SELECT TOP 1 ContractID, AnnualSalary, FromDate, ToDate " +
                    "FROM Contract " +
                    "WHERE EmployeeID = ? " +
                    "ORDER BY ToDate DESC"; // Retrieve the latest contract based on ToDate

            String projectSql = "SELECT TOP 1 p.ProjectID, p.ProjectName, p.Country " +
                    "FROM Assignment AS a " +
                    "JOIN Projects AS p ON a.ProjectID = p.ProjectID " +
                    "WHERE a.EmployeeID = ? " +
                    "ORDER BY p.ProjectID DESC"; // Retrieve the latest project based on ProjectID

            try (Connection conn = dbConnection.getConnection();
                 PreparedStatement contractStmt = conn.prepareStatement(contractSql);
                 PreparedStatement projectStmt = conn.prepareStatement(projectSql)) {

                // Retrieve contract history
                contractStmt.setInt(1, employee.getId());
                try (ResultSet rs = contractStmt.executeQuery()) {
                    while (rs.next()) {
                        Contract contract = new Contract();
                        contract.setId(rs.getInt("ContractID"));
                        contract.setAnnualSalary(rs.getDouble("AnnualSalary"));

                        // Construct the time line from FromDate to ToDate
                        LocalDateTime fromDate = rs.getTimestamp("FromDate").toLocalDateTime();
                        LocalDateTime toDate = rs.getTimestamp("ToDate").toLocalDateTime();
                        TimeLine timeLine = new TimeLine(fromDate, toDate);
                        contract.setTimeLine(timeLine);

                        contracts.add(contract);
                    }
                }

                // Retrieve project history
                projectStmt.setInt(1, employee.getId());
                try (ResultSet rs = projectStmt.executeQuery()) {
                    while (rs.next()) {
                        Project project = new Project();
                        project.setId(rs.getInt("ProjectID"));
                        project.setName(rs.getString("ProjectName"));
                        project.setCountry(Country.valueOf(rs.getString("Country")));
                        projects.add(project);
                    }
                }

                // Fetch the latest contract
                try (PreparedStatement latestContractStmt = conn.prepareStatement(contractSql)) {
                    latestContractStmt.setInt(1, employee.getId());
                    try (ResultSet rs = latestContractStmt.executeQuery()) {
                        if (rs.next()) {
                            Contract latestContract = new Contract();
                            latestContract.setId(rs.getInt("ContractID"));
                            latestContract.setAnnualSalary(rs.getDouble("AnnualSalary"));
                            LocalDateTime fromDate = rs.getTimestamp("FromDate").toLocalDateTime();
                            LocalDateTime toDate = rs.getTimestamp("ToDate").toLocalDateTime();
                            TimeLine timeLine = new TimeLine(fromDate, toDate);
                            latestContract.setTimeLine(timeLine);
                            contracts.add(latestContract);
                        }
                    }
                }

                // Fetch the latest project
                try (PreparedStatement latestProjectStmt = conn.prepareStatement(projectSql)) {
                    latestProjectStmt.setInt(1, employee.getId());
                    try (ResultSet rs = latestProjectStmt.executeQuery()) {
                        if (rs.next()) {
                            Project latestProject = new Project();
                            latestProject.setId(rs.getInt("ProjectID"));
                            latestProject.setName(rs.getString("ProjectName"));
                            latestProject.setCountry(Country.valueOf(rs.getString("Country")));
                            projects.add(latestProject);
                        }
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
            }

            History history = new History();
            history.setContracts(contracts);
            history.setProjects(projects);

            return history;
        }*/
}



