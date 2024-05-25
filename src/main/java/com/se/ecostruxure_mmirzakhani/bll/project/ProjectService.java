package com.se.ecostruxure_mmirzakhani.bll.project;

import com.se.ecostruxure_mmirzakhani.be.entities.Project;
import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.bll.IService;
import com.se.ecostruxure_mmirzakhani.dal.assignment.AssignmentDAO;
import com.se.ecostruxure_mmirzakhani.dal.project.ProjectDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.util.List;

public class ProjectService implements IService<Project> {
    private final ProjectDAO dao;

    public ProjectService(){
        this.dao = new ProjectDAO();
    }

    @Override
    public boolean create(Project project) throws ExceptionHandler {
        return dao.createProject(project);
    }

    @Override
    public boolean remove(Project project) throws ExceptionHandler {
        return false;

    }

    @Override
    public boolean update(Project project) throws ExceptionHandler {
        return false;

    }

    @Override
    public boolean delete(Project project) throws ExceptionHandler {
        return false;

    }
}
