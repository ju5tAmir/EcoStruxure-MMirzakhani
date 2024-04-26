package com.se.ecostruxure_mmirzakhani.be;

import java.util.List;

public class Employee extends User {
    private double annualSalary;
    private double dailyWorkHours;
    private double annualWorkHours;
    private double hourlyRate;
    private double dailyRate;

    private List<Script> scripts;


    public Employee(){

    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public double getAnnualWorkHours() {
        return annualWorkHours;
    }

    public void setAnnualWorkHours(double annualWorkHours) {
        this.annualWorkHours = annualWorkHours;
    }

    public double getDailyWorkHours() {
        return dailyWorkHours;
    }

    public void setDailyWorkHours(double dailyWorkHours) {
        this.dailyWorkHours = dailyWorkHours;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public List<Script> getConfigs() {
        return scripts;
    }

    public void setConfigs(List<Script> scripts) {
        this.scripts = scripts;
    }
}
