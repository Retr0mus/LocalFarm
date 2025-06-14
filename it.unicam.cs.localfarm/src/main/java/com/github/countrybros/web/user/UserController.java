package com.github.countrybros.web.user;

import com.github.countrybros.application.user.IUserService;
import com.github.countrybros.model.user.User;
import com.github.countrybros.model.user.UserRole;
import com.github.countrybros.web.user.request.AddUserRequest;
import com.github.countrybros.web.user.request.EditUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody AddUserRequest request) {
        userService.addUser(request);
        return new ResponseEntity<>("User added", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam int userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<User> getUser(@RequestParam int userId) {
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
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
    public ResponseEntity<String> removeUserRole(@RequestParam int userId, @RequestParam UserRole role) {
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

}
