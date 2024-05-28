package com.se.ecostruxure_mmirzakhani.dal.team;

import com.se.ecostruxure_mmirzakhani.be.entities.Team;
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
    public TeamDAO() {
        dbConnection = new DBConnection();
    }

    public boolean createTeam(Team team) throws ExceptionHandler {
        String sql = "INSERT INTO Teams (TeamName) VALUES (?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, team.getName());
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int teamId = rs.getInt(1);
                        team.setId(teamId);
                    }
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.TEAM_INSERT_FAILED.getValue(), e.getMessage());
        }
    }

    public boolean updateTeam(Team team) throws ExceptionHandler {
        String sql = "UPDATE Teams SET TeamName = ? WHERE TeamID = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, team.getName());
            stmt.setInt(2, team.getId());
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.TEAM_UPDATE_FAILED.getValue(), e.getMessage());
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
            throw new ExceptionHandler(ExceptionMessage.TEAM_RETRIEVAL_FAILED.getValue(), e.getMessage());
        }

        return teams;
    }

    public boolean deleteTeam(Team team) throws ExceptionHandler {
        String deleteAssignmentSql = "DELETE FROM Assignment WHERE TeamID = ?";
        String deleteTeamSql = "DELETE FROM Teams WHERE TeamID = ?";


        try (Connection conn = dbConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement deleteAssignmentStmt = conn.prepareStatement(deleteAssignmentSql);
                 PreparedStatement deleteTeamStmt = conn.prepareStatement(deleteTeamSql)) {

                // Delete from Assignment
                deleteAssignmentStmt.setInt(1, team.getId());
                deleteAssignmentStmt.executeUpdate();

                // Delete from Contract
                deleteTeamStmt.setInt(1, team.getId());
                deleteTeamStmt.executeUpdate();

                conn.commit();
                return true;
            }
            catch (SQLException e) {
                conn.rollback();
                throw new ExceptionHandler(ExceptionMessage.EMPLOYEE_DELETE_FAILED.getValue());
            }
        } catch (SQLException e) {
            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue());
        }
    }
}
