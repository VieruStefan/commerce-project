package com.example.oferte_directe.controller;

import com.example.oferte_directe.entity.User;
import com.example.oferte_directe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public User get_user(@RequestParam Long id){
        return userService.getById(id).get();
    }

    @GetMapping("/")
    public ResponseEntity<?> getUserByDetails(@RequestParam  String firstName,
                                                 @RequestParam  String lastName,
                                                 @RequestParam  String email) {
        if(firstName.isEmpty() && lastName.isEmpty() && email.isEmpty()){
            return ResponseEntity.ok().body(userService.getAll());
        }else {
            Optional<User> userOptional = userService.findByUserDetails(firstName, lastName, email);
            return userOptional
                    .map(user -> ResponseEntity.ok().body(user))
                    .orElse(ResponseEntity.notFound().build());
        }
    }
}
