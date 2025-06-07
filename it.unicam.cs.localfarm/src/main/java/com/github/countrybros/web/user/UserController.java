package com.github.countrybros.web.user;

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
    public void addUser(@RequestBody User user) {
         userService.addUser(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam int userId) {
         userService.deleteUser(userId);
    }

    @GetMapping("/get")
    public User getUser(@RequestParam int userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/edit")
    public void editUser(@RequestParam User user) {
         userService.editUser(user);
    }

    @PostMapping("/addRole")
    public void addRole(@RequestParam String role,@RequestParam int userId) {
        userService.addUserRole(userId, role);
    }

    @DeleteMapping("/removeRole")
    public void removeUserRole(@RequestParam int userId,@RequestParam String role) {
         userService.removeUserRole(userId, role);
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
