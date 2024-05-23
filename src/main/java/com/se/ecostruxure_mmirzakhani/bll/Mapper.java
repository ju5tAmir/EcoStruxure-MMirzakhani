package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Project;
import com.se.ecostruxure_mmirzakhani.be.Assignment;
import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

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

}
