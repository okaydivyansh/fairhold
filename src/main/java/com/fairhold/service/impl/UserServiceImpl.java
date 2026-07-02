package com.fairhold.service.impl;

import com.fairhold.dto.request.SignupRequest;
import com.fairhold.dto.response.SignupResponse;
import com.fairhold.repository.UserRepository;
import com.fairhold.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        return null;

    }
}