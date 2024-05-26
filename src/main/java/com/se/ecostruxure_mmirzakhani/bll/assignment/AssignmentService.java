package com.se.ecostruxure_mmirzakhani.bll.assignment;

import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.bll.IService;
import com.se.ecostruxure_mmirzakhani.dal.assignment.AssignmentDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;

import java.util.List;

public class AssignmentService implements IService<Assignment> {

    private final AssignmentDAO dao;
    public AssignmentService() {
        this.dao = new AssignmentDAO();
    }

    @Override
    public boolean create(Assignment assignment) throws ExceptionHandler{
        return dao.createAssignment(assignment);
    }

    @Override
    public boolean read(Assignment assignment) throws ExceptionHandler{
        return false;
    }

    @Override
    public boolean update(Assignment assignment) throws ExceptionHandler{
        return false;
    }

    @Override
    public boolean delete(Assignment assignment) throws ExceptionHandler{
        return dao.deleteAssignment(assignment);
    }

    /**
     * Check if the overall utilization percentage is exceeding 100%
     */
    public void checkIllegalUtilization(double utilizationPercentage, List<Assignment> assignmentList) throws ExceptionHandler{
        double total = 0;

        for (Assignment assignment: assignmentList){
            total += assignment.getUtilizationPercentage();
        }

        if (total + utilizationPercentage > 100){
            throw new ExceptionHandler(ExceptionMessage.UTILIZATION_EXCEPTION.getValue());
        }
    }

    public List<Assignment> getAllAssignments() throws ExceptionHandler{
        return dao.getAllAssignments();
    }

}
