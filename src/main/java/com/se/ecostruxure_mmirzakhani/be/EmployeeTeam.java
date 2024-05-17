package com.se.ecostruxure_mmirzakhani.be;

public class EmployeeTeam {
    private int employeeID;
    private int teamID;
    private double utilizationPercentage;

    public EmployeeTeam(int employeeID, int teamID, double utilizationPercentage) {
        this.employeeID = employeeID;
        this.teamID = teamID;
        this.utilizationPercentage = utilizationPercentage;
    }

    // Getters and Setters

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public double getUtilizationPercentage() {
        return utilizationPercentage;
    }

    public void setUtilizationPercentage(double utilizationPercentage) {
        this.utilizationPercentage = utilizationPercentage;
    }
}
