package com.barnard.dao;

import com.barnard.model.Profile;

import java.util.List;

public interface ProfileDao {

    Profile getProfileByUserId(int userId);
    List<Profile> getAllProfiles();
    int createProfile(Profile profile);
    void updateProfile(Profile profile);
    void deleteProfile(int userId);

}
