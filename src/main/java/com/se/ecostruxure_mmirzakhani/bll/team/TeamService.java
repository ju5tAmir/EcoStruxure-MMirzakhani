package com.se.ecostruxure_mmirzakhani.bll.team;

import com.se.ecostruxure_mmirzakhani.be.enums.Currency;
import com.se.ecostruxure_mmirzakhani.be.entities.Team;
import com.se.ecostruxure_mmirzakhani.bll.employee.EmployeeService;
import com.se.ecostruxure_mmirzakhani.dal.employee.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.dal.team.TeamDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.util.List;

public class TeamService {
    private TeamDAO dao;

    private EmployeeService employeeService = new EmployeeService();
    public TeamService(){
        this.dao = new TeamDAO();

    }


    public List<Team> getAllTeams() throws ExceptionHandler {
        return dao.getAllTeams();
    }
}
