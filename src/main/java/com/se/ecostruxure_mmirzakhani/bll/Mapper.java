package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Project;
import com.se.ecostruxure_mmirzakhani.be.ProjectMember;
import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.*;

/**
 * This class provides mapping methods to relate employees and teams to their respective projects.
 */
public class Mapper {

    private static final EmployeeDAO dao = new EmployeeDAO();

    public static HashMap<Project, List<ProjectMember>> mapAllTheProjectsToMembers(){
        return dao.getAllProjectsWithTheirMembers();
    }

    public static List<ProjectMember> projectToMembersMapper(Project project){
        List<ProjectMember> projectMembers = new ArrayList<>();

        return dao.getProjectMembers(project);
    }

    /**
     * Maps teams to their project
     */
    public static Set<Team> projectToTeamsMapper(List<ProjectMember> projectMembers){
        // We use set instead of list, to prevent duplicates
        Set<Team> teams = new HashSet<>();

        for (ProjectMember projectMember: projectMembers){
            teams.add(projectMember.getTeam());
        }
        return teams;
    }


    /**
     * Maps the given employee to the working projects
     */
    public static List<Project> employeeToProjectMapper(Employee employee, ObservableMap<Project, ObservableList<ProjectMember>> projectToMembers) {
        List<Project> projects = new ArrayList<>();

        for (Project project: projectToMembers.keySet()){
            for (ProjectMember projectMember: projectToMembers.get(project)){
                System.out.println("1: " + employee);
                System.out.println("2: " + projectMember.getEmployee());
                if (projectMember.getEmployee().equals(employee)) {
                    projects.add(project);
                }
            }
        }
        System.out.println(projects);
        return projects;
    }



//
//    /**
//     * Maps an employee to their projects.
//     *
//     * @param employee The employee whose projects are to be mapped.
//     * @param projects The list of all projects.
//     * @return A list of projects that are assigned to the given employee.
//     */
//    public static List<Project> employeeProjectsMapper(Employee employee, List<Project> projects){
//        List<Project> projectList = new ArrayList<>();
//        for (Project project: projects){
//            if (project.getEmployee().equals(employee)){
//                projectList.add(project);
//            }
//        }
//        return projectList;
//    }
//
//    /**
//     * Maps a team to their projects.
//     *
//     * @param team The team whose projects are to be mapped.
//     * @param projects The list of all projects.
//     * @return A list of projects that are assigned to the given team.
//     */
//    public static List<Project> teamProjectsMapper(Team team, List<Project> projects){
//        List<Project> projectList = new ArrayList<>();
//
//        for (Project project: projects){
//            if (project.getTeam().equals(team)){
//                projectList.add(project);
//            }
//        }
//        return projectList;
//    }
//
//    /**
//     * Links all the teams to their respective projects and returns a list of items as hashmaps.
//     *
//     * @param teams The list of all teams.
//     * @param projects The list of all projects.
//     * @return A hashmaps where each item contains a team and their corresponding list of projects.
//     */
//    public static HashMap<Team, List<Project>> allTeamsToProjects(List<Team> teams, List<Project> projects){
//        HashMap<Team, List<Project>> hashMap = new HashMap<>();
//
//        for (Team team: teams){
//            List<Project> projectsList = new ArrayList<>();
//            for (Project project: projects){
//                if (project.getTeam().equals(team)){
//                    projectsList.add(project);
//                }
//            }
//            hashMap.put(team, projectsList);
//        }
//        return hashMap;
//    }
//
//    /**
//     * Links all the employees to their respective projects and returns a list of items as hashmaps.
//     *
//     * @param employees The list of all employees.
//     * @param projects The list of all projects.
//     * @return A list of items where each item contains an employee and their corresponding list of projects.
//     */
//    public static HashMap<Employee, List<Project>> allEmployeesToProjects(List<Employee> employees, List<Project> projects){
//        HashMap<Employee, List<Project>> hashMap = new HashMap<>();
//
//        for (Employee employee: employees){
//            List<Project> projectList = new ArrayList<>();
//
//            for (Project project: projects){
//                if (project.getEmployee().equals(employee)){
//                    projectList.add(project);
//                }
//            }
//            hashMap.put(employee, projectList);
//
//        }
//        return hashMap;
//    }
}
