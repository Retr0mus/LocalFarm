package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.user.UserDto;
import com.github.countrybros.model.user.User;
import com.github.countrybros.model.user.UserRole;

import java.util.ArrayList;

public class UserMapper {
    public static UserDto toDto(User user) {
        ArrayList<String> roles = new ArrayList<>();

        for(UserRole role : user.getRoles())
            roles.add(role.toString());

        return new UserDto(user.getUserId(), user.getName(), user.getEmail(), roles, user.getAddress().toString());
    }
}
