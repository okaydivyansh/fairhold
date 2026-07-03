package com.fairhold.service;

import com.fairhold.dto.request.SignupRequest;
import com.fairhold.dto.response.SignupResponse;


//this decides what should happen.
//why not directly implement UserServiceImpl -> Because we want our code to depend on abstractions, not implementations.
//Depending on interfaces makes code easier to test, extend, and replace.
//Dependency Inversion Principle

//Controller
//      │
//
//        UserService (Contract)
//      │
//
//        UserServiceImpl (Implementation)

public interface UserService {

    SignupResponse registerUser(SignupRequest request);

}