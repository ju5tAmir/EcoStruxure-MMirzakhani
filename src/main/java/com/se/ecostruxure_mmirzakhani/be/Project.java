package com.se.ecostruxure_mmirzakhani.be;

public class Project {
    private int             id;
    private Team            team;
    private double          utilizationPercentage;

    public Project(){

    }

    public Project(Team team, double utilizationPercentage) {
        this.team = team;
        this.utilizationPercentage = utilizationPercentage;
    }


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

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", team=" + team +
                ", utilizationPercentage=" + utilizationPercentage +
                '}';
    }
}
