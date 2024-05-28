package com.example.merchantmanagement.service;

import com.example.merchantmanagement.dto.CategoryRequest;
import org.springframework.http.HttpStatus;

public interface CategoryService {
    HttpStatus saveCategory(CategoryRequest categoryRequest);
}
