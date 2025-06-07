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

    @Override
    public User getUser(int userId) {
        return userRepository.getUsersByUserId(userId);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);

    }

    @Override
    public void editUser(User user) {

        userRepository.save(user);
    }

    @Override
    public void addUserRole(int userId, String role) {

    }

    @Override
    public void removeUserRole(int userId, String role) {

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