package com.se.ecostruxure_mmirzakhani.dal.assignment;

import com.se.ecostruxure_mmirzakhani.be.entities.*;
import com.se.ecostruxure_mmirzakhani.be.enums.EmployeeType;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.dal.employee.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.dal.project.ProjectDAO;
import com.se.ecostruxure_mmirzakhani.dal.team.TeamDAO;
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
    public AssignmentDAO() throws ExceptionHandler {
        dbConnection = new DBConnection();
    }
    public boolean createAssignment(Assignment assignment) throws SQLException, ExceptionHandler {
        String sql = "INSERT INTO Assignment (EmployeeID, ProjectID, TeamID, UtilizationPercentage, EmployeeType, FromDate, ToDate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

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

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();

            // Check if the assignment was successfully created
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }
    }
    public List<Assignment> getAllAssignments() throws SQLException, ExceptionHandler {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM Assignment";
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(resultSet.getInt("AssignmentID"));

                // Initialize Employee, Project, and Team objects
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("EmployeeID"));
                assignment.setEmployee(employee);

                Project project = new Project();
                project.setId(resultSet.getInt("ProjectID"));
                assignment.setProject(project);

                Team team = new Team();
                team.setId(resultSet.getInt("TeamID"));
                assignment.setTeam(team);

                assignment.setUtilizationPercentage(resultSet.getFloat("UtilizationPercentage"));
                assignment.setEmployeeType(EmployeeType.valueOf(resultSet.getString("EmployeeType")));
                assignment.getTimeLine().setFrom(resultSet.getTimestamp("FromDate").toLocalDateTime());
                assignment.getTimeLine().setTo(resultSet.getTimestamp("ToDate").toLocalDateTime());

                assignments.add(assignment);
            }
        } catch (SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }
        return assignments;
    }
    public boolean updateAssignment(Assignment assignment) throws SQLException, ExceptionHandler {
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
    public boolean deleteAssignment(int assignmentId) throws SQLException, ExceptionHandler {
        String deleteAssignmentSql = "DELETE FROM Assignment WHERE AssignmentID = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement deleteAssignmentStmt = connection.prepareStatement(deleteAssignmentSql)) {

            // Delete from Assignment table
            deleteAssignmentStmt.setInt(1, assignmentId);
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
