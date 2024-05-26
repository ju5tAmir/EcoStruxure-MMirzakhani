package com.se.ecostruxure_mmirzakhani.gui.gui_utils;

import com.se.ecostruxure_mmirzakhani.be.enums.CurrencySign;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;
import com.se.ecostruxure_mmirzakhani.utils.Validate;

/**
 * This class will provide methods to handle client-side validation and some other utils for the graphical user interface.
 */
public class GUIHelper {


    /**
     * Formats double amount as a pretty string and also append the system currency sign to it
     * input:   12345.678
     * output:  €12,345.67
     */
    public static String currencyFormatter(double amount){
        CurrencySign        currencySign    = CurrencyService.getSystemCurrency().getCurrencySign();
        String              value           = CurrencyService.stringFormatter(amount);
        return currencySign.getSign() + value;
    }

    public static String simpleDoubleFormatter(double amount){
        return CurrencyService.stringFormatter(amount);
    }

    public static void validateName(String nameValue) throws RuntimeException {
        Validate.validateName(nameValue);
    }

    public static void validateEmail(String input) {
        Validate.validateEmail(input);
    }
}
