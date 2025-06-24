package com.barnard.dao;

import com.barnard.model.Profile;

import java.util.List;

public interface ProfileDao {

    Profile getProfileByUserId(int userId);
    boolean checkIfProfileExists(int userId);
    List<Profile> getAllProfiles();
    Profile createProfile(Profile profile);
    Profile updateProfile(Profile profile);
    void deleteProfile(int userId);

}
