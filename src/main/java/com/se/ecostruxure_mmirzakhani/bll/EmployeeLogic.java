package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;

import java.util.List;

public class EmployeeLogic {

    private EmployeeDAO employeeDAO;

    public EmployeeLogic(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void createEmployee(Employee employee) {
        employeeDAO.createEmployee(employee);
    }

    public void readEmployee(int id) {
         employeeDAO.readEmployee(id);
    }

    public List<Employee> readAllEmployees() {
        return employeeDAO.readAllEmployees();
    }

    public void updateEmployee(Employee employee) {
        employeeDAO.updateEmployee(employee);
    }

    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }
}