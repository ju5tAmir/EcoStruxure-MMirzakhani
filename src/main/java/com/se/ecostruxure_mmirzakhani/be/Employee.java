package com.se.ecostruxure_mmirzakhani.be;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Employee extends User {
    // Personal attributes
    private String firstName;
    private String lastName;

    // Geography and Team
    private Region region;
    private Country country;
    private Team team;
    private Contract contract;
    private ObservableList<Team> teams;



    /**
     * Constructor
     */
    public Employee(){
        this.teams = FXCollections.observableArrayList();

    }

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

    public ObservableList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ObservableList<Team> teams) {
        this.teams = teams;
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
