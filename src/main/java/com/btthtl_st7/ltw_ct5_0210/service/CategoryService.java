package com.btthtl_st7.ltw_ct5_0210.service;

import com.btthtl_st7.ltw_ct5_0210.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    boolean existsByNameIgnoreCase(String name);
    Optional<Category> findByNameIgnoreCase(String name);
    void deleteById(Long id);
    void save(Category category);
    List<Category> findAll();
    Category findById(Long id);
}
