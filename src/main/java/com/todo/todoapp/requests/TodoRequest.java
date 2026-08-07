package com.todo.todoapp.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.todo.todoapp.models.Status;
import jakarta.validation.constraints.NotBlank;

public record TodoRequest(
   @NotBlank(message = "Please enter a task title")
   @JsonProperty("title")
   String title,
   @JsonProperty("description")
   String description,
   @JsonProperty("status")
   Status status,
   @JsonProperty("category")
   int categoryId
) {}
