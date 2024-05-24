package com.se.ecostruxure_mmirzakhani.dal.project;

import com.se.ecostruxure_mmirzakhani.be.entities.Employee;
import com.se.ecostruxure_mmirzakhani.be.entities.Project;
import com.se.ecostruxure_mmirzakhani.be.enums.Country;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;

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
    public boolean createProject(Project project) throws ExceptionHandler {
        String sql = "INSERT INTO Projects (ProjectName, Country) VALUES (?, ?)";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, project.getName());
            statement.setString(2, project.getCountry().toString());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        project.setId(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.PROJECT_INSERT_FAILED.getValue(), e.getMessage());
        }
    }

    public List<Project> getAllProjects() throws ExceptionHandler {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM Projects";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("ProjectID"));
                project.setName(resultSet.getString("ProjectName"));
                project.setCountry(Country.valueOf(resultSet.getString("Country")));
                projects.add(project);
            }
        } catch (SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.PROJECT_RETRIEVAL_FAILED.getValue(), e.getMessage());
        }
        return projects;
    }

    public List<Project> getAllProjects(Employee employee) throws ExceptionHandler {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT p.ProjectID, p.ProjectName, p.Country " +
                "FROM Projects p " +
                "JOIN Assignment a ON p.ProjectID = a.ProjectID " +
                "WHERE a.EmployeeID = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employee.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Project project = new Project();
                    project.setId(resultSet.getInt("ProjectID"));
                    project.setName(resultSet.getString("ProjectName"));
                    project.setCountry(Country.valueOf(resultSet.getString("Country")));
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.PROJECT_RETRIEVAL_FAILED.getValue(), e.getMessage());
        }
        return projects;
    }

    public boolean updateProject(Project project) throws ExceptionHandler {
        String sql = "UPDATE Projects SET ProjectName = ?, Country = ? WHERE ProjectID = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, project.getName());
            statement.setString(2, project.getCountry().toString());
            statement.setInt(3, project.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.PROJECT_UPDATE_FAILED.getValue(), e.getMessage());
        }
    }

    public boolean deleteProject(int projectId) throws ExceptionHandler {
        String sql = "DELETE FROM Projects WHERE ProjectID = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, projectId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.PROJECT_DELETE_FAILED.getValue(), e.getMessage());
        }
    }
}
