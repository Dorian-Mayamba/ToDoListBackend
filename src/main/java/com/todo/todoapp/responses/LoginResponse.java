package com.todo.todoapp.responses;

public record LoginResponse(
        String accessToken,
        String tokenType,
        UserSummary userSummary
) {
}
