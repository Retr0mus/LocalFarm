package com.github.countrybros.application.user;

import com.github.countrybros.model.user.User;
import com.github.countrybros.model.user.UserRole;
import com.github.countrybros.web.user.request.AddUserRequest;
import com.github.countrybros.web.user.request.EditUserRequest;

/**
 * Permits to manage the users.
 */
public interface IUserService {

     User getUser(int userId);

     void addUser(AddUserRequest request);

     void deleteUser(int userId);

     void editUser(EditUserRequest request);

     void addUserRole(int userId, UserRole userRole);

     void removeUserRole(int userId, UserRole role);

     boolean checkEmailExists(String email);

     boolean userHasRole(int userId, UserRole role);

}
