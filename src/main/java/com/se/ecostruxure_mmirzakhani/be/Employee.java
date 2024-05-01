package com.se.ecostruxure_mmirzakhani.be;

public class Employee extends User {
    // Personal attributes
    private String firstName;
    private String lastName;

    // Geography and Team
    private Region region;
    private Country country;
    private Team team;
    private double hourlyRate;
    private double dailyRate;

    // Contract
    private Contract contract;


    /**
     * Constructor
     */
    public Employee(){}

    /**
     * Methods
     */

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

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double rate) {
        this.hourlyRate = rate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double rate) {
        this.dailyRate = rate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", region=" + region +
                ", country=" + country +
                ", team=" + team +
                ", contract=" + contract +
                '}';
    }
}
