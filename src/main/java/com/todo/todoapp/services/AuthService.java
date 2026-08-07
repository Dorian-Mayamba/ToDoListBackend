package com.todo.todoapp.services;

import com.todo.todoapp.details.UserInfoDetails;
import com.todo.todoapp.models.User;
import com.todo.todoapp.requests.LoginRequest;
import com.todo.todoapp.requests.RegisterRequest;
import com.todo.todoapp.responses.LoginResponse;
import com.todo.todoapp.responses.RegisterResponse;
import com.todo.todoapp.responses.UserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authToken = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.email(), loginRequest.password()
        );

        Authentication authentication = authenticationManager.authenticate(authToken);

        String accessToken = jwtService.generateToken(authentication.getName());

        return new LoginResponse(
            accessToken,
                "Bearer",
                new UserSummary(
                        (UserInfoDetails) authentication.getPrincipal()
                )
        );
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        return userService.registerUser(registerRequest);
    }

}
