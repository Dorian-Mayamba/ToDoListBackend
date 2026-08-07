package com.todo.todoapp.responses;

import com.todo.todoapp.models.User;

public record RegisterResponse(
        String firstName,
        String email
) {
    public RegisterResponse(User user) {
        this(user.getFirstName(),
                user.getEmail());
    }
}
