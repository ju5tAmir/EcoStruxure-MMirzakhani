package com.se.ecostruxure_mmirzakhani.bll;

public enum RegexPattern {

    PARENTHESES("\\(([^()]*)\\)"),
    MULTIPLE_NUMBERS("\\d+[*]\\d+");

    private final String value;

    RegexPattern(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
