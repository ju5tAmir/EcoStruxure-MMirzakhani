package com.se.ecostruxure_mmirzakhani.exceptions;

public enum ExceptionMessage {
    // ** General Exceptions ** \\
    ILLEGAL_FILE_OPERATION("Illegal file operation occurred.");


    // ** GUI Exceptions ** \\


    // ** Logical Exceptions ** \\


    // ** Database Exceptions ** \\




    private final String value;

    ExceptionMessage(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
