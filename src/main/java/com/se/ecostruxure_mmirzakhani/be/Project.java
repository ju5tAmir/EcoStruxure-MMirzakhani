package com.se.ecostruxure_mmirzakhani.be;

public class Project {
    private int             id;
    private Employee        employee;
    private Team            team;
    private double          utilizationPercentage;

    // ******************** Constructors **************************************
    public Project(Employee employee, Team team, double utilizationPercentage) {
        this.employee = employee;
        this.team = team;
        this.utilizationPercentage = utilizationPercentage;
    }

    // ******************** Methods *******************************************
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public double getUtilizationPercentage() {
        return utilizationPercentage;
    }

    public void setUtilizationPercentage(double utilizationPercentage) {
        this.utilizationPercentage = utilizationPercentage;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", employee=" + employee +
                ", team=" + team +
                ", utilizationPercentage=" + utilizationPercentage +
                '}';
    }
}
