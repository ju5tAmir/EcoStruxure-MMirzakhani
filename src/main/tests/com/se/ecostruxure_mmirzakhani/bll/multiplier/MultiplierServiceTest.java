package com.se.ecostruxure_mmirzakhani.bll.multiplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplierServiceTest {

    @Test
    @DisplayName("Test applyGrossMarginMultiplier with positive original rate and gross margin percentage")
    public void testApplyGrossMarginMultiplier_withPositiveOriginalRateAndGrossMarginPercentage() {
        double originalRate = 100.0;
        double grossMarginPercentage = 0.2; // 20%

        double expectedNewRate = 125.0; // Calculated manually
        double actualNewRate = MultiplierService.applyGrossMarginMultiplier(originalRate, grossMarginPercentage);

        assertEquals(expectedNewRate, actualNewRate, 0.01);
    }


    @Test
    @DisplayName("Test applyGrossMarginMultiplier with zero original rate and positive gross margin percentage")
    public void testApplyGrossMarginMultiplier_withZeroOriginalRateAndPositiveGrossMarginPercentage() {
        double originalRate = 0.0;
        double grossMarginPercentage = 0.2; // 20%

        double expectedNewRate = 0.0;
        double actualNewRate = MultiplierService.applyGrossMarginMultiplier(originalRate, grossMarginPercentage);

        assertEquals(expectedNewRate, actualNewRate, 0.01);
    }

    @Test
    @DisplayName("Test applyMarkupMultiplier with positive original rate and markup percentage")
    public void testApplyMarkupMultiplier_withPositiveOriginalRateAndMarkupPercentage() {
        double originalRate = 100.0;
        double markupPercentage = 0.2; // 20%

        double expectedNewRate = 120.0; // Calculated manually
        double actualNewRate = MultiplierService.applyMarkupMultiplier(originalRate, markupPercentage);

        assertEquals(expectedNewRate, actualNewRate, 0.01);
    }

    @Test
    @DisplayName("Test applyMarkupMultiplier with zero original rate and positive markup percentage")
    public void testApplyMarkupMultiplier_withZeroOriginalRateAndPositiveMarkupPercentage() {
        double originalRate = 0.0;
        double markupPercentage = 0.2; // 20%

        double expectedNewRate = 0.0;
        double actualNewRate = MultiplierService.applyMarkupMultiplier(originalRate, markupPercentage);

        assertEquals(expectedNewRate, actualNewRate, 0.01);
    }
}