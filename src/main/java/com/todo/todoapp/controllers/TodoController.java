package com.todo.todoapp.controllers;

import com.todo.todoapp.details.UserInfoDetails;
import com.todo.todoapp.requests.TodoRequest;
import com.todo.todoapp.responses.TodoResponse;
import com.todo.todoapp.services.TodoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/todos")
@Slf4j
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<TodoResponse> getTodos(@AuthenticationPrincipal Jwt jwt){

        return todoService.getTodosByUsername(jwt.getSubject());
    }

    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@RequestBody TodoRequest todoRequest, @AuthenticationPrincipal Jwt jwt){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(todoService.addTodo(todoRequest, jwt.getSubject()));
    }

    @PutMapping("{todoId}/{userId}")
    public ResponseEntity<TodoResponse> editTodo(@PathVariable("todoId") int targetId,
                                                 @PathVariable("userId") int userId,
                                                 @AuthenticationPrincipal UserInfoDetails userInfoDetails,
                                                 @Valid @RequestBody TodoRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(todoService.updateTodo(targetId, request, userInfoDetails, userId ));
    }

    @DeleteMapping("{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable("todoId") int todoId) {
        todoService.removeTodo(todoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

}
