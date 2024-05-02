package com.se.ecostruxure_mmirzakhani.utils;

import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class to validate various user inputs
 */
public class Validate implements IUtils {

    /**
     * Converts a string representation of a valid double to an actual double value.
     *
     * @param doubleAsString The string representation of the double.
     * @return The double value parsed from the string.
     */
    private static double convertStringToDouble(String doubleAsString){
        return Double.parseDouble(doubleAsString);
    }


    /**
     * Validates a string representation of a double using regex.
     *
     * @param doubleAsString The string to validate as a double.
     * @return The validated double value.
     * @throws ExceptionHandler if the input string does not represent a valid double.
     */
    public static double validateDouble(String doubleAsString) throws ExceptionHandler {
        // Regex pattern to check if user input matches to double format
        Pattern pattern = Pattern.compile("^[-+]?\\d*\\.?\\d+$");
        // Match the provided double with the pattern
        Matcher matcher = pattern.matcher(doubleAsString);

        // If the input matches to the pattern, which means it's an eligible double
        if (matcher.find()){
            return convertStringToDouble(doubleAsString);
        } else {
            // Throw an Exception if the input is not a valid double
            throw new ExceptionHandler(ExceptionMessage.INVALID_DOUBLE.getValue());
        }
    }
}
