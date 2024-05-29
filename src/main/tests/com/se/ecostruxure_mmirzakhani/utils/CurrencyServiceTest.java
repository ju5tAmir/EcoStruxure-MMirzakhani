package com.se.ecostruxure_mmirzakhani.utils;

import com.se.ecostruxure_mmirzakhani.be.enums.Currency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyServiceTest {
    @Test
    @DisplayName("Test convert with valid currencies and amount")
    public void testConvert_withValidCurrenciesAndAmount() {
        double amount = 100.0;
        Currency fromCurrency = Currency.USD;
        Currency toCurrency = Currency.EUR;

        double expectedConvertedValue = 91.91; // Calculated manually based on a hypothetical exchange rate
        double actualConvertedValue = CurrencyService.convert(fromCurrency, toCurrency, amount);

        assertEquals(expectedConvertedValue, actualConvertedValue, 0.01);
    }


    @Test
    @DisplayName("Test convert with same currencies and amount")
    public void testConvert_withSameCurrenciesAndAmount() {
        double amount = 100.0;
        Currency fromCurrency = Currency.EUR;
        Currency toCurrency = Currency.EUR;

        double expectedConvertedValue = 100.0;
        double actualConvertedValue = CurrencyService.convert(fromCurrency, toCurrency, amount);

        assertEquals(expectedConvertedValue, actualConvertedValue, 0.01);
    }

    @Test
    @DisplayName("Test convert with zero amount")
    public void testConvert_withZeroAmount() {
        double amount = 0.0;
        Currency fromCurrency = Currency.USD;
        Currency toCurrency = Currency.EUR;

        double expectedConvertedValue = 0.0;
        double actualConvertedValue = CurrencyService.convert(fromCurrency, toCurrency, amount);

        assertEquals(expectedConvertedValue, actualConvertedValue, 0.01);
    }

    @Test
    @DisplayName("Test to only show how shitty Iran's currency is.")
    public void testConvert_toShowHowShittyTheIranCurrencyIs() {
        double amount = 1_000_000;
        Currency fromCurrency = Currency.IRR;
        Currency toCurrency = Currency.EUR;

        double expectedConvertedValue = 21.9;
        double actualConvertedValue = CurrencyService.convert(fromCurrency, toCurrency, amount);

        assertEquals(expectedConvertedValue, actualConvertedValue, 0.01);
    }
}