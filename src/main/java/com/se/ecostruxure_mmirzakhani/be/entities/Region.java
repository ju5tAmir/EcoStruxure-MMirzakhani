package com.se.ecostruxure_mmirzakhani.be.entities;

import com.se.ecostruxure_mmirzakhani.be.enums.Country;

import java.util.Set;

public class Region {
    private int                 id;
    private String              name;
    private Set<Country>        countries;

    // ******************** Constructors *********************************
    public Region(){

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

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}

