package com.se.ecostruxure_mmirzakhani.dal.assignment;

import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

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

    public void createAssignment(int employeeId, int projectId, int teamId, float utilizationPercentage, String employeeType) throws SQLException {
        String sql = "INSERT INTO Assignment (EmployeeID, ProjectID, TeamID, UtilizationPercentage, EmployeeType) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, employeeId);
                statement.setInt(2, projectId);
                statement.setInt(3, teamId);
                statement.setFloat(4, utilizationPercentage);
                statement.setString(5, employeeType);
                statement.executeUpdate();
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public Assignment getAssignment(int assignmentId) throws SQLException {
        String sql = "SELECT * FROM Assignment WHERE AssignmentID = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, assignmentId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return new Assignment(
                            resultSet.getInt("AssignmentID"),
                            resultSet.getInt("EmployeeID"),
                            resultSet.getInt("ProjectID"),
                            resultSet.getInt("TeamID"),
                            resultSet.getFloat("UtilizationPercentage"),
                            resultSet.getString("EmployeeType"),
                            resultSet.getTimestamp("FromDate"),
                            resultSet.getTimestamp("ToDate")
                    );
                }
            }
            return null;
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public List<Assignment> getAllAssignments() throws SQLException {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM Assignment";
        try (Connection connection = dbConnection.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    assignments.add(new Assignment(
                            resultSet.getInt("AssignmentID"),
                            resultSet.getInt("EmployeeID"),
                            resultSet.getInt("ProjectID"),
                            resultSet.getInt("TeamID"),
                            resultSet.getFloat("UtilizationPercentage"),
                            resultSet.getString("EmployeeType"),
                            resultSet.getTimestamp("FromDate"),
                            resultSet.getTimestamp("ToDate")
                    ));
                }
            }
            return assignments;
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAssignment(int assignmentId, int employeeId, int projectId, int teamId, float utilizationPercentage, String employeeType) throws SQLException {
        String sql = "UPDATE Assignment SET EmployeeID = ?, ProjectID = ?, TeamID = ?, UtilizationPercentage = ?, EmployeeType = ? WHERE AssignmentID = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, employeeId);
                statement.setInt(2, projectId);
                statement.setInt(3, teamId);
                statement.setFloat(4, utilizationPercentage);
                statement.setString(5, employeeType);
                statement.setInt(6, assignmentId);
                statement.executeUpdate();
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAssignment(int assignmentId) throws SQLException {
        String sql = "DELETE FROM Assignment WHERE AssignmentID = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, assignmentId);
                statement.executeUpdate();
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }
}
