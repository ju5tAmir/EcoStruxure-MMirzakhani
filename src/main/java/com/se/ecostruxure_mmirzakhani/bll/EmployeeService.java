package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;

import java.util.List;

public class EmployeeService {

    private EmployeeDAO dao = new EmployeeDAO();



    public List<Employee> getAllEmployees(){
        return dao.getAllEmployees();
    }
}
