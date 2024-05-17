package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Project;
import com.se.ecostruxure_mmirzakhani.be.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class provides mapping methods to relate employees and teams to their respective projects.
 */
public class Mapper {

    /**
     * Maps an employee to their projects.
     *
     * @param employee The employee whose projects are to be mapped.
     * @param projects The list of all projects.
     * @return A list of projects that are assigned to the given employee.
     */
    public static List<Project> employeeProjectsMapper(Employee employee, List<Project> projects){
        List<Project> projectList = new ArrayList<>();
        for (Project project: projects){
            if (project.getEmployee().equals(employee)){
                projectList.add(project);
            }
        }
        return projectList;
    }

    /**
     * Maps a team to their projects.
     *
     * @param team The team whose projects are to be mapped.
     * @param projects The list of all projects.
     * @return A list of projects that are assigned to the given team.
     */
    public static List<Project> teamProjectsMapper(Team team, List<Project> projects){
        List<Project> projectList = new ArrayList<>();

        for (Project project: projects){
            if (project.getTeam().equals(team)){
                projectList.add(project);
            }
        }
        return projectList;
    }

    /**
     * Links all the teams to their respective projects and returns a list of hashmaps.
     *
     * @param teams The list of all teams.
     * @param projects The list of all projects.
     * @return A list of hashmaps where each hashmap contains a team and their corresponding list of projects.
     */
    public static List<HashMap<Team, List<Project>>> allTeamsToProjects(List<Team> teams, List<Project> projects){
        List<HashMap<Team, List<Project>>> list = new ArrayList<>();

        for (Team team: teams){
            HashMap<Team, List<Project>> hashMap = new HashMap<>();
            List<Project> projectsList = new ArrayList<>();
            for (Project project: projects){
                if (project.getTeam().equals(team)){
                    projectsList.add(project);
                }
            }
            hashMap.put(team, projectsList);
            list.add(hashMap);
        }
        return list;
    }

    /**
     * Links all the employees to their respective projects and returns a list of hashmaps.
     *
     * @param employees The list of all employees.
     * @param projects The list of all projects.
     * @return A list of hashmaps where each hashmap contains an employee and their corresponding list of projects.
     */
    public static List<HashMap<Employee, List<Project>>> allEmployeesToProjects(List<Employee> employees, List<Project> projects){
        List<HashMap<Employee, List<Project>>> list = new ArrayList<>();

        for (Employee employee: employees){
            HashMap<Employee, List<Project>> hashMap = new HashMap<>();
            List<Project> projectList = new ArrayList<>();
            for (Project project: projects){
                if (project.getEmployee().equals(employee)){
                    projectList.add(project);
                }
            }
            hashMap.put(employee, projectList);
            list.add(hashMap);
        }
        return list;
    }
}
