package com.btthtl_st7.ltw_ct5_0210.config;

import com.btthtl_st7.ltw_ct5_0210.interceptor.RoleUrlInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RoleUrlInterceptor())
                .addPathPatterns("/admin/**", "/user/**");
    }   
}
