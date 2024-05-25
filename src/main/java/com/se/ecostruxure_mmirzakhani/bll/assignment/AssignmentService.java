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
    public boolean create(Assignment assignment) throws ExceptionHandler{
        return false;
    }

    @Override
    public boolean remove(Assignment assignment) throws ExceptionHandler{
        return dao.deleteAssignment(assignment);
    }

    @Override
    public boolean update(Assignment assignment) throws ExceptionHandler{
        return false;
    }

    @Override
    public boolean delete(Assignment assignment) throws ExceptionHandler{
        return false;
    }
}
