//Controller:
//   receives HTTP requests,
//   extracts data from the request,
//   calls the appropriate service method,
//   returns the response.

package com.fairhold.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fairhold.service.UserService;
import lombok.RequiredArgsConstructor;
import com.fairhold.dto.request.SignupRequest;
import com.fairhold.dto.response.SignupResponse;
import jakarta.validation.Valid;  //"Before calling my controller method, validate this object."
//Without @Valid, Spring ignores those validation annotations like @NotBlank @Email
import org.springframework.http.HttpStatus;  //This provides standard HTTP status codes.
import org.springframework.http.ResponseEntity; //Instead of returning only data, this can return status, headers,body
import org.springframework.web.bind.annotation.PostMapping; //This maps an HTTP POST request to a controller method.
import org.springframework.web.bind.annotation.RequestBody; //Take the JSON request body and convert it into a Java object.
//Without it, Spring won't know where to get the SignupRequest from.

@RestController //This class will handle HTTP requests, and the return value of its
// methods should be written directly into the HTTP response body (typically as JSON
@RequestMapping("/api/auth")  //This defines a base URL for all endpoints in this controller.
/*  /api → identifies REST API endpoints.
 /auth → groups authentication-related operations.*/

//This avoids repeating /api/auth on every endpoint.

//@RestController is used for REST APIs because it automatically serializes returned objects into JSON.
// @Controller is primarily used for rendering server-side views.
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup") //@PostMapping("/signup") -> /api/auth/signup
    //ResponseEntity<SignupResponse> -> The HTTP response body will contain a SignupResponse DTO.
    public ResponseEntity<SignupResponse> signup(
            @Valid @RequestBody SignupRequest request) {
        //"I have received a valid signup request. I don't know how to register a user, so I'll ask the service."
        SignupResponse response = userService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)  //A new resource has been created.
                .body(response);  //This sets the HTTP response body.
    }
}
