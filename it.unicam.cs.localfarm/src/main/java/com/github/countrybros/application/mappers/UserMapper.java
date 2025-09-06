package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.requests.user.AddUserRequest;
import com.github.countrybros.model.user.User;
import com.github.countrybros.model.user.UserRole;

import java.util.ArrayList;

/**
 * Maps an user
 */
public class UserMapper {
    public static UserDto toDto(User user) {
        ArrayList<String> roles = new ArrayList<>();

        for(UserRole role : user.getRoles())
            roles.add(role.toString());

        return new UserDto(user.getUserId(), user.getName(), user.getEmail(), roles, user.getAddress().toString());
    }

    public User toDomain(AddUserRequest request) {

        User user = new User();
        user.setName(request.name);
        user.setEmail(request.email);
        user.setPassword(request.password);

        return user;
    }
}
