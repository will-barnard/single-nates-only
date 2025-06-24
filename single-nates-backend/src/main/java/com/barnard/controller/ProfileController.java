package com.barnard.controller;

import com.barnard.dao.ProfileDao;
import com.barnard.dao.UserDao;
import com.barnard.model.Profile;
import com.barnard.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("permitAll()")
public class ProfileController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ProfileDao profileDao;

    // Add this method to set CORS headers manually
    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

    @GetMapping("/profiles/{userId}")
    public ResponseEntity<Profile> getProfileByUserId(@PathVariable int userId) {
        Profile profile = profileDao.getProfileByUserId(userId);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/profiles/checkIfProfile")
    public ResponseEntity<Boolean> checkIfProfileExists(Principal principal) {
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        boolean exists = profileDao.checkIfProfileExists(userId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/profiles/user")
    public ResponseEntity<Profile> getProfileByPrincipal(Principal principal) {
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        Profile profile = profileDao.getProfileByUserId(userId);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileDao.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @PostMapping("/profiles")
    public Profile createProfile(@RequestBody Profile profile, Principal principal) {
        Profile newProfile = null;
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        profile.setUserId(userId);
        try {
            newProfile = profileDao.createProfile(profile);
        } catch (Exception e) {
            System.out.println("Something went wrong creating the profile in db");
            return null; // or handle the error appropriately
        }
        return newProfile;
    }

    @PutMapping("/profiles")
    public Profile updateProfile(@RequestBody Profile profile, Principal principal) {
        Profile updatedProfile = null;
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        profile.setUserId(userId);
        try {
            updatedProfile = profileDao.updateProfile(profile);
        } catch (Exception e) {
            System.out.println("Something went wrong updating the profile in db");
        }
        return updatedProfile;
    }

    @DeleteMapping("/profiles/{userId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable int userId) {
        profileDao.deleteProfile(userId);
        return ResponseEntity.ok().build();
    }
}
