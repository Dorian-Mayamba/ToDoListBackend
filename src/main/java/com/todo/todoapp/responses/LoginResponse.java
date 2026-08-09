package com.todo.todoapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponse(
        @JsonProperty("token")
        String accessToken,
        @JsonProperty("type")
        String tokenType,
        @JsonProperty("user")
        UserSummary userSummary
) {
}
