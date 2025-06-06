package com.github.countrybros.web;

import com.github.countrybros.application.user.IUserService;
import com.github.countrybros.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/add")
    public boolean addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/delete")
    public boolean deleteUser(@RequestParam int userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/get")
    public User getUser(@RequestParam int userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/edit")
    public boolean editUser(@RequestParam User user) {
        return userService.editUser(user);
    }

    @PostMapping("/addRole")
    public boolean addRole(@RequestParam String role,@RequestParam int userId) {
        return userService.addUserRole(userId, role);
    }

    @DeleteMapping("/removeRole")
    public boolean removeUserRole(@RequestParam int userId,@RequestParam String role) {
        return userService.removeUserRole(userId, role);
    }

    @GetMapping("/emailExists")
    public boolean checkEmailExists(String email) {
        return false;
    }

    @GetMapping("/hasRole")
    public boolean userHasRole(@RequestParam int userId,@RequestParam String role) {
        return false;
    }

}
