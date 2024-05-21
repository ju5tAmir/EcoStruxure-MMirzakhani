package com.se.ecostruxure_mmirzakhani.gui.gui_utils;

import com.se.ecostruxure_mmirzakhani.be.CurrencySign;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;

/**
 * This class will provide methods to handle client-side validation and some other utils for the graphical user interface.
 */
public class GUIHelper {


    /**
     * Formats double amount as a pretty string and also append the system currency sign to it
     * input:   12345.678
     * output:  €12,345.67
     */
    public static String formatter(double amount){
        CurrencySign        currencySign    = CurrencyService.getSystemCurrency().getCurrencySign();
        String              value           = CurrencyService.stringFormatter(amount);
        return currencySign.getSign() + value;
    }
}