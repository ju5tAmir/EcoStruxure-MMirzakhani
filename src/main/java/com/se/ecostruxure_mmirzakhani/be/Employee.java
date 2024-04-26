package com.se.ecostruxure_mmirzakhani.be;

import java.util.List;

public class Employee extends User {
    private double annualSalary;
    private double dailyWorkHours;
    private double yearlyWorkHours;
    private double hourlyRate;
    private double dailyRate;

    private List<Config> configs;


    public Employee(){

    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public double getYearlyWorkHours() {
        return yearlyWorkHours;
    }

    public void setYearlyWorkHours(double yearlyWorkHours) {
        this.yearlyWorkHours = yearlyWorkHours;
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

    public List<Config> getConfigs() {
        return configs;
    }

    public void setConfigs(List<Config> configs) {
        this.configs = configs;
    }
}
