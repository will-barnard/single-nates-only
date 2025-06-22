package com.barnard.controller;

import com.barnard.dao.ProfileDao;
import com.barnard.dao.UserDao;
import com.barnard.model.Profile;
import com.barnard.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/profiles/{userId}")
    public ResponseEntity<Profile> getProfileByUserId(@PathVariable int userId) {
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
    public ResponseEntity<Integer> createProfile(@RequestBody Profile profile) {
        int profileId = profileDao.createProfile(profile);
        if (profileId > 0) {
            return ResponseEntity.ok(profileId);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/profiles/{userId}")
    public ResponseEntity<Void> updateProfile(@PathVariable int userId, @RequestBody Profile profile) {
        profile.setUserId(userId);
        profileDao.updateProfile(profile);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/profiles/{userId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable int userId) {
        profileDao.deleteProfile(userId);
        return ResponseEntity.ok().build();
    }
}
