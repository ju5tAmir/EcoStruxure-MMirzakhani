package com.se.ecostruxure_mmirzakhani.dal.employee;

import com.se.ecostruxure_mmirzakhani.be.entities.*;
import com.se.ecostruxure_mmirzakhani.be.enums.Country;
import com.se.ecostruxure_mmirzakhani.be.enums.Currency;
import com.se.ecostruxure_mmirzakhani.be.enums.EmployeeType;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public EmployeeDAO() {
        dbConnection = new DBConnection();
    }


    //Todo: handle all exceptions
    public boolean createEmployee(Employee employee) throws ExceptionHandler {
        String insertEmployeeSql = "INSERT INTO Employees (FirstName, LastName, Email) VALUES (?, ?, ?)";
        String insertContractSql = "INSERT INTO Contract (EmployeeID, AnnualSalary, FixedAnnualAmount, AnnualWorkHours, AverageDailyWorkHours, OverheadPercentage, Currency) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement insertEmployeeStmt = conn.prepareStatement(insertEmployeeSql, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement insertContractStmt = conn.prepareStatement(insertContractSql, Statement.RETURN_GENERATED_KEYS)) {
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
                    insertContractStmt.setDouble(2, contract.getAnnualSalary());
                    insertContractStmt.setDouble(3, contract.getFixedAnnualAmount());
                    insertContractStmt.setDouble(4, contract.getAnnualWorkHours());
                    insertContractStmt.setDouble(5, contract.getAverageDailyWorkHours());
                    insertContractStmt.setDouble(6, contract.getOverheadPercentage());
                    insertContractStmt.setString(7, contract.getCurrency().name());
                    insertContractStmt.executeUpdate();

                    // Retrieve the generated EmployeeID
                    ResultSet rsC = insertEmployeeStmt.getGeneratedKeys();
                    if (rsC.next()) {
                        int contractID = rs.getInt(1);
                        contract.setId(contractID);
                    } else {
                        throw new SQLException("Creating contract failed, no ID obtained.");
                    }
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw new ExceptionHandler(ExceptionMessage.EMPLOYEE_INSERT_FAILED.getValue());
            }
        } catch (SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
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
                throw new ExceptionHandler(ExceptionMessage.EMPLOYEE_UPDATE_FAILED.getValue());
            }
        } catch (SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }
    }

    public List<Employee> getAllEmployees() throws ExceptionHandler {
        List<Employee> employees = new ArrayList<>();

        String sql = "SELECT e.EmployeeID, e.FirstName, e.LastName, e.Email, " +
                "c.ContractID, c.AnnualSalary, c.FixedAnnualAmount, c.AnnualWorkHours, " +
                "c.AverageDailyWorkHours, c.OverheadPercentage, c.Currency " +
                "FROM Employees e " +
                "LEFT JOIN Contract c ON e.EmployeeID = c.EmployeeID ";

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
        } catch (SQLException e) {
            throw new ExceptionHandler(e.getMessage(), e.getMessage());
        }
        return employees;
    }


    public boolean deleteEmployee(Employee employee) throws ExceptionHandler {
        String deleteAssignmentSql = "DELETE FROM Assignment WHERE EmployeeID = ?";
        String deleteContractSql = "DELETE FROM Contract WHERE EmployeeID = ?";
        String deleteEmployeeSql = "DELETE FROM Employees WHERE EmployeeID = ?";

        try (Connection conn = dbConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement deleteAssignmentStmt = conn.prepareStatement(deleteAssignmentSql);
                 PreparedStatement deleteContractStmt = conn.prepareStatement(deleteContractSql);
                 PreparedStatement deleteEmployeeStmt = conn.prepareStatement(deleteEmployeeSql)) {

                // Delete from Assignment
                deleteAssignmentStmt.setInt(1, employee.getId());
                deleteAssignmentStmt.executeUpdate();

                // Delete from Contract
                deleteContractStmt.setInt(1, employee.getId());
                deleteContractStmt.executeUpdate();

                // Delete from Employees
                deleteEmployeeStmt.setInt(1, employee.getId());
                deleteEmployeeStmt.executeUpdate();

                conn.commit();
                return true;}
            catch (SQLException e) {
                    conn.rollback();
                    throw new ExceptionHandler(ExceptionMessage.EMPLOYEE_DELETE_FAILED.getValue());
                }
            } catch (SQLException e) {
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

                    // Construct the timeline from FromDate to ToDate
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
                    project.setCountry(Country.TAIWAN.valueOf(rs.getString("Country")));
                    projects.add(project);
                }
            }

        } catch (SQLException e) {
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
                throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
            }

            History history = new History();
            history.setContracts(contracts);
            history.setProjects(projects);

            return history;
        }*/
}

