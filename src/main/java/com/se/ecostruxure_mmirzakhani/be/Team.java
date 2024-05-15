package com.se.ecostruxure_mmirzakhani.be;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Team {
    private int id;
    private String name;
    private ObservableList<Employee> employees;


    public Team(String name) {
        this.name = name;
        this.employees = FXCollections.observableArrayList();
    }

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
        this.employees = FXCollections.observableArrayList();

    }

    public Team() {
        this.employees = FXCollections.observableArrayList();

    }

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
    public ObservableList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ObservableList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return name;
    }
}
