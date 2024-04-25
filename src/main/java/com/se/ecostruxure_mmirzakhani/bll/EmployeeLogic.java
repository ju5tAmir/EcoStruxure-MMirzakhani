package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.dal.UserDAO;

import java.util.List;

public class EmployeeLogic {
    private UserDAO UserDAO;

    public EmployeeLogic() {
        UserDAO = new UserDAO();
    }

    public void createEmployee(Employee employee) {
        UserDAO.createEmployee(employee);
    }

    public boolean deleteEmployee(Employee employee) {
        return UserDAO.deleteEmployee(employee.getId());
    }

    public void editEmployee(Employee employee) {
        UserDAO.editEmployee(employee);
    }

    public List<Employee> getEmployees() {
        return UserDAO.getEmployeeList();
    }
}