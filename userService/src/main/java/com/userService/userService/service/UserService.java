package com.userService.userService.service;

import java.util.List;

import com.userService.userService.entity.User;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByName(String userName);
    User saveUser(User user);
}
