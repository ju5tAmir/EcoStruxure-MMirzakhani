package com.se.ecostruxure_mmirzakhani.exceptions;

public enum ExceptionMessage {
    // ** General Exceptions ** \\
    ILLEGAL_FILE_OPERATION("Illegal file operation occurred."),
    FAILED_TO_LOAD_CONFIG_FILE("Failed to load Database configs"),
    INVALID_DOUBLE("The provided number is not a valid number."),

    // ** GUI Exceptions ** \\


    // ** Logical Exceptions ** \\


    // ** Database Exceptions ** \\
    DB_CONNECTION_FAILURE("An error occurred while trying to connect to the database"),
    EMPLOYEE_INSERT_FAILED("Failure in inserting the employee information into database occurred."),
    TEAM_INSERT_FAILED("Failure in inserting the team information into database occurred."),
    KEY_GENERATION_FAILURE("Failure in generating key to the employee.");



    private final String value;

    ExceptionMessage(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
