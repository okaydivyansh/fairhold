package com.fairhold.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

//We use DTOs to decouple the API from the database model. DTOs allow us to expose only the required fields,
//hide sensitive information like passwords, and keep the API stable even if the entity changes internally.
@Getter
@Setter
public class SignupRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}