package com.todo.todoapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.todo.todoapp.details.UserInfoDetails;
import com.todo.todoapp.models.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public record UserSummary(
        @JsonProperty("id")
        int userId,
        @JsonProperty("firstName")
        String firstName,
        @JsonProperty("email")
        String username,
        @JsonProperty("roles")
        List<String> roles
) {
    public UserSummary(UserInfoDetails userInfoDetails) {
        this(
                userInfoDetails.getUser().getUserId(),
                userInfoDetails.getUser().getFirstName(),
                userInfoDetails.getUsername(),
                userInfoDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()
        );
    }
}
