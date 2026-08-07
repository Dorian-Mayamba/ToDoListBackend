package com.todo.todoapp.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "A first name is mandatory")
        String firstName,
        @NotBlank(message = "A last name is mandatory")
        String lastName,
        @NotBlank(message = "An email is mandatory")
        @Email(message = "Please enter a valid email")
        String email,
        @NotBlank(message = "A password is mandatory")
        String password
) {
}
