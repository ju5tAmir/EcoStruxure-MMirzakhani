package com.se.ecostruxure_mmirzakhani.be.entities;

import com.se.ecostruxure_mmirzakhani.be.enums.Currency;

public class Contract {
    private int                 id;
    private double              annualSalary;
    private double              fixedAnnualAmount;
    private double              annualWorkHours;
    private double              averageDailyWorkHours;
    private double              overheadPercentage;
    private Currency currency;
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

    public double getOverheadPercentage() {
        return overheadPercentage;
    }

    public void setOverheadPercentage(double overheadPercentage) {
        this.overheadPercentage = overheadPercentage;
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
                ", annualSalary=" + annualSalary +
                ", fixedAnnualAmount=" + fixedAnnualAmount +
                ", annualWorkHours=" + annualWorkHours +
                ", averageDailyWorkHours=" + averageDailyWorkHours +
                ", overheadPercentage=" + overheadPercentage +
                ", currency=" + currency +
                ", timeLine=" + timeLine +
                '}';
    }
}
