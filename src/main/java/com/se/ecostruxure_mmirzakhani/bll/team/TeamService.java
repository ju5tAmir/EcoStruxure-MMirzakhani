package com.se.ecostruxure_mmirzakhani.bll.team;

import com.se.ecostruxure_mmirzakhani.be.enums.Currency;
import com.se.ecostruxure_mmirzakhani.be.entities.Team;
import com.se.ecostruxure_mmirzakhani.bll.IService;
import com.se.ecostruxure_mmirzakhani.bll.employee.EmployeeService;
import com.se.ecostruxure_mmirzakhani.dal.employee.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.dal.team.TeamDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.util.List;

public class TeamService implements IService<Team> {
    private TeamDAO dao;

    private EmployeeService employeeService = new EmployeeService();
    public TeamService(){
        this.dao = new TeamDAO();

    }


    public List<Team> getAllTeams() throws ExceptionHandler {
        return dao.getAllTeams();
    }

    @Override
    public boolean create(Team team) throws ExceptionHandler {
        return dao.createTeam(team);
    }

    @Override
    public boolean read(Team team) throws ExceptionHandler {
        return false;
    }

    @Override
    public boolean update(Team team) throws ExceptionHandler {
        return dao.updateTeam(team);
    }

    @Override
    public boolean delete(Team team) throws ExceptionHandler {
        return dao.deleteTeam(team);
    }
}
