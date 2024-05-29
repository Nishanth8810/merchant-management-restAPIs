package com.example.merchantmanagement.service.serviceImpl;

import com.example.merchantmanagement.dto.CategoryRequest;
import com.example.merchantmanagement.entity.Category;
import com.example.merchantmanagement.repository.CategoryRepository;
import com.example.merchantmanagement.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImplementation implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public HttpStatus saveCategory(CategoryRequest categoryRequest) {
        try {
            Category category = new Category();
            category.setCategoryName(categoryRequest.getCategoryName());
            categoryRepository.save(category);
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

    }
}
