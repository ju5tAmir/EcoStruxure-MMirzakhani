package com.se.ecostruxure_mmirzakhani.be;

import java.util.Objects;

public class Config {
    private int id;
    private String      name;
    private String      description; // Description of the config
    private String      operation; // Mathematical operation for the config
    private String      key; // Key name to call in the other operations
    private String      value; // output after operation is finished
    private boolean     isManual; // If the user can enter a value in this config
    private boolean     isVerified; // If the config is from a verified source

    /**
     * Constructor
     */
    public Config(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue(int id) {
        if (this.id == id){
            return value;
        }
        return "NULL";
    }

    public String getValue(String key) {
        if (Objects.equals(key, this.key)){
            return value;
        }
        return "NULL";
    }
    public void setValue(String value) {
        this.value = value;
    }

    public boolean isManual() {
        return isManual;
    }

    public void setManual(boolean manual) {
        isManual = manual;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
