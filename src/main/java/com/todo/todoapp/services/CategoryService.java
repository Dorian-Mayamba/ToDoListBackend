package com.todo.todoapp.services;

import com.todo.todoapp.exception.notfound.CategoryNotFoundException;
import com.todo.todoapp.models.Category;
import com.todo.todoapp.repositories.CategoryRepository;
import com.todo.todoapp.requests.CategoryRequest;
import com.todo.todoapp.responses.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<List<CategoryResponse>> getCategories() {
        List<CategoryResponse> categories =  categoryRepository.
                findAll()
                .stream()
                .map(CategoryResponse::new)
                .toList();
        return ResponseEntity.ok(categories);
    }

    public ResponseEntity<CategoryResponse> getCategoryResponse(int id){
        return ResponseEntity.ok(
                new CategoryResponse(
                        categoryRepository.getById(id)
                                .orElseThrow(() -> new CategoryNotFoundException("Cannot find category"))
                )
        );
    } // ToDo Create a category response mapper

    public Category getCategory (int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Cannot find category"));
    }

    public ResponseEntity<CategoryResponse> addCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        Category addedCategory = categoryRepository.save(category);
        return getCategoryResponse(addedCategory.getId());
    }

    public ResponseEntity<CategoryResponse> editCategory(int categoryId, CategoryRequest categoryRequest) {
        Category categoryToUpdate = getCategory(categoryId);
        categoryToUpdate.setName(categoryRequest.getName());
        Category updatedCategory = categoryRepository.save(categoryToUpdate);
        return ResponseEntity.ok(
                new CategoryResponse(updatedCategory)
        );
    }

    public ResponseEntity<?> removeCategory(int categoryId){
        categoryRepository.deleteById(categoryId);
        return ResponseEntity.noContent()
                .build();
    }

}
