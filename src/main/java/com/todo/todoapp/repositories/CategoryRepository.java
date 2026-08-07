package com.todo.todoapp.repositories;

import com.todo.todoapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> getById(int id);
}
