package com.se.ecostruxure_mmirzakhani.bll.assignment;

import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.bll.IService;
import com.se.ecostruxure_mmirzakhani.dal.assignment.AssignmentDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

public class AssignmentService implements IService<Assignment> {

    private AssignmentDAO dao;
    public AssignmentService(){
        this.dao = new AssignmentDAO();
    }

    @Override
    public void create(Assignment object) {

    }

    @Override
    public void remove(Assignment object) throws ExceptionHandler{
        dao.removeAssignment();
    }

    @Override
    public void update(Assignment object) {

    }

    @Override
    public void delete(Assignment object) {

    }
}
