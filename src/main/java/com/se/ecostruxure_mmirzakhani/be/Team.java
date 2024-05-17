package com.se.ecostruxure_mmirzakhani.be;

public class Team {
    private int teamID;
    private String teamName;

    // Constructors
    public Team() {}

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(int teamID, String teamName) {
        this.teamID = teamID;
        this.teamName = teamName;
    }

    // Getters and Setters
    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return teamName;
    }
}
