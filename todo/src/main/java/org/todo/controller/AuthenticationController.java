package org.todo.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.todo.request.AuthenticationRequest;
import org.todo.request.RegisterRequest;
import org.todo.response.AuthenticationResponse;
import org.todo.service.AuthenticationService;

@Tag(name = "Authentication REST API Endpoints",
        description = "Operations related to register user")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Register a user", description = "Create new user in database")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest registerRequest) throws Exception {
        authenticationService.register(registerRequest);
    }

    @Operation(summary = "Login a user",
            description = "Submit email and password to authenticate user")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest  authenticationRequest) throws Exception {
        return authenticationService.login(authenticationRequest);
    }
}
