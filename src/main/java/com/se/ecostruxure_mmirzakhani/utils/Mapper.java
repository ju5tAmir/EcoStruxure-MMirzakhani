package com.se.ecostruxure_mmirzakhani.utils;

import com.se.ecostruxure_mmirzakhani.be.entities.Employee;
import com.se.ecostruxure_mmirzakhani.be.entities.Project;
import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.be.entities.Team;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class provides methods to map Employees, Projects, Assignments and so on.
 */
public class Mapper {

    /**
     * Maps a project to it's assignments
     */
    public static List<Assignment> projectAssignmentMapper(Project project, List<Assignment> assignments){
        return assignments.stream()
                .filter(assignment -> assignment.getProject().equals(project))
                .collect(Collectors.toList());
    }

    /**
     * Maps an employee to their assignments
     */
    public static List<Assignment> employeeAssignmentMapper(Employee employee, List<Assignment> assignments){
        return assignments.stream()
                .filter(assignment -> assignment.getEmployee().equals(employee))
                .collect(Collectors.toList());
    }

    /**
     * Maps the teams of a given project
     */
    public static List<Team> projectTeamMapper(Project project, List<Assignment> assignments) {
        return assignments.stream()
                .filter(assignment -> assignment.getProject().equals(project))
                .map(Assignment::getTeam)
                .collect(Collectors.toList());
    }
}
