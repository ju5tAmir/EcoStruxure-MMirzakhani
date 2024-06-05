package com.se.ecostruxure_mmirzakhani.be.entities;

import com.se.ecostruxure_mmirzakhani.be.enums.MultiplierType;

public class Multiplier {
    private int id;
    private Project project;
    private MultiplierType multiplierType;
    private double value;

    public Multiplier(int id, Project project, MultiplierType multiplierType, double value) {
        this.id = id;
        this.project = project;
        this.multiplierType = multiplierType;
        this.value = value;
    }

    public Multiplier() {
        this.project = new Project(); // Prevent NullPointerException

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public MultiplierType getMultiplierType() {
        return multiplierType;
    }

    public void setMultiplierType(MultiplierType multiplierType) {
        this.multiplierType = multiplierType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Multiplier{" +
                "id=" + id +
                ", project=" + project +
                ", multiplierType=" + multiplierType +
                ", value=" + value +
                '}';
    }
}
