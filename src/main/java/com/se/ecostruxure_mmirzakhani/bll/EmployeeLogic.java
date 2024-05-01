package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.util.List;

public class EmployeeLogic {
    private final EmployeeDAO dao;

    {
        try {
            dao = new EmployeeDAO();
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor
     */
    public EmployeeLogic(){

    }

    /**
     * Retrieve all the employees from the database with their latest contract (without history)
     * @return List of employees
     * @throws ExceptionHandler If any error occurred.
     */
    public List<Employee> getAllEmployees() throws ExceptionHandler{
        return dao.getAllEmployees();
    }

    // ToDo: Implement methods to calculate rates

}
