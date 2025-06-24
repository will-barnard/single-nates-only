package com.barnard.dao;

import com.barnard.model.Profile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcProfileDao implements ProfileDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Profile getProfileByUserId(int userId) {
        String sql = "SELECT * FROM profiles WHERE user_id = ?";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, userId);
            if (rs.next()) {
                return mapRowToProfile(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkIfProfileExists(int userId) {
        String sql = "SELECT COUNT(*) FROM profiles WHERE user_id = ?";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId);
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Profile> getAllProfiles() {
        List<Profile> profiles = new ArrayList<>();
        String sql = "SELECT * FROM profiles";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
            while (rs.next()) {
                profiles.add(mapRowToProfile(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profiles;
    }

    @Override
    public Profile createProfile(Profile profile) {
        Profile newProfile = null;
        String sql = "INSERT INTO profiles (user_id, profile_picture_id, first_name, last_name, gender_identity, interested_in, location, bio, skills, criminal_record, education) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, profile.getUserId(), profile.getProfilePictureId(), profile.getFirstName(), 
            profile.getLastName(), profile.getGenderIdentity(), profile.getInterestedIn(), profile.getLocation(), 
            profile.getBio(), profile.getSkills(), profile.getCriminalRecord(), profile.getEducation());
            newProfile = getProfileByUserId(profile.getUserId());
            return newProfile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Profile updateProfile(Profile profile) {
        Profile updatedProfile = null;
        String sql = "UPDATE profiles SET profile_picture_id = ?, first_name = ?, last_name = ?, gender_identity = ?, interested_in = ?, location = ?, bio = ?, skills = ?, criminal_record = ?, education = ? WHERE user_id = ?";
        try {
            jdbcTemplate.update(sql, profile.getProfilePictureId(), profile.getFirstName(), profile.getLastName(), 
            profile.getGenderIdentity(), profile.getInterestedIn(), profile.getLocation(), profile.getBio(), 
            profile.getSkills(), profile.getCriminalRecord(), profile.getEducation(), profile.getUserId());
            updatedProfile = getProfileByUserId(profile.getUserId());
            return updatedProfile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteProfile(int userId) {
        String sql = "DELETE FROM profiles WHERE user_id = ?";
        try {
            jdbcTemplate.update(sql, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Profile mapRowToProfile(SqlRowSet rs) {
        return new Profile(
            rs.getInt("profile_id"),
            rs.getInt("user_id"),
            (Integer) rs.getObject("profile_picture_id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("gender_identity"),
            rs.getString("interested_in"),
            rs.getString("location"),
            rs.getString("bio"),
            rs.getString("skills"),
            rs.getString("criminal_record"),
            rs.getString("education")
        );
    }
}