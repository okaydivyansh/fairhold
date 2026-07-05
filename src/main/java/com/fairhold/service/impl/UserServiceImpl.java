package com.fairhold.service.impl;

import com.fairhold.dto.request.LoginRequest;
import com.fairhold.dto.request.SignupRequest;
import com.fairhold.dto.response.LoginResponse;
import com.fairhold.dto.response.SignupResponse;
import com.fairhold.entity.Role;
import com.fairhold.entity.User;
import com.fairhold.exception.EmailAlreadyExistsException;
import com.fairhold.exception.InvalidCredentialsException;
import com.fairhold.repository.UserRepository;
import com.fairhold.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//Checks duplicate email
//
//Encrypts password
//
//Creates User entity
//
//Saves user
//
//Builds SignupResponse
//
//Returns it
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //without lombok
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserServiceImpl(UserRepository userRepository,
//                           PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
    @Override
    public SignupResponse registerUser(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());

//        User.builder() is used for building user object and it is equivalent to
//        User user = new User(
//                null,
//                request.getName(),
//                request.getEmail(),
//                encodedPassword,
//                Role.USER
//        );
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                .role(Role.USER)  //Every new signup gets the USER role. Only an admin should be able to create ADMIN users later.
                .build(); //This actually creates the User object.
        User savedUser = userRepository.save(user); //userRepository.save(user) persists the entity.

        //we are not returning user object. rather we return a DTO
        return SignupResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .message("User registered successfully.")
                .build();

    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return LoginResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .message("Login successful.")
                .build();
    }
}