package com.todo.todoapp.services;

import com.todo.todoapp.details.UserInfoDetails;
import com.todo.todoapp.models.Category;
import com.todo.todoapp.models.Todo;
import com.todo.todoapp.requests.TodoRequest;
import com.todo.todoapp.responses.TodoResponse;
import com.todo.todoapp.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    public List<TodoResponse> getTodosByUsername(String username) {
        UserInfoDetails userInfoDetails = (UserInfoDetails) userService.loadUserByUsername(username);
        return todoRepository.findAllByUser(userInfoDetails.getUser())
                .stream()
                .map(TodoResponse::new)
                .toList();
    }

    public TodoResponse addTodo(TodoRequest todoRequest, String username){
        Todo todo = new Todo();
        todo.setTitle(todoRequest.title());
        todo.setDescription(todoRequest.description());
        todo.setStatus(todoRequest.status());
        if (todoRequest.categoryId() != 0) {
            Category category = categoryService.getCategory(todoRequest.categoryId());
            todo.setCategory(category);
        }
        UserInfoDetails userInfoDetails =  (UserInfoDetails) userService.loadUserByUsername(username);
        todo.setUser(userInfoDetails.getUser());
        Todo newTodo = todoRepository.save(todo);
        return new TodoResponse(newTodo);
    }

    public Todo findById(int id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public TodoResponse updateTodo(int id, TodoRequest todoRequest, UserInfoDetails userInfoDetails, int userId) {
        if (userId != userInfoDetails.getUser().getUserId()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        Todo todoToUpdate = findById(id);
        todoToUpdate.setTitle(todoRequest.title());
        todoToUpdate.setDescription(todoRequest.description());
        todoToUpdate.setStatus(todoRequest.status());
        if (todoRequest.categoryId() != 0) {
            Category category = categoryService.getCategory(todoRequest.categoryId());
            todoToUpdate.setCategory(category);
        }

        Todo updatedTodo = todoRepository.save(todoToUpdate);
        return new TodoResponse(updatedTodo);

    }

    public void removeTodo(int id) {
        Todo todo = findById(id);
        todoRepository.delete(todo
        );
    }

}
