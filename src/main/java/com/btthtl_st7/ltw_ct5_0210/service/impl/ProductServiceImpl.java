package com.btthtl_st7.ltw_ct5_0210.service.impl;

import com.btthtl_st7.ltw_ct5_0210.entity.Product;
import com.btthtl_st7.ltw_ct5_0210.repository.ProductRepository;
import com.btthtl_st7.ltw_ct5_0210.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }
}
