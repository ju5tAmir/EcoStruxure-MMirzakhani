package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Project;
import com.se.ecostruxure_mmirzakhani.be.Team;

import java.util.List;

public class TeamService {




    /**
     * Calculate and return the total cost for all employees in the provided list of projects.
     * @param projects A list of Project objects, each containing an associated employee.
     * @return The total cost for all employees across all projects.
     */
    public static double getTotalCost(List<Project> projects) {
        // Initialize the total cost to 0
        double totalCost = 0;

        // Iterate through each project in the provided list
        for (Project project : projects) {
            // Add the cost of the employee associated with the current project to the total cost
            totalCost += EmployeeService.getTotalCost(project.getEmployee());
        }

        // Return the calculated total cost
        return totalCost;
    }
}
