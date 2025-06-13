package com.github.countrybros.application.user;

import com.github.countrybros.application.errors.NotFoundInRepositoryException;
import com.github.countrybros.infrastructure.repository.IUserRepository;
import com.github.countrybros.model.user.Cart;
import com.github.countrybros.model.user.User;
import com.github.countrybros.model.user.UserRole;
import com.github.countrybros.web.user.request.AddUserRequest;
import com.github.countrybros.web.user.request.EditUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        User user = userRepository.getUsersByUserId(userId);
        if (user == null) {
            throw new NotFoundInRepositoryException("User with ID " + userId + " not found.");
        }
        return user;
    }

    @Override
    public void addUser(AddUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        Cart cart = new Cart();
        user.setCart(cart);
        cartService.save(cart);
        user.setRoles(request.getRoles());

        userRepository.save(user);

    }

    @Override
    public void deleteUser(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundInRepositoryException("Cannot delete: User with ID " + userId + " not found.");
        }
        userRepository.deleteById(userId);

    }

    @Override
    public void editUser(EditUserRequest request) {
        User existingUser = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundInRepositoryException("Cannot edit: User with ID " + request.getUserId() + " not found."));

        existingUser.setName(request.getName());
        existingUser.setEmail(request.getEmail());
        existingUser.setPassword(request.getPassword());
        existingUser.setRoles(request.getRoles());

        Cart cart = cartService.getCartById(request.getCartId());
        existingUser.setCart(cart);

        userRepository.save(existingUser);
    }

    @Override
    public void addUserRole(int userId, UserRole role) {
        User user = userRepository.getUsersByUserId(userId);
        if (user == null) {
            throw new NotFoundInRepositoryException("User with ID " + userId + " not found.");
        }

        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }

    @Override
    public void removeUserRole(int userId, UserRole role) {

        User user = userRepository.getUsersByUserId(userId);
        if (user == null) {
            throw new NotFoundInRepositoryException("User with ID " + userId + " not found.");
        }

        if (user.getRoles().contains(role)) {
            user.getRoles().remove(role);
            userRepository.save(user);
        }

    }

    @Override
    public boolean checkEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean userHasRole(int userId, UserRole role) {
        User user = userRepository.getUsersByUserId(userId);
        if (user == null) {
            throw new NotFoundInRepositoryException("User with ID " + userId + " not found.");
        }
        return user.getRoles().contains(role);
    }


}