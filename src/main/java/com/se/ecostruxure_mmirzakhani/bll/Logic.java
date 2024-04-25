package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Profile;

import java.util.List;

public class Logic {

    private UserDAO userDAO;

    public Logic() {
        userDAO = new UserDAO();
    }

    public List<Profile> getProfiles() {
        return userDAO.getProfileList();
    }

    public void createProfile(Profile profile) {
        userDAO.createProfile(profile);
    }

    public void editProfile(Profile profile) {
        userDAO.editProfile(profile);
    }

    public boolean deleteProfile(Profile profile) {
        return userDAO.deleteProfile(profile.getId());
    }
}