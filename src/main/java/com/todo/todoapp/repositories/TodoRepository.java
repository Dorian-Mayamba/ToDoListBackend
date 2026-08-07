package com.todo.todoapp.repositories;

import com.todo.todoapp.models.Todo;
import com.todo.todoapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findAllByUser(User user);
}
