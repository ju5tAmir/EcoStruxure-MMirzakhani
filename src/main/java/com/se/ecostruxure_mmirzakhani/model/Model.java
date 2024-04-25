package com.se.ecostruxure_mmirzakhani.model;

import com.se.ecostruxure_mmirzakhani.be.Profile;
import com.se.ecostruxure_mmirzakhani.bll.Logic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

    private Logic logic;

    public Model() {
        this.logic = new Logic();
    }

    private ObservableList<Profile> profileList = FXCollections.observableArrayList();


    public ObservableList<Profile> getProfiles() {
        updateProfileList();
        return profileList;
    }

    public void addProfileToList(Profile profile) {
        profileList.add(profile);
    }

    public void updateProfileList() {
        this.profileList.setAll(logic.getProfiles());
    }


    public void createProfile(Profile profile) {
        logic.createProfile(profile);
    }

    public boolean deleteProfile(Profile profile) {
        return logic.deleteProfile(profile);
    }

    public void editProfile(Profile profile) {
        logic.editProfile(profile);
    }
}

