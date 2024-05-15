package com.se.ecostruxure_mmirzakhani.be;

import java.time.LocalDateTime;

public class Profile {
    private int                 id;
    private Team                team;
    private Country             country;
    private Region              region;
    private double              annualSalary;
    private double              fixedAnnualAmount;
    private double              annualWorkHours;
    private double              averageDailyWorkHours;
    private boolean             isOverhead;
    private double              overheadPercentage;
    private double              utilizationPercentage;

    private LocalDateTime       validFrom;
    private LocalDateTime       validUntil;

    // ******************** Constructors *********************************
    public Profile(){

    }

    // ******************** Methods **************************************
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public double getFixedAnnualAmount() {
        return fixedAnnualAmount;
    }

    public void setFixedAnnualAmount(double fixedAnnualAmount) {
        this.fixedAnnualAmount = fixedAnnualAmount;
    }

    public double getAnnualWorkHours() {
        return annualWorkHours;
    }

    public void setAnnualWorkHours(double annualWorkHours) {
        this.annualWorkHours = annualWorkHours;
    }

    public double getAverageDailyWorkHours() {
        return averageDailyWorkHours;
    }

    public void setAverageDailyWorkHours(double averageDailyWorkHours) {
        this.averageDailyWorkHours = averageDailyWorkHours;
    }

    public boolean isOverhead() {
        return isOverhead;
    }

    public void setOverhead(boolean overhead) {
        isOverhead = overhead;
    }

    public double getOverheadPercentage() {
        return overheadPercentage;
    }

    public void setOverheadPercentage(double overheadPercentage) {
        this.overheadPercentage = overheadPercentage;
    }

    public double getUtilizationPercentage() {
        return utilizationPercentage;
    }

    public void setUtilizationPercentage(double utilizationPercentage) {
        this.utilizationPercentage = utilizationPercentage;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }


    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", team=" + team +
                ", country=" + country +
                ", region=" + region +
                ", annualSalary=" + annualSalary +
                ", fixedAnnualAmount=" + fixedAnnualAmount +
                ", annualWorkHours=" + annualWorkHours +
                ", averageDailyWorkHours=" + averageDailyWorkHours +
                ", isOverhead=" + isOverhead +
                ", overheadPercentage=" + overheadPercentage +
                ", utilizationPercentage=" + utilizationPercentage +
                ", validFrom=" + validFrom +
                ", validUntil=" + validUntil +
                '}';
    }
}
