package com.se.ecostruxure_mmirzakhani.utils;

import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateTest {
    @Test
    @DisplayName("Test validateDouble with valid double string")
    public void testValidateDouble_withValidDoubleString() throws  ExceptionHandler {
        String doubleAsString = "123.45";
        double expectedDouble = 123.45;

        double actualDouble = Validate.validateDouble(doubleAsString);

        assertEquals(expectedDouble, actualDouble, 0.01);
    }

    @Test
    @DisplayName("Test validateDouble with invalid double string")
    public void testValidateDouble_withInvalidDoubleString() {
        String invalidDoubleAsString = "abc";

        assertThrows(ExceptionHandler.class, () -> {
            Validate.validateDouble(invalidDoubleAsString);
        });
    }

    @Test
    @DisplayName("Test validateName with invalid name (less than 2 characters)")
    public void testValidateName_withInvalidNameLessThanTwoChars() {
        String invalidName = "A";

        assertThrows(RuntimeException.class, () -> {
            Validate.validateName(invalidName);
        });
    }

    @Test
    @DisplayName("Test validateName with invalid name (more than 50 characters)")
    public void testValidateName_withInvalidNameMoreThanFiftyChars() {
        String invalidName = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

        assertThrows(RuntimeException.class, () -> {
            Validate.validateName(invalidName);
        });
    }

    @Test
    @DisplayName("Test validateName with invalid name (contains non-letter characters)")
    public void testValidateName_withInvalidNameContainsNonLetters() {
        String invalidName = "John Doe123";

        assertThrows(RuntimeException.class, () -> {
            Validate.validateName(invalidName);
        });
    }

    @Test
    @DisplayName("Test validateEmail with valid email")
    public void testValidateEmail_withValidEmail() {
        String validEmail = "test@example.com";

        assertDoesNotThrow(() -> {
            Validate.validateEmail(validEmail);
        });
    }


    @Test
    @DisplayName("Test validateEmail with invalid email")
    public void testValidateEmail_withInvalidEmail() {
        String invalidEmail = "test@example";

        assertThrows(RuntimeException.class, () -> {
            Validate.validateEmail(invalidEmail);
        });
    }
}