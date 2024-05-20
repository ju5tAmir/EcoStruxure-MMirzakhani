package com.se.ecostruxure_mmirzakhani.be;

import java.util.Objects;

public class ProjectMember {
    private int             id;
    private Employee        employee;
    private Team            team;
    private double          utilizationPercentage;
    private TimeLine        timeLine;


    public ProjectMember(){

    }

    public ProjectMember(Employee employee, Team team, double utilizationPercentage) {
        this.employee = employee;
        this.team = team;
        this.utilizationPercentage = utilizationPercentage;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public TimeLine getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(TimeLine timeLine) {
        this.timeLine = timeLine;
    }

    @Override
    public String toString() {
        return "ProjectMember{" +
                "id=" + id +
                ", employee=" + employee +
                ", team=" + team +
                ", utilizationPercentage=" + utilizationPercentage +
                ", timeLine=" + timeLine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectMember that)) return false;
        return getId() == that.getId() && Double.compare(getUtilizationPercentage(), that.getUtilizationPercentage()) == 0 && Objects.equals(getEmployee(), that.getEmployee()) && Objects.equals(getTeam(), that.getTeam()) && Objects.equals(getTimeLine(), that.getTimeLine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmployee(), getTeam(), getUtilizationPercentage(), getTimeLine());
    }
}
