package com.github.countrybros.application.user;

import com.github.countrybros.model.user.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Service that performs all the tasks related to the management of the user
 */
public class UserService implements IUserService {
    private Map<Integer, User> users = new HashMap<>();

    @Override
    public User getUser(int userId) {
        return users.get(userId);
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(int userId) {
        return false;
    }

    @Override
    public boolean editUser(User user) {
        return false;
    }

    @Override
    public boolean addUserRole(int userId, String role) {
        return false;
    }

    @Override
    public boolean removeUserRole(int userId, String role) {
        return false;
    }



}