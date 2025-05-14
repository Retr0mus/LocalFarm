package com.github.countrybros.application;

import com.github.countrybros.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Service that performs all the tasks related to the management of the user
 */
public class UserManager {
    private Map<Integer, User> users = new HashMap<>();

    public User getUser(int userId) {
        return users.get(userId);
    }

    public boolean addUser(User user) {
        return false;
    }

    public boolean deleteUser(int userId) {
        return false;
    }

    public boolean editUser(User user) {
        return false;
    }

    public boolean addUserRole(int userId, String role) {
        return false;
    }
    public boolean removeUserRole(int userId, String role) {
        return false;
    }


}