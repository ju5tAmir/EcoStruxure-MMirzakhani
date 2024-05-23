package com.se.ecostruxure_mmirzakhani.dal.team;

import com.se.ecostruxure_mmirzakhani.be.entities.Team;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeamDAO {

    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public TeamDAO() throws ExceptionHandler {
        dbConnection = new DBConnection();
    }

    public boolean createTeam(Team team) throws ExceptionHandler, SQLException {
        String createTeam = "INSERT INTO TEAM(TeamName) VALUES(?)";
        try (Connection connection = dbConnection.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(createTeam, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, team.getName());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating team failed, no rows affected.");
                }

                // Retrieve the generated id for the team
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        team.setId(rs.getInt(1));
                    } else {
                        throw new SQLException("Creating team failed, no ID obtained.");
                    }
                }
                connection.commit();
                return true;
            } catch (SQLException sqlException) {
                if (connection != null) {
                    connection.rollback();
                }
                throw new ExceptionHandler("Unable to create a new Team" + sqlException.getMessage());
            }
        }
    }
    public boolean updateTeam(Team team) throws ExceptionHandler {
        String updateTeamQuery = "UPDATE Team SET TeamName = ? WHERE TeamID = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(updateTeamQuery)) {
            statement.setString(1, team.getName());
            statement.setInt(2, team.getId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Return true if rows were affected, false otherwise
        } catch (SQLException sqlException) {
            throw new ExceptionHandler("Update Failed " + sqlException.getMessage());
        }
    }

    public List<Team> getAllTeams() throws ExceptionHandler {
        List<Team> allTeams = new ArrayList<>();
        String query = "SELECT TeamName, TeamID FROM TEAM";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)){

            ResultSet rs = preparedStatement.executeQuery();
            Set<String> teamNames = new HashSet<>(); // Store unique team names
            while (rs.next()){
                String teamName = rs.getString("TeamName");
                if (!teamNames.contains(teamName)) { // Check if team name is unique
                    Team team = new Team();
                    team.setId(rs.getInt("TeamID"));
                    team.setName(teamName);
                    allTeams.add(team);
                    teamNames.add(teamName); // Add team name to set to track uniqueness
                }
            }
            return allTeams;

        } catch (SQLException sqlException) {
            throw new ExceptionHandler("Failed to retrieve list of Teams from the database " + sqlException.getMessage());
        }
    }
    public boolean deleteTeam(int id) throws ExceptionHandler, SQLException {
        String deleteTeam = "DELETE Team WHERE TeamID = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteTeam)){
             preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException sqlException){
            throw new ExceptionHandler("Failed to delete team: " + sqlException.getMessage());
        }
    }
}
