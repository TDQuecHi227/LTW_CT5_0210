package com.btthtl_st7.ltw_ct5_0210.service.impl;

import com.btthtl_st7.ltw_ct5_0210.entity.Category;
import com.btthtl_st7.ltw_ct5_0210.repository.CategoryRepository;
import com.btthtl_st7.ltw_ct5_0210.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public boolean existsByNameIgnoreCase(String name) {
        return categoryRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public Optional<Category> findByNameIgnoreCase(String name) {
        return categoryRepository.findByNameIgnoreCase(name);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }
}
