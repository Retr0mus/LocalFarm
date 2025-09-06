package com.github.countrybros.application.services.user;

import com.github.countrybros.application.errors.ImpossibleRequestException;
import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.application.mappers.UserMapper;
import com.github.countrybros.infrastructure.repositories.user.IUserRepository;
import com.github.countrybros.model.user.User;
import com.github.countrybros.model.user.UserRole;
import com.github.countrybros.application.models.requests.user.AddUserRequest;
import com.github.countrybros.application.models.requests.user.EditUserRequest;
import com.github.countrybros.model.user.UserStatus;
import com.github.countrybros.model.utils.PasswordSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that performs all the tasks related to the management of the user
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICartService cartService;

    @Override
    public User getUser(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundInRepositoryException("User with id " + userId + "not found"));
    }

    @Override
    public void addUser(AddUserRequest request) {

        if (checkEmailExists(request.email))
            throw new ImpossibleRequestException("Email " + request.email + " already exists.");

        UserMapper userMapper = new UserMapper();
        User user = userMapper.toDomain(request);

        cartService.save(user.getCart());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int userId, int adminId) {
        if(!getUser(adminId).getRoles().contains(UserRole.ADMIN)) {
            throw new NotFoundInRepositoryException("User with ID " + userId + " is not admin.");
        }

        User user = getUser(userId);
        user.setStatus(UserStatus.inactive);
        userRepository.save(user);
    }

    @Override
    public void editUser(EditUserRequest request) {
//        User existingUser = userRepository.findById(request.userId)
//                .orElseThrow(() -> new NotFoundInRepositoryException("Cannot edit: User with ID " + request.userId + " not found."));
//
//        existingUser.setName(request.name);
//        existingUser.setEmail(request.email);
//        existingUser.setPassword(request.password);
//        existingUser.setRoles(request.roles);
//
//        Cart cart = cartService.getCartById(request.cartId);
//        existingUser.setCart(cart);
//
//        userRepository.save(existingUser);
    }

    @Override
    public void addUserRole(int userId, UserRole role) {
        User user = getUser(userId);

        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }

    @Override
    public void removeUserRole(int userId, String role) {

        UserRole userRole;

        try {
            userRole = UserRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ImpossibleRequestException("Role " + role + " not found");
        }

        User user = getUser(userId);

        if (user.getRoles().contains(userRole)) {
            user.getRoles().remove(userRole);
            userRepository.save(user);
        }
        else
            throw new ImpossibleRequestException("User with ID " + user.getUserId() + " has not the role: " + role);

    }

    @Override
    public boolean checkEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean userHasRole(int userId, UserRole role) {
        User user = getUser(userId);
        return user.getRoles().contains(role);
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }


}