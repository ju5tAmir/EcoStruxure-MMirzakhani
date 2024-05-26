package com.se.ecostruxure_mmirzakhani.utils;

import com.se.ecostruxure_mmirzakhani.be.entities.Contract;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class to validate various user inputs
 */

// ToDo: Implement JUnit tests
//       Implement Percentage validator
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
        // Matches the provided double with the pattern
        Matcher matcher = pattern.matcher(doubleAsString);

        // If the input matches to the pattern, which means it's an eligible double
        if (matcher.find()){
            return convertStringToDouble(doubleAsString);
        } else {
            // Throw an Exception if the input is not a valid double
            throw new ExceptionHandler(ExceptionMessage.INVALID_DOUBLE.getValue());
        }
    }

    /**
     * Validates a string input to ensure it meets the criteria for a name.
     * Names must contain only letters, with a minimum length of 3 and a maximum length of 50 characters.
     *
     * @param name The string to validate as a name.
     * @return The validated name if it meets the criteria.
     * @throws ExceptionHandler if the input string does not represent a valid name.
     */

    public static void validateName(String name) throws RuntimeException{
        // Regex pattern to check if user input matches to a name (only letters, min 3 and max 50)
        Pattern pattern = Pattern.compile("^[a-zA-Z]{3,50}$");

        // Matches the provided name with the pattern
        Matcher matcher = pattern.matcher(name);

        // If the input not matches to the pattern, which means it's an illegal input
        if (!matcher.find()){
            throw new RuntimeException(ExceptionMessage.INVALID_NAME.getValue());
        }
    }


    public static void validateEmail(String input) throws RuntimeException{
        // Regex pattern to check if user input matches to an email
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

        // Matches the provided input with the pattern
        Matcher matcher = pattern.matcher(input);

        // If the input not matches to the pattern, which means it's an illegal input
        if (!matcher.find()){
            throw new RuntimeException(ExceptionMessage.INVALID_EMAIL.getValue());
        }
    }


    /**
     * Checks if a contract's values for annual salary, annual work hours, and average daily work hours
     * are all non-zero, ensuring safe division without encountering arithmetic errors.
     *
     * @param contract The contract object containing salary and work hour information.
     * @return True if all values are non-zero, indicating safe conditions for division; otherwise, false.
     */
    public static boolean safeDivision(Contract contract){
        return contract.getAnnualSalary() != 0 &&
                contract.getAnnualWorkHours() != 0 &&
                contract.getAverageDailyWorkHours() != 0;
    }

}
