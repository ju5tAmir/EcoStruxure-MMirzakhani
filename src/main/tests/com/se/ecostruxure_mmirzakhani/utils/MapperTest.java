package com.se.ecostruxure_mmirzakhani.utils;

import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.be.entities.Employee;
import com.se.ecostruxure_mmirzakhani.be.entities.Project;
import com.se.ecostruxure_mmirzakhani.be.entities.Team;
import com.se.ecostruxure_mmirzakhani.be.enums.Country;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.se.ecostruxure_mmirzakhani.utils.Mapper.employeeAssignmentMapper;
import static com.se.ecostruxure_mmirzakhani.utils.Mapper.projectAssignmentMapper;
import static org.junit.jupiter.api.Assertions.*;

class MapperTest {


    @Test
    @DisplayName("Test with assignments matching the given project")
    public void testProjectAssignmentMapper_withMatchingAssignments() {
        Project project1 = new Project(1, "Project1", Country.DENMARK);
        Project project2 = new Project(2, "Project2", Country.SWEDEN);

        Assignment assignment1 = new Assignment();
        assignment1.setProject(project1);
        assignment1.setEmployee(new Employee());
        assignment1.setTeam(new Team());

        Assignment assignment2 = new Assignment();
        assignment2.setProject(project1);
        assignment2.setEmployee(new Employee());
        assignment2.setTeam(new Team());

        Assignment assignment3 = new Assignment();
        assignment3.setProject(project2);
        assignment3.setEmployee(new Employee());
        assignment3.setTeam(new Team());

        List<Assignment> assignments = Arrays.asList(assignment1, assignment2, assignment3);

        List<Assignment> result = projectAssignmentMapper(project1, assignments);

        assertEquals(2, result.size());
        assertTrue(result.contains(assignment1));
        assertTrue(result.contains(assignment2));
    }

    @Test
    @DisplayName("Test with no assignments matching the given project")
    public void testProjectAssignmentMapper_withNoMatchingAssignments() {
        Project project1 = new Project(1, "Project1", Country.DENMARK);
        Project project2 = new Project(2, "Project2", Country.SWEDEN);

        Assignment assignment1 = new Assignment();
        assignment1.setProject(project1);
        assignment1.setEmployee(new Employee());
        assignment1.setTeam(new Team());

        Assignment assignment2 = new Assignment();
        assignment2.setProject(project1);
        assignment2.setEmployee(new Employee());
        assignment2.setTeam(new Team());


        List<Assignment> assignments = Arrays.asList(assignment1, assignment2);

        List<Assignment> result = projectAssignmentMapper(project2, assignments);

        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Test with an empty list of assignments")
    public void testProjectAssignmentMapper_withEmptyAssignmentsList() {
        Project project1 = new Project(1, "Project1", Country.DENMARK);
        List<Assignment> assignments = Arrays.asList();

        List<Assignment> result = projectAssignmentMapper(project1, assignments);

        assertEquals(0, result.size());
    }


    @Test
    @DisplayName("Test with assignments matching the given employee")
    public void testEmployeeAssignmentMapper_withMatchingAssignments() {
        Employee employee1 = new Employee();
        employee1.setId(1);
        Employee employee2 = new Employee();
        employee2.setId(2);

        Assignment assignment1 = new Assignment();
        assignment1.setProject(new Project(1, "Project1", Country.DENMARK));
        assignment1.setEmployee(employee1);
        assignment1.setTeam(new Team());

        Assignment assignment2 = new Assignment();
        assignment2.setProject(new Project(2, "Project2", Country.SWEDEN));
        assignment2.setEmployee(employee1);
        assignment2.setTeam(new Team());

        Assignment assignment3 = new Assignment();
        assignment3.setProject(new Project(3, "Project3", Country.NORWAY));
        assignment3.setEmployee(employee2);
        assignment3.setTeam(new Team());

        List<Assignment> assignments = Arrays.asList(assignment1, assignment2, assignment3);

        List<Assignment> result = employeeAssignmentMapper(employee1, assignments);

        assertEquals(2, result.size());
        assertTrue(result.contains(assignment1));
        assertTrue(result.contains(assignment2));
    }

    @Test
    @DisplayName("Test with no assignments matching the given employee")
    public void testEmployeeAssignmentMapper_withNoMatchingAssignments() {
        Employee employee1 = new Employee();
        employee1.setId(1);
        Employee employee2 = new Employee();
        employee2.setId(2);

        Assignment assignment1 = new Assignment();
        assignment1.setProject(new Project(1, "Project1", Country.DENMARK));
        assignment1.setEmployee(employee2);
        assignment1.setTeam(new Team());

        Assignment assignment2 = new Assignment();
        assignment2.setProject(new Project(2, "Project2", Country.SWEDEN));
        assignment2.setEmployee(employee2);
        assignment2.setTeam(new Team());

        List<Assignment> assignments = Arrays.asList(assignment1, assignment2);

        List<Assignment> result = employeeAssignmentMapper(employee1, assignments);

        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Test with an empty list of assignments")
    public void testEmployeeAssignmentMapper_withEmptyAssignmentsList() {
        Employee employee = new Employee();
        employee.setId(1);


        List<Assignment> assignments = Arrays.asList();

        List<Assignment> result = employeeAssignmentMapper(employee, assignments);

        assertEquals(0, result.size());
    }
}