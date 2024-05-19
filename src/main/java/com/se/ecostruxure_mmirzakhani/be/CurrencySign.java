package com.se.ecostruxure_mmirzakhani.be;

import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;

public enum CurrencySign {
    EUR("€"),
    USD("$");

    private final String sign;


    CurrencySign(String sign){
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
