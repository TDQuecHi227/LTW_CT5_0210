package com.btthtl_st7.ltw_ct5_0210.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/user/home")
    public String userHome() { return "user/home"; }

    @GetMapping("/admin/home")
    public String adminHome() { return "admin/home"; }
}
