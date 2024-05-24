package com.se.ecostruxure_mmirzakhani.bll.assignment;

import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.bll.IService;
import com.se.ecostruxure_mmirzakhani.dal.assignment.AssignmentDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

public class AssignmentService implements IService<Assignment> {

    private final AssignmentDAO dao;
    public AssignmentService() {
        this.dao = new AssignmentDAO();
    }

    @Override
    public void create(Assignment assignment) throws ExceptionHandler{

    }

    @Override
    public void remove(Assignment assignment) throws ExceptionHandler{
        dao.deleteAssignment(assignment);
    }

    @Override
    public void update(Assignment assignment) throws ExceptionHandler{

    }

    @Override
    public void delete(Assignment assignment) throws ExceptionHandler{

    }
}
