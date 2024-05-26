package com.se.ecostruxure_mmirzakhani.be.entities;

import com.se.ecostruxure_mmirzakhani.be.enums.Country;

import java.util.Objects;

public class Project {
    private int             id;
    private String          name;
    private Country country;

    // ******************** Constructors **************************************
    public Project(){

    }

    public Project(String name, Country country){
        this.name = name;
        this.country = country;
    }


    public Project(int id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }


    // ******************** Methods *******************************************


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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project project)) return false;
        return getId() == project.getId() && Objects.equals(getName(), project.getName()) && getCountry() == project.getCountry();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCountry());
    }
}
