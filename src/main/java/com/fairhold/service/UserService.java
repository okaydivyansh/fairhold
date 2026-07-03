package com.fairhold.service;

import com.fairhold.dto.request.SignupRequest;
import com.fairhold.dto.response.SignupResponse;

//Service contains the business logic i,e,.
//this decides what should happen.
//why not directly implement UserServiceImpl -> Because we want our code to depend on abstractions, not implementations.
//Depending on interfaces makes code easier to test, extend, and replace.
//Dependency Inversion Principle
//Dependency Injection is a design pattern where an object's dependencies are provided by an external framework (Spring)
//instead of the object creating them itself. This promotes loose coupling and easier testing.

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