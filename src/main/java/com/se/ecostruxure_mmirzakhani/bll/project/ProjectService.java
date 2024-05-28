package com.se.ecostruxure_mmirzakhani.bll.project;

import com.se.ecostruxure_mmirzakhani.be.entities.Project;
import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.be.enums.EmployeeType;
import com.se.ecostruxure_mmirzakhani.bll.IService;
import com.se.ecostruxure_mmirzakhani.dal.project.ProjectDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;


import java.util.List;
import java.util.stream.Collectors;

public class ProjectService implements IService<Project> {
    private final ProjectDAO dao;

    public ProjectService(){
        this.dao = new ProjectDAO();
    }

    /**
     * Returns list of the overhead assignments for a project
     */
    public static List<Assignment> projectOverheadAssignments(Project project, List<Assignment> assignments) {
        return assignments.stream()
                .filter(assignment -> assignment.getProject().equals(project))
                .filter(assignment -> assignment.getEmployeeType() == EmployeeType.OVERHEAD)
                .collect(Collectors.toList());
    }

    /**
     * Returns list of the production resource assignments for a project
     */
    public static List<Assignment> projectProductResourceAssignments(Project project, List<Assignment> assignments) {
        return assignments.stream()
                .filter(assignment -> assignment.getProject().equals(project))
                .filter(assignment -> assignment.getEmployeeType() == EmployeeType.PRODUCTION_RESOURCE)
                .collect(Collectors.toList());
    }

    @Override
    public boolean create(Project project) throws ExceptionHandler {
        return dao.createProject(project);
    }

    @Override
    public boolean read(Project project) throws ExceptionHandler {
        return false;
    }

    @Override
    public boolean update(Project project) throws ExceptionHandler {
        return dao.updateProject(project);

    }

    @Override
    public boolean delete(Project project) throws ExceptionHandler {
        return dao.deleteProject(project);

    }

    public List<Project> getAllProjects() throws ExceptionHandler{
        return dao.getAllProjects();
    }
}
