package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Employee;

import java.util.List;

public class Logic {
    private UserDAO userDAO;

    public Logic() {
        userDAO = new UserDAO();
    }

    public void createEmployee(Employee employee) {
        userDAO.createEmployee(employee);
    }

    public boolean deleteEmployee(Employee employee) {
        return userDAO.deleteEmployee(employee.getId());
    }

    public void editEmployee(Employee employee) {
        userDAO.editEmployee(employee);
    }

    public List<Employee> getEmployees() {
        return userDAO.getEmployeeList();
    }
}