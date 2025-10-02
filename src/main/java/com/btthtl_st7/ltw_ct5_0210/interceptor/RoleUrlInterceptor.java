package com.btthtl_st7.ltw_ct5_0210.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class RoleUrlInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String role = (String) request.getSession().getAttribute("ROLE");

        if (uri.startsWith("/admin/") && !"ADMIN".equals(role)) {
            response.sendRedirect("/login?error=forbidden");
            return false;
        }
        if (uri.startsWith("/user/") && role == null) {
            response.sendRedirect("/login?error=forbidden");
            return false;
        }
        return true;
    }
}
