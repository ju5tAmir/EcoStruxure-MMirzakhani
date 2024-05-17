package com.se.ecostruxure_mmirzakhani.dal;

import com.se.ecostruxure_mmirzakhani.be.EmployeeTeamGeography;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTeamGeographiesDAO {

    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public EmployeeTeamGeographiesDAO() throws ExceptionHandler {
        dbConnection = new DBConnection();
    }

    public void addEmployeeTeamGeography(EmployeeTeamGeography employeeTeamGeography) throws SQLException {
        String sql = "INSERT INTO EmployeeTeamGeographies (EmployeeTeamID, GeographyID) VALUES (?, ?)";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, employeeTeamGeography.getEmployeeTeamID());
                statement.setInt(2, employeeTeamGeography.getGeographyID());
                statement.executeUpdate();
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public List<EmployeeTeamGeography> getEmployeeTeamGeographies(int employeeTeamId) throws SQLException {
        List<EmployeeTeamGeography> employeeTeamGeographies = new ArrayList<>();
        String sql = "SELECT * FROM EmployeeTeamGeographies WHERE EmployeeTeamID = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, employeeTeamId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    EmployeeTeamGeography employeeTeamGeography = new EmployeeTeamGeography(
                            resultSet.getInt("EmployeeTeamGeographyID"),
                            resultSet.getInt("EmployeeTeamID"),
                            resultSet.getInt("GeographyID")
                    );
                    employeeTeamGeographies.add(employeeTeamGeography);
                }
            }
            return employeeTeamGeographies;
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }
}
