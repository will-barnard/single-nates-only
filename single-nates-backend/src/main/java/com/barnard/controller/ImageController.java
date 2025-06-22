package com.barnard.controller;

import com.barnard.dao.ImageDao;
import com.barnard.dao.UserDao;
import com.barnard.model.User;
import com.barnard.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.Principal;
import java.util.UUID;

@RestController
@CrossOrigin
@PreAuthorize("permitAll()")
@RequestMapping(path = "/image")
public class ImageController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private UserDao userDao;
    private final String imageDirectory = "/volume/";


    @PostMapping(path = "/create")
    public int createImage(@RequestParam("image") MultipartFile[] image, Principal principal) {
        String imageString = null;

        for (MultipartFile imageFile : image) {
            try {
                imageString = imageService.saveImageToStorage(imageDirectory, imageFile);
            } catch (Exception e) {
                System.out.println("Something went wrong saving the image");
            }
        }
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        int imageId = 0;
        try {
            imageId = imageDao.createImage(imageString, userId);
        } catch (Exception e) {
            System.out.println("Something went wrong creating the image in db");
        }

        return imageId;
    }

    @GetMapping(path = "/{imageId}")
    public byte[] getImage(@PathVariable int imageId) {
        String imageName = imageDao.getImagePathById(imageId);
        byte[] imageBytes = null;
        try {
            imageBytes = imageService.getImage(imageDirectory, imageName);
        } catch (Exception e) {
            System.out.println("Something went wrong retrieving an image");
        }
        return imageBytes;
    }

    @DeleteMapping(path = "/delete/{imageId}")
    public void deleteImage(@PathVariable int imageId) {
        String imageName = imageDao.getImagePathById(imageId);
        try {
            imageService.deleteImage(imageDirectory, imageName);
        } catch (Exception e) {
            System.out.println("Something went wrong deleting an image");
        }
    }

    @PostMapping(path = "/profile/{imageId}")
    public byte[] addImageToProfile(@PathVariable int imageId, Principal principal) {
        int userId = userDao.getUserByUsername(principal.getName()).getId();
        byte[] image = null;
        try {
            imageDao.addImageToProfile(userId, imageId);
            image = imageService.getImage(imageDirectory, imageDao.getImagePathById(imageId));
        } catch (Exception e) {
            System.out.println("Something went wrong adding an image to profile");
        }
        return image;
    }

}
