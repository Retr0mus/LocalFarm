package com.github.countrybros.application.services.user;

import com.github.countrybros.model.user.User;
import com.github.countrybros.model.user.UserRole;
import com.github.countrybros.application.models.requests.user.AddUserRequest;
import com.github.countrybros.application.models.requests.user.EditUserRequest;

/**
 * Permits to manage the users.
 */
public interface IUserService {

     User getUser(int userId);

     void addUser(AddUserRequest request);

     void deleteUser(int userId, int adminId);

     void editUser(EditUserRequest request);

     void addUserRole(int userId, UserRole userRole);

     void removeUserRole(int userId, String role);

     boolean checkEmailExists(String email);

     boolean userHasRole(int userId, UserRole role);

}
