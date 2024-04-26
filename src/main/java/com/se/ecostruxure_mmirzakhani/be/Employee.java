package com.se.ecostruxure_mmirzakhani.be;

public class Employee {
    private int id;
    //Data to calculate the total annual salary
    private double annualSalary;
    private double overheadMultiplier;
    private double fixedAnnualAmount;
    //
    private String geography;
    private String team;
    //Data to calculate hours of productivity
    private double annualEffectiveHours;
    private double utilizationPercentage;
    //
    private boolean isOverhead;

    public Employee(int id, double annualSalary, double overheadMultiplier, double fixedAnnualAmount, String geography, String team, double annualEffectiveHours, double utilizationPercentage, boolean isOverhead) {
        this.id = id;
        this.annualSalary = annualSalary;
        this.overheadMultiplier = overheadMultiplier;
        this.fixedAnnualAmount = fixedAnnualAmount;
        this.geography = geography;
        this.team = team;
        this.annualEffectiveHours = annualEffectiveHours;
        this.utilizationPercentage = utilizationPercentage;
        this.isOverhead = isOverhead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public double getOverheadMultiplier() {
        return overheadMultiplier;
    }

    public void setOverheadMultiplier(double overheadMultiplier) {
        this.overheadMultiplier = overheadMultiplier;
    }

    public double getFixedAnnualAmount() {
        return fixedAnnualAmount;
    }

    public void setFixedAnnualAmount(double fixedAnnualAmount) {
        this.fixedAnnualAmount = fixedAnnualAmount;
    }

    public String getGeography() {
        return geography;
    }

    public void setGeography(String geography) {
        this.geography = geography;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public double getAnnualEffectiveHours() {
        return annualEffectiveHours;
    }

    public void setAnnualEffectiveHours(double annualEffectiveHours) {
        this.annualEffectiveHours = annualEffectiveHours;
    }

    public double getUtilizationPercentage() {
        return utilizationPercentage;
    }

    public void setUtilizationPercentage(double utilizationPercentage) {
        this.utilizationPercentage = utilizationPercentage;
    }

    public boolean isOverhead() {
        return isOverhead;
    }

    public void setOverhead(boolean overhead) {
        isOverhead = overhead;
    }
}
