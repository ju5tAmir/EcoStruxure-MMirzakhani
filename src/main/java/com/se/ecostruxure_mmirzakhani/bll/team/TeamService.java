package com.se.ecostruxure_mmirzakhani.bll.team;


import com.se.ecostruxure_mmirzakhani.be.entities.Team;
import com.se.ecostruxure_mmirzakhani.bll.IService;
import com.se.ecostruxure_mmirzakhani.bll.employee.EmployeeService;
import com.se.ecostruxure_mmirzakhani.dal.team.TeamDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.util.List;

public class TeamService implements IService<Team> {
    private TeamDAO dao;
    public TeamService(){
        this.dao = new TeamDAO();

    }

    @Override
    public boolean create(Team team) throws ExceptionHandler {
        return dao.createTeam(team);
    }


    @Override
    public boolean update(Team team) throws ExceptionHandler {
        return dao.updateTeam(team);
    }

    @Override
    public boolean delete(Team team) throws ExceptionHandler {
        return dao.deleteTeam(team);
    }

    public List<Team> getAllTeams() throws ExceptionHandler {
        return dao.getAllTeams();
    }

}
