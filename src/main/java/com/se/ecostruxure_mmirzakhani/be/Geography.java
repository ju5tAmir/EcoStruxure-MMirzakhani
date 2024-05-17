package com.se.ecostruxure_mmirzakhani.be;

public class Geography {
    private int geographyID;
    private String countryName;
    private String region;

    public Geography(int geographyID, String countryName, String region) {
        this.geographyID = geographyID;
        this.countryName = countryName;
        this.region = region;
    }

    // Getters and Setters
    public int getGeographyID() { return geographyID; }
    public void setGeographyID(int geographyID) { this.geographyID = geographyID; }

    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
}
