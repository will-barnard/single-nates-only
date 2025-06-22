package com.barnard.dao;

import com.barnard.exception.DaoException;
import com.barnard.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcImageDao implements ImageDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int createImage(String imagePath, int userId) {
        int imageId;
        String sql = "INSERT into image (image_path, user_id) " +
                "VALUES (?, ?) " +
                "RETURNING image_id;";
        try {
            imageId = jdbcTemplate.queryForObject(sql, int.class, imagePath, userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return imageId;
    }

    @Override
    public String getImagePathById(int imageId) {
        String path = null;
        String sql = "SELECT image_path FROM image " +
                "WHERE image_id = ?;";
        try {
            path = jdbcTemplate.queryForObject(sql, String.class, imageId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return path;
    }

    @Override
    public void addImageToProfile(int userId, int imageId) {
        String sql = "UPDATE profiles " +
                "SET profile_picture_id = ? " +
                "WHERE user_id = ?;";
        try {
            jdbcTemplate.update(sql, imageId, userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

}
