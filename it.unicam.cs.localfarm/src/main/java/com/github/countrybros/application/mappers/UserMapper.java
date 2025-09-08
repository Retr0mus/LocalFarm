package com.github.countrybros.application.mappers;

import com.github.countrybros.application.models.dtos.user.UserDto;
import com.github.countrybros.application.models.requests.user.AddUserRequest;
import com.github.countrybros.model.user.User;
import com.github.countrybros.model.user.UserRole;

import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HexFormat;

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

    public User toDomain(AddUserRequest request) throws NoSuchAlgorithmException {

        User user = new User();
        user.setName(request.name);
        user.setEmail(request.email);
        user.setAddress(request.location);
        user.setPassword(request.password);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(request.password.getBytes());
        String hashedPassword = HexFormat.of().formatHex(hashBytes);

        user.setPassword(hashedPassword);

        return user;
    }
}
