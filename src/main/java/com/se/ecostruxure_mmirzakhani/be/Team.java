package com.se.ecostruxure_mmirzakhani.be;

import java.util.Objects;

public class Team {
    private int         id;
    private String      name;
    private double      hourlyRate;
    private double      dailyRate;
    private double      totalCost;


    // ******************** Constructors *********************************
    public Team(String name) {
        this.name = name;
    }

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team() {

    }


    // ******************** Methods **************************************
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hourlyRate=" + hourlyRate +
                ", dailyRate=" + dailyRate +
                ", totalCost=" + totalCost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team team)) return false;
        return getId() == team.getId() && Objects.equals(getName(), team.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
