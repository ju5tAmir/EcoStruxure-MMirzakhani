package com.se.ecostruxure_mmirzakhani.dal.project;

import com.se.ecostruxure_mmirzakhani.be.entities.Project;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public ProjectDAO() throws ExceptionHandler {
        dbConnection = new DBConnection();
    }

    public void createProject(String projectName, String country) throws SQLException {
        String sql = "INSERT INTO Projects (ProjectName, Country) VALUES (?, ?)";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, projectName);
                statement.setString(2, country);
                statement.executeUpdate();
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public Project getProject(int projectId) throws SQLException {
        String sql = "SELECT * FROM Projects WHERE ProjectID = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, projectId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return new Project(resultSet.getInt("ProjectID"), resultSet.getString("ProjectName"), resultSet.getString("Country"));
                }
            }
            return null;
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM Projects";
        try (Connection connection = dbConnection.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    projects.add(new Project(resultSet.getInt("ProjectID"), resultSet.getString("ProjectName"), resultSet.getString("Country")));
                }
            }
            return projects;
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProject(int projectId, String projectName, String country) throws SQLException {
        String sql = "UPDATE Projects SET ProjectName = ?, Country = ? WHERE ProjectID = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, projectName);
                statement.setString(2, country);
                statement.setInt(3, projectId);
                statement.executeUpdate();
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProject(int projectId) throws SQLException {
        String sql = "DELETE FROM Projects WHERE ProjectID = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, projectId);
                statement.executeUpdate();
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }
}
