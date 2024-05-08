package com.se.ecostruxure_mmirzakhani.be;

public enum Region {
    AFRICA("Africa"),
    ANTARCTICA("Antarctica"),
    ASIA("Asia"),
    EUROPE("Europe"),
    NORTH_AMERICA("North America"),
    AUSTRALIA("Australia"),
    SOUTH_AMERICA("South America");

    private final String name;

    Region(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

