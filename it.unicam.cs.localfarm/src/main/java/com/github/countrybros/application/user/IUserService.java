package com.github.countrybros.application.user;

import com.github.countrybros.model.user.User;

/**
 * Permits to manage the users.
 */
public interface IUserService {

    public User getUser(int userId);

    public boolean addUser(User user);

    public boolean deleteUser(int userId);

    public boolean editUser(User user);

    public boolean addUserRole(int userId, String role);

    public boolean removeUserRole(int userId, String role);
}
