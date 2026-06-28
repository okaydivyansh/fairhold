package com.fairhold.repository;

import com.fairhold.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//User entity → describes what data is stored.
//UserRepository → provides how to access and manipulate that data.
public interface UserRepository extends JpaRepository<User, Long> {
//spring automatically converts this to SELECT * FROM users
//WHERE email = ?
//This is called Query Method Derivation.
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}