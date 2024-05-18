package com.se.ecostruxure_mmirzakhani.be;

import java.util.Objects;

public class Project {
    private int             id;
    private Employee        employee;
    private Team            team;
    private double          utilizationPercentage;
    private TimeLine        timeLine;

    // ******************** Constructors **************************************
    public Project(){
        this.employee =                 new Employee();
        this.team =                     new Team();
    }

    public Project(Employee employee, Team team, double utilizationPercentage, TimeLine timeLine) {
        this.employee =                 employee;
        this.team =                     team;
        this.utilizationPercentage =    utilizationPercentage;
        this.timeLine =                 timeLine;
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

    public TimeLine getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(TimeLine timeLine) {
        this.timeLine = timeLine;
    }
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", employee=" + employee +
                ", team=" + team +
                ", utilizationPercentage=" + utilizationPercentage +
                ", timeLine" + timeLine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project project)) return false;
        return getId() == project.getId() && Double.compare(getUtilizationPercentage(), project.getUtilizationPercentage()) == 0 && Objects.equals(getEmployee(), project.getEmployee()) && Objects.equals(getTeam(), project.getTeam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmployee(), getTeam(), getUtilizationPercentage());
    }
}
