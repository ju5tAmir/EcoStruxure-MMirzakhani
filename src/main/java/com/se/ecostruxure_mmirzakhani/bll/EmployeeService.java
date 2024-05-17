package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Project;
import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;

import java.util.List;

public class EmployeeService {

    private static final EmployeeDAO dao = new EmployeeDAO();

    public List<Employee> getAllEmployees(){
        return dao.getAllEmployees();
    }

    public List<Team> getAllTeams() {
        return dao.getAllTeams();
    }

    public List<Project> getAllProjects() {
        return dao.getAllProjects();
    }

    /**
     * Calculate the total cost for the given employee
     */
    public static double getTotalCost(Employee employee){
        double annualSalary         = employee.getContract().getAnnualSalary();
        double annualFixedAmount    = employee.getContract().getFixedAnnualAmount();
        double overheadPercentage   = employee.getContract().getOverheadPercentage();
        double overheadAmount       = annualSalary * overheadPercentage / 100;

        return annualSalary + annualFixedAmount + overheadAmount;
    }
}
