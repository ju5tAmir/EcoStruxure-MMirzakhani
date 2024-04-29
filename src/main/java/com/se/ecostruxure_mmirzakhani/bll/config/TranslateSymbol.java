package com.se.ecostruxure_mmirzakhani.bll.config;

import com.se.ecostruxure_mmirzakhani.be.Config;

/**
 * Represents hard-coded symbols which are used to translate human-readable strings into mathematical operations.
 * <p>
 * These symbols are utilized in {@link ConfigService#translate()} to retrieve the actual value of their attributes.
 * <p>
 * Note: This list does not contain all the symbols of the program.
 * <p>
 * User can create new {@link Config} instances with a {@link Config#key} representing a new symbol, which will then be saved in the database.
 */
public enum TranslateSymbol {
    // Salary
    AnnualSalary("AnnualSalary"),
    FixedAnnualAmount("FixedAnnualAmount"),

    // Working Hours
    AnnualWorkHours("AnnualWorkHours"),
    AverageDailyWorkHours("AverageDailyWorkHours"),

    // Rates
    DailyRate("DailyRate"),
    HourlyRate("HourlyRate");

    private final String value;


    TranslateSymbol(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
