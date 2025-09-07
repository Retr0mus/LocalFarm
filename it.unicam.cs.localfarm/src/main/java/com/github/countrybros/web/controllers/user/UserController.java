package com.github.countrybros.web.controllers.user;

import com.github.countrybros.application.mappers.UserMapper;
import com.github.countrybros.application.services.user.IUserService;
import com.github.countrybros.model.user.UserRole;
import com.github.countrybros.application.models.requests.user.AddUserRequest;
import com.github.countrybros.application.models.requests.user.EditUserRequest;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@Valid @RequestBody AddUserRequest request) {
        userService.addUser(request);
        return new ResponseEntity<>("User added", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> disableUser(@PathParam("userId") int userId,
                                              @PathParam("adminId") int adminId) {
        userService.disableUser(userId, adminId);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getUser(@RequestParam int userId) {
        return new ResponseEntity<>(UserMapper.toDto(userService.getUser(userId)), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers().stream().map(UserMapper::toDto), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editUser(@RequestBody EditUserRequest request) {
        userService.editUser(request);
        return new ResponseEntity<>("User updated", HttpStatus.OK);
    }

    @PostMapping("/addRole")
    public ResponseEntity<String> addRole(@RequestParam UserRole role, @RequestParam int userId) {
        userService.addUserRole(userId, role);
        return new ResponseEntity<>("Role added to user", HttpStatus.OK);
    }

    @DeleteMapping("/removeRole")
    public ResponseEntity<String> removeUserRole(@RequestParam int userId, @PathParam("role") String role) {
        userService.removeUserRole(userId, role);
        return new ResponseEntity<>("Role removed from user", HttpStatus.OK);
    }

    @GetMapping("/emailExists")
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
        boolean exists = userService.checkEmailExists(email);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping("/hasRole")
    public ResponseEntity<Boolean> userHasRole(@RequestParam int userId, @RequestParam UserRole role) {
        boolean hasRole = userService.userHasRole(userId, role);
        return new ResponseEntity<>(hasRole, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }



}
