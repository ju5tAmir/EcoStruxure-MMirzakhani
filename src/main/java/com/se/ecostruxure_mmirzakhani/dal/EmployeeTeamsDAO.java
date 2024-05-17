package com.se.ecostruxure_mmirzakhani.dal;

import com.se.ecostruxure_mmirzakhani.be.EmployeeTeam;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTeamsDAO {

    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public EmployeeTeamsDAO() throws ExceptionHandler {
        dbConnection = new DBConnection();
    }

    public void addEmployeeTeam(EmployeeTeam employeeTeam) throws SQLException {
        String sql = "INSERT INTO EmployeeTeams (EmployeeID, TeamID, UtilizationPercentage) VALUES (?, ?, ?)";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, employeeTeam.getEmployeeID());
                statement.setInt(2, employeeTeam.getTeamID());
                statement.setBigDecimal(3, BigDecimal.valueOf(employeeTeam.getUtilizationPercentage()));
                statement.executeUpdate();
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public List<EmployeeTeam> getAllEmployeeTeams() throws ExceptionHandler, SQLException {
        List<EmployeeTeam> employeeTeams = new ArrayList<>();

        String sql = "SELECT * FROM EmployeeTeams";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int employeeID = resultSet.getInt("EmployeeID");
                int teamID = resultSet.getInt("TeamID");
                double utilizationPercentage = resultSet.getDouble("UtilizationPercentage");

                EmployeeTeam employeeTeam = new EmployeeTeam(employeeID, teamID, utilizationPercentage);
                employeeTeams.add(employeeTeam);
            }
        } catch (SQLException e) {
            throw new ExceptionHandler("Error retrieving employee teams");
        }

        return employeeTeams;
    }
}
