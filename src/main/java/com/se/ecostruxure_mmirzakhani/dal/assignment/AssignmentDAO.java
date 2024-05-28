package com.se.ecostruxure_mmirzakhani.dal.assignment;

import com.se.ecostruxure_mmirzakhani.be.entities.*;
import com.se.ecostruxure_mmirzakhani.be.enums.Country;
import com.se.ecostruxure_mmirzakhani.be.enums.Currency;
import com.se.ecostruxure_mmirzakhani.be.enums.EmployeeType;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO {
    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public AssignmentDAO() {
        dbConnection = new DBConnection();
    }
    public boolean createAssignment(Assignment assignment) throws ExceptionHandler {
        String sql = "INSERT INTO Assignment (EmployeeID, ProjectID, TeamID, UtilizationPercentage, EmployeeType) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters for the prepared statement
            statement.setInt(1, assignment.getEmployee().getId());
            statement.setInt(2, assignment.getProject().getId());
            statement.setInt(3, assignment.getTeam().getId());
            statement.setDouble(4, assignment.getUtilizationPercentage());
            statement.setString(5, assignment.getEmployeeType().name());

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();

            // Check if the assignment was successfully created
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Retrieve and update the ID of newly created assignment
                        int assignmentId = generatedKeys.getInt(1);
                        assignment.setId(assignmentId);
                        return true;
                    } else {
                        throw new SQLException("Creating assignment failed, no ID obtained.");
                    }
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new ExceptionHandler(e.getMessage());
        }
    }

    public List<Assignment> getAllAssignments() throws ExceptionHandler {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT a.AssignmentID, a.UtilizationPercentage, a.EmployeeType, " +
                "e.EmployeeID, e.FirstName, e.LastName, e.Email, " +
                "p.ProjectID, p.ProjectName, p.Country, " +
                "t.TeamID, t.TeamName, " +
                "a.FromDate, a.ToDate, " +
                "c.ContractID, c.AnnualSalary, c.FixedAnnualAmount, c.AnnualWorkHours, " +
                "c.AverageDailyWorkHours, c.OverheadPercentage, c.Currency " +
                "FROM Assignment a " +
                "JOIN Employees e ON a.EmployeeID = e.EmployeeID " +
                "JOIN Projects p ON a.ProjectID = p.ProjectID " +
                "JOIN Teams t ON a.TeamID = t.TeamID " +
                "JOIN Contract c ON e.EmployeeID = c.EmployeeID";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Create Assignment object and set its properties
                Assignment assignment = new Assignment();
                assignment.setId(resultSet.getInt("AssignmentID"));
                assignment.setUtilizationPercentage(resultSet.getFloat("UtilizationPercentage"));
                assignment.setEmployeeType(EmployeeType.valueOf(resultSet.getString("EmployeeType")));

                // Create and set Employee details
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("EmployeeID"));
                employee.setFirstName(resultSet.getString("FirstName"));
                employee.setLastName(resultSet.getString("LastName"));
                employee.setEmail(resultSet.getString("Email"));
                assignment.setEmployee(employee);

                // Create and set Contract details
                Contract contract = new Contract();
                contract.setId(resultSet.getInt("ContractID"));
                contract.setAnnualSalary(resultSet.getFloat("AnnualSalary"));
                contract.setFixedAnnualAmount(resultSet.getFloat("FixedAnnualAmount"));
                contract.setAnnualWorkHours(resultSet.getFloat("AnnualWorkHours"));
                contract.setAverageDailyWorkHours(resultSet.getFloat("AverageDailyWorkHours"));
                contract.setOverheadPercentage(resultSet.getFloat("OverheadPercentage"));
                contract.setCurrency(Currency.valueOf(resultSet.getString("Currency")));
                employee.setContract(contract);

                // Create and set Project details
                Project project = new Project();
                project.setId(resultSet.getInt("ProjectID"));
                project.setName(resultSet.getString("ProjectName"));
                project.setCountry(Country.fromString(resultSet.getString("Country")));
                assignment.setProject(project);

                // Create and set Team details
                Team team = new Team();
                team.setId(resultSet.getInt("TeamID"));
                team.setName(resultSet.getString("TeamName"));
                assignment.setTeam(team);

                // Set the TimeLine
                TimeLine timeLine = new TimeLine(
                        resultSet.getTimestamp("FromDate").toLocalDateTime(),
                        resultSet.getTimestamp("ToDate").toLocalDateTime()
                );
                assignment.setTimeLine(timeLine);

                // Add the assignment to the list
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), e.getMessage());
        }
        return assignments;
    }


    public boolean updateAssignment(Assignment assignment) throws ExceptionHandler {
        String sql = "UPDATE Assignment " +
                "SET EmployeeID = ?, " +
                "    ProjectID = ?, " +
                "    TeamID = ?, " +
                "    UtilizationPercentage = ?, " +
                "    EmployeeType = ?, " +
                "    FromDate = ?, " +
                "    ToDate = ? " +
                "WHERE AssignmentID = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            statement.setInt(1, assignment.getEmployee().getId());
            statement.setInt(2, assignment.getProject().getId());
            statement.setInt(3, assignment.getTeam().getId());
            statement.setDouble(4, assignment.getUtilizationPercentage());
            statement.setString(5, assignment.getEmployeeType().toString());
            statement.setTimestamp(6, Timestamp.valueOf(assignment.getTimeLine().getFrom()));
            statement.setTimestamp(7, Timestamp.valueOf(assignment.getTimeLine().getTo()));
            statement.setInt(8, assignment.getId());

            // Execute the SQL statement
            int rowsUpdated = statement.executeUpdate();

            // Check if the assignment was successfully updated
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }
    }
    public boolean deleteAssignment(Assignment assignment) throws ExceptionHandler {
        String deleteAssignmentSql = "DELETE FROM Assignment WHERE AssignmentID = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement deleteAssignmentStmt = connection.prepareStatement(deleteAssignmentSql)) {

            // Delete from Assignment table
            deleteAssignmentStmt.setInt(1, assignment.getId());
            int rowsDeleted = deleteAssignmentStmt.executeUpdate();

            // Check if deletion was successful
            if (rowsDeleted > 0) {
                return true;
            } else {
                return false; // No rows were deleted, assignment may not exist
            }

        } catch (SQLException | ExceptionHandler e) {
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }
    }
}
