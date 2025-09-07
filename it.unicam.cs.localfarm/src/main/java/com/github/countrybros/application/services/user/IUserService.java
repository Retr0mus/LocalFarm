package com.github.countrybros.application.services.user;

import com.github.countrybros.model.user.User;
import com.github.countrybros.model.user.UserRole;
import com.github.countrybros.application.models.requests.user.AddUserRequest;
import com.github.countrybros.application.models.requests.user.EditUserRequest;
import com.github.countrybros.application.models.requests.user.AddUserRequest;
import com.github.countrybros.application.models.requests.user.EditUserRequest;

import java.util.List;

import java.util.List;

/**
 * Permits to manage the users.
 */
public interface IUserService {

     User getUser(int userId);

     List<User> getAllUsers();

     void addUser(AddUserRequest request);

     void disableUser(int userId, int adminId);

     void editUser(EditUserRequest request);

     void addUserRole(int userId, UserRole userRole);

     void removeUserRole(int userId, String role);

     boolean checkEmailExists(String email);

     boolean userHasRole(int userId, UserRole role);

     List<User> getAllUsers();

}
