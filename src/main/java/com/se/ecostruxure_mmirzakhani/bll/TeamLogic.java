package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.dal.TeamDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class TeamLogic {

    private final TeamDAO teamDAO;
    private EmployeeDAO dao;
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

    public double teamHourlyRate(int teamId) throws ExceptionHandler, SQLException {

        ObservableList<Employee> employees = dao.getEmployeesByTeam(teamId);
        double totalHourlyRate = employees.stream()
                .mapToDouble(employee -> employee.getContract().getHourlyRate())
                .sum();

        return totalHourlyRate / employees.size();
    }

    public double teamDailyRate(int teamId) throws ExceptionHandler, SQLException {

        ObservableList<Employee> employees = dao.getEmployeesByTeam(teamId);
        double totalDailyRate = employees.stream()
                .mapToDouble(employee -> employee.getContract().getDailyRate())
                .sum();

        return totalDailyRate / employees.size();
    }

    public double teamMarkupHourlyRate(int teamId, double markupPercentage) throws ExceptionHandler, SQLException {
        double averageHourlyRate = teamHourlyRate(teamId);
        return applyMarkup(averageHourlyRate, markupPercentage);
    }

    public double teamMarkupDailyRate(int teamId, double markupPercentage) throws ExceptionHandler, SQLException {
        double averageDailyRate = teamDailyRate(teamId);
        return applyMarkup(averageDailyRate, markupPercentage);
    }

    public double teamGMHourlyRate(int teamId, double gmPercentage) throws ExceptionHandler, SQLException {
        double averageHourlyRate = teamHourlyRate(teamId);
        return applyGM(averageHourlyRate, gmPercentage);
    }

    public double teamGMDailyRate(int teamId, double gmPercentage) throws ExceptionHandler, SQLException {
        double averageDailyRate = teamDailyRate(teamId);
        return applyGM(averageDailyRate, gmPercentage);
    }

    private double applyMarkup(double rate, double markupPercentage) {
        return rate * (1 + markupPercentage / 100);
    }

    private double applyGM(double rate, double gmPercentage) {
        return rate / (1 - gmPercentage / 100);
    }

}
