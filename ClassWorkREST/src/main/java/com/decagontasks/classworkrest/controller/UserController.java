package com.decagontasks.classworkrest.controller;

import com.decagontasks.classworkrest.entity.User;
import com.decagontasks.classworkrest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController (UserService userService){
        this.userService = userService;
    }
//    create
    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        user.setId(0L);
        return userService.createUser(user);
    }
//    read
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id){
        return userService.displayUser(id);
    }
//    update
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }
//    delete
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        return userService.deleteUser(id);
    }
}
