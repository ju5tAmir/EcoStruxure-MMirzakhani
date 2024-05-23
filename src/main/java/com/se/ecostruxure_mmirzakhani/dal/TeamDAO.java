package com.se.ecostruxure_mmirzakhani.dal;

import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;

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

    public boolean createTeam(Team team) throws ExceptionHandler {
        String sql = "INSERT INTO Teams (TeamName) VALUES (?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, team.getName());
            int rowsAffected = stmt.executeUpdate();

            // Check if the insertion was successful
            if (rowsAffected > 0) {
                // Retrieve the generated TeamID
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int teamId = rs.getInt(1);
                    team.setId(teamId);
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }
    }

    public boolean updateTeam(Team team) throws ExceptionHandler {
        String sql = "UPDATE Teams SET TeamName = ? WHERE TeamID = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, team.getName());
            stmt.setInt(2, team.getId());
            int rowsAffected = stmt.executeUpdate();

            // Check if the update was successful
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }
    }

    public List<Team> getAllTeams() throws ExceptionHandler {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT TeamID, TeamName FROM Teams";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Team team = new Team();
                team.setId(rs.getInt("TeamID"));
                team.setName(rs.getString("TeamName"));
                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }

        return teams;
    }

    public boolean deleteTeam(int teamId) throws ExceptionHandler {
        String sql = "DELETE FROM Teams WHERE TeamID = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teamId);
            int rowsAffected = stmt.executeUpdate();

            // Check if the deletion was successful
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }
    }

}
