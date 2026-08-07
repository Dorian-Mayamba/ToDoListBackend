package com.todo.todoapp.controllers;

import com.todo.todoapp.requests.LoginRequest;
import com.todo.todoapp.requests.RegisterRequest;
import com.todo.todoapp.responses.LoginResponse;
import com.todo.todoapp.responses.RegisterResponse;
import com.todo.todoapp.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.register(registerRequest));
    }
}
