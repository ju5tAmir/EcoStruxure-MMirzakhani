package com.se.ecostruxure_mmirzakhani.utils;

import com.se.ecostruxure_mmirzakhani.be.Contract;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;

import java.math.BigDecimal;
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

    public static String validateName(String name) throws ExceptionHandler{
        // Regex pattern to check if user input matches to a name (only letters, min 3 and max 50)
        Pattern pattern = Pattern.compile("^[a-zA-Z]{3,50}$");

        // Matches the provided name with the pattern
        Matcher matcher = pattern.matcher(name);

        // If the input matches to the pattern, which means it's an eligible name
        if (matcher.find()){
            // Returns the name, here we could have return boolean, but I thought it would be easier to handle this in the model (This way we reduce complexity in the Model)
            return name;
        } else {
            // Throw an Exception if the input is not a valid name
            throw new ExceptionHandler(ExceptionMessage.INVALID_NAME.getValue());
        }
    }

    /**
     * Checks if a contract's values for annual salary, annual work hours, and average daily work hours
     * are all non-zero, ensuring safe division without encountering arithmetic errors.
     *
     * @param contract The contract object containing salary and work hour information.
     * @return True if all values are non-zero, indicating safe conditions for division; otherwise, false.
     */
    public static boolean safeDivision(Contract contract) {
        return contract.getAnnualSalary().compareTo(BigDecimal.ZERO) != 0 &&
                contract.getAnnualWorkHours() != 0 &&
                contract.getAverageDailyWorkHours() != 0;
    }

    public static BigDecimal validateBigDecimal(String decimalAsString) throws ExceptionHandler {
        // Regex pattern to check if user input matches to BigDecimal format
        Pattern pattern = Pattern.compile("^[-+]?\\d*\\.?\\d+$");
        // Matches the provided string with the pattern
        Matcher matcher = pattern.matcher(decimalAsString);

        // If the input matches the pattern, which means it's an eligible BigDecimal
        if (matcher.find()) {
            return new BigDecimal(decimalAsString);
        } else {
            // Throw an Exception if the input is not a valid BigDecimal
            //throw new ExceptionHandler(ExceptionMessage.INVALID_BIGDECIMAL.getValue());
            throw new RuntimeException();
        }
    }

    // Example usage
    public static void main(String[] args) {
        try {
            BigDecimal validBigDecimal = validateBigDecimal("123.45");
            System.out.println("Valid BigDecimal: " + validBigDecimal);
        } catch (ExceptionHandler e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
