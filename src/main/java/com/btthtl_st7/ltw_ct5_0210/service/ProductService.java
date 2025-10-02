package com.btthtl_st7.ltw_ct5_0210.service;

import com.btthtl_st7.ltw_ct5_0210.entity.Product;

import java.util.List;

public interface ProductService {
    void save(Product product);
    void deleteById(Long id);
    List<Product> findAll();
    Product findById(Long id);
}
