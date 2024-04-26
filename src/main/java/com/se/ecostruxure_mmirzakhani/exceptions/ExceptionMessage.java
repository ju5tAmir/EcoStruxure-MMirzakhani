package com.se.ecostruxure_mmirzakhani.exceptions;

public enum ExceptionMessage {
    // ** General Exceptions ** \\
    ILLEGAL_FILE_OPERATION("Illegal file operation occurred."),
    FAILED_TO_LOAD_CONFIG_FILE("Failed to load Database configs"),

    // ** GUI Exceptions ** \\


    // ** Logical Exceptions ** \\


    // ** Database Exceptions ** \\
    DB_CONNECTION_FAILURE("An error occurred while trying to connect to the database");



    private final String value;

    ExceptionMessage(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
