package com.se.ecostruxure_mmirzakhani.be;
public class Employee {
    private int id;
    private String name;
    private Double annualSalary;
    private Double overheadMultiplier;
    private Double fixedAnnualAmount;
    private String country;
    private String team;
    private Double annualWorkingHours;
    private Double utilization;
    private Boolean employeeType;

    // Default constructor
    public Employee() {}

    public Employee(int employeeId, String name, Double annualSalary, Double overheadMultiplier, Double fixedAnnualAmount,
                    String country, String team, Double annualWorkingHours, Double utilization, Boolean employeeType) {
        this.id = employeeId;
        this.name = name;
        this.annualSalary = annualSalary;
        this.overheadMultiplier = overheadMultiplier;
        this.fixedAnnualAmount = fixedAnnualAmount;
        this.country = country;
        this.team = team;
        this.annualWorkingHours = annualWorkingHours;
        this.utilization = utilization;
        this.employeeType = employeeType;
    }
    public Employee(String name, Double annualSalary, Double overheadMultiplier, Double fixedAnnualAmount,
                    String country, String team, Double annualWorkingHours, Double utilization, Boolean employeeType) {
        this.name = name;
        this.annualSalary = annualSalary;
        this.overheadMultiplier = overheadMultiplier;
        this.fixedAnnualAmount = fixedAnnualAmount;
        this.country = country;
        this.team = team;
        this.annualWorkingHours = annualWorkingHours;
        this.utilization = utilization;
        this.employeeType = employeeType;
    }


    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(Double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public Double getOverheadMultiplier() {
        return overheadMultiplier;
    }

    public void setOverheadMultiplier(Double overheadMultiplier) {
        this.overheadMultiplier = overheadMultiplier;
    }

    public Double getFixedAnnualAmount() {
        return fixedAnnualAmount;
    }

    public void setFixedAnnualAmount(Double fixedAnnualAmount) {
        this.fixedAnnualAmount = fixedAnnualAmount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Double getAnnualWorkingHours() {
        return annualWorkingHours;
    }

    public void setAnnualWorkingHours(Double annualWorkingHours) {
        this.annualWorkingHours = annualWorkingHours;
    }

    public Double getUtilization() {
        return utilization;
    }

    public void setUtilization(Double utilization) {
        this.utilization = utilization;
    }

    public Boolean getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(Boolean employeeType) {
        this.employeeType = employeeType;
    }


}