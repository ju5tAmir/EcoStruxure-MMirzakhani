package com.se.ecostruxure_mmirzakhani.bll.rate;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Project;
import com.se.ecostruxure_mmirzakhani.be.ProjectMember;
import com.se.ecostruxure_mmirzakhani.bll.EmployeeService;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;

import java.util.List;

public class RateService implements IRateService{
    private final Project project;
    private final List<ProjectMember> projectMembers;

    public RateService(Project project, List<ProjectMember> projectMembers){
        this.project = project;
        this.projectMembers = projectMembers;
    }


    @Override
    public double getHourlyRate() {
        // Initialize the total rate to accumulate the hourly rates of all employees
        double totalRate = 0;

        // if project doesn't have any member, return 0
        if (projectMembers == null){
            return 0;
        }

        // Iterate through each project member to calculate the hourly rate for the employee assigned to the project
        for (ProjectMember projectMember : projectMembers) {
            Employee    employee                        = projectMember.getEmployee();
            double      employeeTotalCost               = EmployeeService.getTotalCost(employee);
            double      employeeAnnualWorkHours         = employee.getContract().getAnnualWorkHours();
            double      employeeUtilizationPercentage   = projectMember.getUtilizationPercentage(); // Utilization percentage for this project of the employee
            double      employeeEffectiveWorkHours      = employeeAnnualWorkHours * employeeUtilizationPercentage / 100;

            double      employeeRate                    = employeeTotalCost / employeeEffectiveWorkHours;

            totalRate += employeeRate;
        }

        // Return the calculated total cost
        return CurrencyService.doubleFormatter(totalRate);
    }

    @Override
    public double getDailyRate() {
        // Initialize the total rate to accumulate the daily rates of all employees
        double totalRate = 0;

        // Return zero if project doesn't have any member
        if (projectMembers == null) {
            return 0;
        }

        // Iterate through each project to calculate the daily rate for the employee assigned to the project
        for (ProjectMember projectMember : projectMembers) {
            Employee    employee                        = projectMember.getEmployee();
            double      employeeTotalCost               = EmployeeService.getTotalCost(employee);
            double      employeeAnnualWorkHours         = employee.getContract().getAnnualWorkHours();
            double      employeeAverageDailyWorkHours   = employee.getContract().getAverageDailyWorkHours();
            double      employeeUtilizationPercentage   = projectMember.getUtilizationPercentage(); // Utilization percentage for this project of the employee
            double      employeeEffectiveWorkHours      = employeeAnnualWorkHours * employeeUtilizationPercentage / 100;


            double employeeRate = employeeTotalCost / employeeEffectiveWorkHours * employeeAverageDailyWorkHours;

            totalRate += employeeRate;

        }
        return CurrencyService.doubleFormatter(totalRate);
    }

    public Project getProject(){
        return project;
    }







    @Override
    public String toString() {
        return "RateService{" +
                "project=" + project +
                ", projectMembers=" + projectMembers +
                '}';
    }
}
