package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.requests.user.AddUserRequest;
import com.github.countrybros.model.user.User;

/**
 * Maps an user
 */
public class UserMapper {

    public User toDomain(AddUserRequest request) {

        User user = new User();
        user.setName(request.name);
        user.setEmail(request.email);
        user.setPassword(request.password);

        return user;
    }
}
