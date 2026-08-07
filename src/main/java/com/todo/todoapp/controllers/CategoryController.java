package com.todo.todoapp.controllers;

import com.todo.todoapp.requests.CategoryRequest;
import com.todo.todoapp.responses.CategoryResponse;
import com.todo.todoapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return categoryService.getCategories();
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable("categoryId") int categoryId) {
        return categoryService.getCategoryResponse(categoryId);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest categoryRequest){
        return categoryService.addCategory(categoryRequest);
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<CategoryResponse> editCategory(@PathVariable("categoryId") int categoryId, @RequestBody CategoryRequest categoryRequest){
        return categoryService.editCategory(categoryId, categoryRequest);
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<?> removeCategory(@PathVariable("categoryId") int categoryId) {
        return categoryService.removeCategory(categoryId);
    }

}
