package com.example.oferte_directe.repository;

import com.example.oferte_directe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.firstName = :firstName AND u.lastName = :lastName AND u.email = :email")
    Optional<User> findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);

}
