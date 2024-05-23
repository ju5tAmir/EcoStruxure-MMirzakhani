package com.se.ecostruxure_mmirzakhani.be;

import java.util.ArrayList;
import java.util.List;

public class History {
    private List<Contract>      contracts;
    private List<Project>       projects;


    // ******************** Constructors *********************************
    public History(){
        this.contracts = new ArrayList<>();
        this.projects  = new ArrayList<>();
    }


    // ******************** Methods **************************************
    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "History{" +
                "contracts=" + contracts +
                ", projects=" + projects +
                '}';
    }
}
