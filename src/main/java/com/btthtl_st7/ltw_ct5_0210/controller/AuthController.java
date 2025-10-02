package com.btthtl_st7.ltw_ct5_0210.controller;

import com.btthtl_st7.ltw_ct5_0210.dto.RegisterForm;
import com.btthtl_st7.ltw_ct5_0210.entity.User;
import com.btthtl_st7.ltw_ct5_0210.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpServletRequest request) {
        return userService.login(username, password)
                .map(user -> {
                    request.getSession().setAttribute("ROLE", user.getRole());
                    if ("ADMIN".equals(user.getRole())) return "redirect:/admin/home";
                    else return "redirect:/user/home";
                })
                .orElse("redirect:/login?error=true");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login?logout=true";
    }
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("form", new RegisterForm());
        return "register";
    }
    @PostMapping("/register")
    public String doRegister(@ModelAttribute("form") @Valid RegisterForm form,
                             BindingResult rs,
                             HttpServletRequest request) {

        // Check password confirm
        if (!rs.hasErrors() && !form.getPassword().equals(form.getConfirmPassword())) {
            rs.rejectValue("confirmPassword", "password.mismatch", "Mật khẩu xác nhận không khớp");
        }
        // Check username/email/phone unique
        if (!rs.hasErrors() && userService.existsByUsernameIgnoreCase(form.getUsername())) {
            rs.rejectValue("username", "username.exists", "Tên đăng nhập đã tồn tại");
        }
        if (!rs.hasErrors() && userService.findAll().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(form.getEmail()))) {
            rs.rejectValue("email", "email.exists", "Email đã tồn tại");
        }
        if (!rs.hasErrors() && userService.findAll().stream().anyMatch(u -> u.getPhone().equals(form.getPhone()))) {
            rs.rejectValue("phone", "phone.exists", "Số điện thoại đã tồn tại");
        }

        if (rs.hasErrors()) {
            return "register";
        }

        User u = new User();
        u.setUsername(form.getUsername());
        u.setPassword(form.getPassword());
        u.setEmail(form.getEmail());
        u.setPhone(form.getPhone());
        u.setRole("USER");
        userService.save(u);

        request.getSession().setAttribute("ROLE", "USER");
        return "redirect:/user/home";
    }
}
