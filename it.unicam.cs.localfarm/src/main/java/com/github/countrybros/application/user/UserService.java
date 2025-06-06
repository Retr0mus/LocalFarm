package com.github.countrybros.application.user;

import com.github.countrybros.infrastructure.repository.IUserRepository;
import com.github.countrybros.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service that performs all the tasks related to the management of the user
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
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

    //TODO checks if the email is already present on the marketplace
    @Override
    public boolean checkEmailExists(String email) {
        return false;
    }
    //TODO check if the user already have that roles
    @Override
    public boolean userHasRole(int userId, String role) {
        return false;
    }


}