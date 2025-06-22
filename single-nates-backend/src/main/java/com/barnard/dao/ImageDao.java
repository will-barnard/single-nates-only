package com.barnard.dao;


import com.barnard.model.Image;

public interface ImageDao {

    int createImage(String imagePath, int userId);
    String getImagePathById(int imageId);
    void addImageToProfile(int userId, int imageId);

}
