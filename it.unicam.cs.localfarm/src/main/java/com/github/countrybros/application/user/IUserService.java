package com.github.countrybros.application.user;

import com.github.countrybros.model.user.User;

/**
 * Permits to manage the users.
 */
public interface IUserService {

     User getUser(int userId);

     boolean addUser(User user);

     boolean deleteUser(int userId);

     boolean editUser(User user);

     boolean addUserRole(int userId, String role);

     boolean removeUserRole(int userId, String role);

     boolean checkEmailExists(String email);

     boolean userHasRole(int userId, String role);

}
