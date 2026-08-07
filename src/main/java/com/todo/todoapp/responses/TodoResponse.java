package com.todo.todoapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.todo.todoapp.models.Status;
import com.todo.todoapp.models.Todo;

public record TodoResponse(
        @JsonProperty("id")
        int id,
        @JsonProperty("title")
        String title,
        @JsonProperty("description")
        String description,
        @JsonProperty("status")
        Status status,
        @JsonProperty("category")
        CategoryResponse categoryResponse
) {
    public TodoResponse(Todo todo) {
        this(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getStatus(),
                todo.getCategory() != null ?
                new CategoryResponse(todo.getCategory()) : null
        );
    }
}
