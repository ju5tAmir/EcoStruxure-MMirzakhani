package com.se.ecostruxure_mmirzakhani.be;

import java.util.List;

public class Employee extends User {
    private List<Profile> profiles;


    // ******************** Constructors **************************************
    public Employee(){

    }
    public Employee(int id) {
        super(id);
    }



    // ******************** Methods *******************************************
    public int getId() {
        return super.getId();
    }

    public void setId(int id) {
        super.setId(id);
    }

    public String getFirstName() {
        return super.getFirstName();
    }

    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    public String getLastName() {
        return super.getLastName();
    }

    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public String toString() {
        return "Employee{"  +
                "id=" + super.getId() +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", profiles=" + profiles +
                '}';
    }
}
