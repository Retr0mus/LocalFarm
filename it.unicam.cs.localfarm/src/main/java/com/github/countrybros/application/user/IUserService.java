package com.github.countrybros.application.user;

import com.github.countrybros.model.user.User;

/**
 * Permits to manage the users.
 */
public interface IUserService {

     User getUser(int userId);

     void addUser(User user);

     void deleteUser(int userId);

     void editUser(User user);

     void addUserRole(int userId, String role);

     void removeUserRole(int userId, String role);

     boolean checkEmailExists(String email);

     boolean userHasRole(int userId, String role);

}
