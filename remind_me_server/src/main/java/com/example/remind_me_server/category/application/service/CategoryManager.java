package com.example.remind_me_server.category.application.service;


import org.springframework.stereotype.Service;

import com.example.remind_me_server.category.application.port.out.CategoryRepository;
import com.example.remind_me_server.category.domain.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryManager {
    private final CategoryRepository categoryRepository;


    public Category getOrCreateCategory(String categoryName, Long userId) {
        Category co =  categoryRepository.findByName(categoryName, userId);

        if(co != null) return co;

        Category newC = new Category(null, categoryName, userId);
        Category category = categoryRepository.save(newC);

        return category;
        // If not found, create a new category
        // return categoryRepository.save(Category.create(categoryName, userId));       
    }
}
