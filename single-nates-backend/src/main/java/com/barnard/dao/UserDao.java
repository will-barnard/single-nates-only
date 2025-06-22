package com.barnard.dao;

import com.barnard.model.RegisterUserDto;
import com.barnard.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();
    User getUserById(int id);
    User getUserByUsername(String username);
    String getUsernameByEmail(String email);
    User createUser(RegisterUserDto user);
    boolean checkIfEmailIsUsed(String email);
    boolean checkIfUsernameIsUsed(String username);
}
