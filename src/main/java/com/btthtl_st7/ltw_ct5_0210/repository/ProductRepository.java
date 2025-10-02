package com.btthtl_st7.ltw_ct5_0210.repository;

import com.btthtl_st7.ltw_ct5_0210.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
