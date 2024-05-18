package com.se.ecostruxure_mmirzakhani.be;

import java.time.LocalDateTime;

public class Contract {
    private int                 id;
    private Country             country;
    private Currency            currency;
    private double              annualSalary;
    private double              fixedAnnualAmount;
    private double              annualWorkHours;
    private double              averageDailyWorkHours;
    private double              overallUtilizationPercentage;
    private double              overheadPercentage;
    private boolean             isOverhead;
    private TimeLine            timeLine;



    // ******************** Constructors *********************************
    public Contract(){

    }

    // ******************** Methods **************************************
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public double getFixedAnnualAmount() {
        return fixedAnnualAmount;
    }

    public void setFixedAnnualAmount(double fixedAnnualAmount) {
        this.fixedAnnualAmount = fixedAnnualAmount;
    }

    public double getAnnualWorkHours() {
        return annualWorkHours;
    }

    public void setAnnualWorkHours(double annualWorkHours) {
        this.annualWorkHours = annualWorkHours;
    }

    public double getAverageDailyWorkHours() {
        return averageDailyWorkHours;
    }

    public void setAverageDailyWorkHours(double averageDailyWorkHours) {
        this.averageDailyWorkHours = averageDailyWorkHours;
    }

    public double getOverallUtilizationPercentage() {
        return overallUtilizationPercentage;
    }

    public void setOverallUtilizationPercentage(double overallUtilizationPercentage) {
        this.overallUtilizationPercentage = overallUtilizationPercentage;
    }

    public double getOverheadPercentage() {
        return overheadPercentage;
    }

    public void setOverheadPercentage(double overheadPercentage) {
        this.overheadPercentage = overheadPercentage;
    }

    public boolean isOverhead() {
        return isOverhead;
    }

    public void setOverhead(boolean overhead) {
        isOverhead = overhead;
    }


    public TimeLine getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(TimeLine timeLine) {
        this.timeLine = timeLine;
    }




    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", country=" + country +
                ", currency=" + currency +
                ", annualSalary=" + annualSalary +
                ", fixedAnnualAmount=" + fixedAnnualAmount +
                ", annualWorkHours=" + annualWorkHours +
                ", averageDailyWorkHours=" + averageDailyWorkHours +
                ", overallUtilizationPercentage=" + overallUtilizationPercentage +
                ", overheadPercentage=" + overheadPercentage +
                ", isOverhead=" + isOverhead +
                ", timeLine=" + timeLine +
                '}';
    }
}
