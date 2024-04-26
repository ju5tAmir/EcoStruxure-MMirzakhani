package com.se.ecostruxure_mmirzakhani.be;

public class Employee extends User {
    // Personal attributes
    private String firstName;
    private String lastName;

    // Contract details
    private double annualSalary;
    private double fixedAnnualAmount;
    private double annualWorkHours;
    private double averageDailyWorkHours;
    private boolean isOverhead;
    // -- Multipliers
    private double overheadPercentage;
    private double utilizationPercentage;
    private double markupPercentage;
    private double grossMarginPercentage;

    // Geography and Team
    private Region region;
    private Country country;
    private Team team;

    // Rates
    private double hourlyRate;
    private double dailyRate;


    /**
     * Constructor
     */
    public Employee(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public double getMarkupPercentage() {
        return markupPercentage;
    }

    public void setMarkupPercentage(double markupPercentage) {
        this.markupPercentage = markupPercentage;
    }

    public double getGrossMarginPercentage() {
        return grossMarginPercentage;
    }

    public void setGrossMarginPercentage(double grossMarginPercentage) {
        this.grossMarginPercentage = grossMarginPercentage;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", annualSalary=" + annualSalary +
                ", fixedAnnualAmount=" + fixedAnnualAmount +
                ", annualWorkHours=" + annualWorkHours +
                ", averageDailyWorkHours=" + averageDailyWorkHours +
                ", isOverhead=" + isOverhead +
                ", overheadPercentage=" + overheadPercentage +
                ", utilizationPercentage=" + utilizationPercentage +
                ", markupPercentage=" + markupPercentage +
                ", grossMarginPercentage=" + grossMarginPercentage +
                ", region=" + region +
                ", country=" + country +
                ", team=" + team +
                ", hourlyRate=" + hourlyRate +
                ", dailyRate=" + dailyRate +
                '}';
    }
}
