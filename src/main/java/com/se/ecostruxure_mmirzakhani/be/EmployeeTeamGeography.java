package com.se.ecostruxure_mmirzakhani.be;

public class EmployeeTeamGeography {
    private int employeeTeamGeographyID;
    private int employeeTeamID;
    private int geographyID;

    public EmployeeTeamGeography(int employeeTeamGeographyID, int employeeTeamID, int geographyID) {
        this.employeeTeamGeographyID = employeeTeamGeographyID;
        this.employeeTeamID = employeeTeamID;
        this.geographyID = geographyID;
    }

    // Getters and Setters
    public int getEmployeeTeamGeographyID() { return employeeTeamGeographyID; }
    public void setEmployeeTeamGeographyID(int employeeTeamGeographyID) { this.employeeTeamGeographyID = employeeTeamGeographyID; }

    public int getEmployeeTeamID() { return employeeTeamID; }
    public void setEmployeeTeamID(int employeeTeamID) { this.employeeTeamID = employeeTeamID; }

    public int getGeographyID() { return geographyID; }
    public void setGeographyID(int geographyID) { this.geographyID = geographyID; }
}
