package com.se.ecostruxure_mmirzakhani.exceptions;

/**
 * Custom exception handler
 */
public class ExceptionHandler extends Exception {

    // Constructor
    public ExceptionHandler(String exceptionMessage){
        super(exceptionMessage);
    }

    /**
     * Exception message with details
     * @param exceptionMessage User defined message for the exception
     * @param details Extra details retrieved for exception
     */
    public ExceptionHandler(String exceptionMessage, String details){
        super(exceptionMessage +
                "\nDetails: \n"
                + details);
    }
}
