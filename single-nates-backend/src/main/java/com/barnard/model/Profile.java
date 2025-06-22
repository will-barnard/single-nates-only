package com.barnard.model;

public class Profile {
    private int profileId;
    private int userId;
    private Integer profilePictureId;
    private String firstName;
    private String lastName;
    private String genderIdentity;
    private String interestedIn;
    private String location;
    private String bio;
    private String skills;
    private String criminalRecord;
    private String education;

    // Constructor with all parameters
    public Profile(int profileId, int userId, Integer profilePictureId, String firstName, String lastName, String genderIdentity, String interestedIn, String location, String bio, String skills, String criminalRecord, String education) {
        this.profileId = profileId;
        this.userId = userId;
        this.profilePictureId = profilePictureId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.genderIdentity = genderIdentity;
        this.interestedIn = interestedIn;
        this.location = location;
        this.bio = bio;
        this.skills = skills;
        this.criminalRecord = criminalRecord;
        this.education = education;
    }

    // Getters and Setters
    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getProfilePictureId() {
        return profilePictureId;
    }

    public void setProfilePictureId(Integer profilePictureId) {
        this.profilePictureId = profilePictureId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGenderIdentity() {
        return genderIdentity;
    }

    public void setGenderIdentity(String genderIdentity) {
        this.genderIdentity = genderIdentity;
    }

    public String getInterestedIn() {
        return interestedIn;
    }

    public void setInterestedIn(String interestedIn) {
        this.interestedIn = interestedIn;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getCriminalRecord() {
        return criminalRecord;
    }

    public void setCriminalRecord(String criminalRecord) {
        this.criminalRecord = criminalRecord;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}