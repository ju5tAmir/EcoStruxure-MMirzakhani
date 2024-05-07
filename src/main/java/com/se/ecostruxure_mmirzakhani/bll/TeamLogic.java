package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.dal.TeamDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class TeamLogic {
    private final TeamDAO teamDAO;

    {
        try {
            teamDAO = new TeamDAO();
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public List<Team> getAllTeams() throws ExceptionHandler {
        return teamDAO.getAllTeams();
    }
    public boolean createTeam(Team team) throws ExceptionHandler, SQLException {
        return teamDAO.createTeam(team);
    }

    public boolean updateTeam(Team team) throws ExceptionHandler, SQLException {
        return teamDAO.updateTeam(team);
    }

    public boolean deleteTeam(int id) throws ExceptionHandler, SQLException {
        return teamDAO.deleteTeam(id);
    }

}
