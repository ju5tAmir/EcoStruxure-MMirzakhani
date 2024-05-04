package com.se.ecostruxure_mmirzakhani.dal;

import com.se.ecostruxure_mmirzakhani.be.Employee;
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
            } catch (SQLException e) {
                if (connection != null) {
                    connection.rollback();
                }
                throw new ExceptionHandler(e.getMessage());
            }
        }}


    public List<Team> getAllTeams() throws ExceptionHandler {
        List<Team> allTeams = new ArrayList<>();
        String query = "SELECT DISTINCT TeamName FROM TEAM";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)){

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Team team = new Team();

                team.setName(rs.getString("TeamName"));

                allTeams.add(team);
            }
            return allTeams;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
