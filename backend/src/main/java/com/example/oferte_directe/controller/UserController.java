package com.example.oferte_directe.controller;

import com.example.oferte_directe.entity.User;
import com.example.oferte_directe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @Autowired

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public void create_user(@RequestBody User user){
        userService.create(user);

    }

    @GetMapping("/")
    public List<User> get_users(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User get_user(@RequestParam Long id){
        return userService.getById(id).get();
    }
}
