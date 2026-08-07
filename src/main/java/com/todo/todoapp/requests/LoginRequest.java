package com.todo.todoapp.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record LoginRequest(
       @NotBlank (message = "Email is mandatory") @Email(message = "Please supply a valid email") String email,
       @NotBlank(message = "A password is mandatory") @Length(min = 4, message = "The password should contain at least 4 characters") String password
) {

}
