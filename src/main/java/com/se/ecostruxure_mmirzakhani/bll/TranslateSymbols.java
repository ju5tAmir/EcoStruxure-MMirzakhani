package com.se.ecostruxure_mmirzakhani.bll;

public enum TranslateSymbols {
    ANNUAL_SALARY("ANNUAL_SALARY"),
    ANNUAL_WORK_HOURS("ANNUAL_WORK_HOURS");

    private final String value;

    TranslateSymbols(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
