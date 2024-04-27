package com.se.ecostruxure_mmirzakhani.be;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Contract {
    // Contract details
    private double annualSalary;
    private double fixedAnnualAmount;
    private double annualWorkHours;
    private double averageDailyWorkHours;
    private boolean isOverhead;
    // -- Multipliers
    private double overheadPercentage;
    private double utilizationPercentage;
    private double markupPercentage;
    private double grossMarginPercentage;

    // Rates
    private double hourlyRate;
    private double dailyRate;

    // Valid dates
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;

    /**
     * Constructor
     */
    public Contract(){

    }

    /**
     * Methods
     */

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

    public boolean isOverhead() {
        return isOverhead;
    }

    public void setOverhead(boolean overhead) {
        isOverhead = overhead;
    }

    public double getOverheadPercentage() {
        return overheadPercentage;
    }

    public void setOverheadPercentage(double overheadPercentage) {
        this.overheadPercentage = overheadPercentage;
    }

    public double getUtilizationPercentage() {
        return utilizationPercentage;
    }

    public void setUtilizationPercentage(double utilizationPercentage) {
        this.utilizationPercentage = utilizationPercentage;
    }

    public double getMarkupPercentage() {
        return markupPercentage;
    }

    public void setMarkupPercentage(double markupPercentage) {
        this.markupPercentage = markupPercentage;
    }

    public double getGrossMarginPercentage() {
        return grossMarginPercentage;
    }

    public void setGrossMarginPercentage(double grossMarginPercentage) {
        this.grossMarginPercentage = grossMarginPercentage;
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

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "annualSalary=" + annualSalary +
                ", fixedAnnualAmount=" + fixedAnnualAmount +
                ", annualWorkHours=" + annualWorkHours +
                ", averageDailyWorkHours=" + averageDailyWorkHours +
                ", isOverhead=" + isOverhead +
                ", overheadPercentage=" + overheadPercentage +
                ", utilizationPercentage=" + utilizationPercentage +
                ", markupPercentage=" + markupPercentage +
                ", grossMarginPercentage=" + grossMarginPercentage +
                ", hourlyRate=" + hourlyRate +
                ", dailyRate=" + dailyRate +
                ", validFrom=" + validFrom +
                ", validUntil=" + validUntil +
                '}';
    }
}
