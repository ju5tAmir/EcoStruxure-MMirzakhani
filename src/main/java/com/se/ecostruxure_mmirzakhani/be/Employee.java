package com.se.ecostruxure_mmirzakhani.be;


import java.util.ArrayList;
import java.util.List;

public class Employee extends User {
    private Contract contract;
    private List<Project> projects = new ArrayList<>();


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


    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project){
        this.projects.add(project);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "contract=" + contract +
                ", projects=" + projects +
                '}';
    }
}
