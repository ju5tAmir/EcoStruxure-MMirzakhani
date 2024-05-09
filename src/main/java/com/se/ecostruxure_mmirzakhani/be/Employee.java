package com.se.ecostruxure_mmirzakhani.be;

import java.util.ArrayList;
import java.util.List;

public class Employee extends User {
    // Personal attributes
    private String firstName;
    private String lastName;

    // Geography and Team
    private Region region;
    private Country country;
    private Team team;

    // Contract
    private Contract contract;

    //Rate Record
    private List<RateRecord> rateHistory = new ArrayList<>();

    /**
     * Constructor
     */
    public Employee(){}

    /**
     * Methods
     */

    //Rate Record Methods
    public void addRateRecord(RateRecord rateRecord) {
        rateHistory.add(rateRecord);
    }

    public List<RateRecord> getRateHistory() {
        return rateHistory;
    }

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
