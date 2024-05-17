package com.se.ecostruxure_mmirzakhani.dal;

import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {

    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public TeamDAO() throws ExceptionHandler {
        dbConnection = new DBConnection();
    }

    public boolean addTeam(Team team) throws SQLException {
        String sql = "INSERT INTO Teams (TeamName) VALUES (?)";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, team.getTeamName());
                statement.executeUpdate();
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM Teams";
        try (Connection connection = dbConnection.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Team team = new Team(
                            resultSet.getInt("TeamID"),
                            resultSet.getString("TeamName")
                    );
                    teams.add(team);
                }
            }
            return teams;
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateTeam(Team team) throws SQLException {
        String sql = "UPDATE Teams SET TeamName = ? WHERE TeamID = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, team.getTeamName());
                statement.setInt(2, team.getTeamID());
                statement.executeUpdate();
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean deleteTeam(int teamID) throws SQLException {
        String sql = "DELETE FROM Teams WHERE TeamID = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, teamID);
                statement.executeUpdate();
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
