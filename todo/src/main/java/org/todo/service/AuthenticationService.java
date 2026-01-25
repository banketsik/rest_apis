package org.todo.service;

import org.todo.request.AuthenticationRequest;
import org.todo.request.RegisterRequest;
import org.todo.response.AuthenticationResponse;

public interface AuthenticationService {
    void register(RegisterRequest registerRequest) throws Exception;
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
