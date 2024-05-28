package com.example.oferte_directe.service;

import com.example.oferte_directe.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);
    List<User> getAll();
    Optional<User> getById(Long id);
}
