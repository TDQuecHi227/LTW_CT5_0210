package com.btthtl_st7.ltw_ct5_0210.service;

import com.btthtl_st7.ltw_ct5_0210.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String username, String password);
    Optional<User> findByUsernameIgnoreCase(String username);
    boolean existsByUsernameIgnoreCase(String username);
    List<User> findAll();
    void save(User user);
}
