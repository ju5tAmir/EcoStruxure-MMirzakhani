package com.se.ecostruxure_mmirzakhani.model;

import com.se.ecostruxure_mmirzakhani.be.Config;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ConfigModel {
    // List of all the configs
    private final ObservableList<Config> configs = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public ConfigModel(){

    }


    /**
     * Add a configuration to the list.
     * @param config The configuration to be added to the list.
     */
    public void addConfig(Config config){
        configs.add(config);
    }


    public List<Config> getConfigs(){
        return configs;
    }

}
