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

    /**
     * Applies a gross margin multiplier to the original rate.
     *
     * @param originalRate The original rate (hourly or daily).
     * @param grossMarginPercentage The gross margin percentage (e.g., 0.2 for 20%).
     * @return The new rate after applying the gross margin multiplier.
     */
    public static double applyGrossMarginMultiplier(double originalRate, double grossMarginPercentage) {
        return originalRate / (1 - grossMarginPercentage);
    }

    /**
     * Applies a markup multiplier to the original rate.
     *
     * @param originalRate The original rate (hourly or daily).
     * @param markupPercentage The markup percentage (e.g., 0.2 for 20%).
     * @return The new rate after applying the markup multiplier.
     */
    public static double applyMarkupMultiplier(double originalRate, double markupPercentage) {
        return originalRate * (1 + markupPercentage);
    }
}
