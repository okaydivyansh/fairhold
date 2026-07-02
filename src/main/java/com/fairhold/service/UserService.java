package com.fairhold.service;

import com.fairhold.dto.request.SignupRequest;
import com.fairhold.dto.response.SignupResponse;

public interface UserService {

    SignupResponse registerUser(SignupRequest request);

}