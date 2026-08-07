package com.todo.todoapp.responses;

import com.todo.todoapp.models.Category;

public record CategoryResponse(
        int id,
        String name
) {
    public CategoryResponse(
            Category category
    ){
        this(category.getId(),
                category.getName()
                );
    }
}
