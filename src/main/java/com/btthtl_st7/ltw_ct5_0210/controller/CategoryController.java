package com.btthtl_st7.ltw_ct5_0210.controller;

import com.btthtl_st7.ltw_ct5_0210.dto.CategoryForm;
import com.btthtl_st7.ltw_ct5_0210.entity.Category;
import com.btthtl_st7.ltw_ct5_0210.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/admin/categories")
    public String list(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "admin/category/list";
    }
    @GetMapping("/admin/categories/new")
    public String createForm(Model model) {

        model.addAttribute("form", new CategoryForm());
        model.addAttribute("mode", "create");
        return "admin/category/form";
    }
    @PostMapping("/admin/categories")
    public String create(@ModelAttribute("form") @Valid CategoryForm form, BindingResult rs, Model model) {
        // Unique name check
        if (!rs.hasErrors() && categoryService.existsByNameIgnoreCase(form.getName())) {
            rs.rejectValue("name", "name.exists", "Tên danh mục đã tồn tại");
        }
        if (rs.hasErrors()) {
            model.addAttribute("mode", "create");
            return "admin/category/form";
        }
        Category c = new Category();
        c.setName(form.getName());
        c.setSlug(form.getSlug());
        c.setDescription(form.getDescription());
        c.setActive(form.getActive() != null ? form.getActive() : true);
        categoryService.save(c);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        var c = categoryService.findById(id);
        CategoryForm form = new CategoryForm();
        form.setId(c.getId());
        form.setName(c.getName());
        form.setSlug(c.getSlug());
        form.setDescription(c.getDescription());
        form.setActive(c.isActive());

        model.addAttribute("form", form);
        model.addAttribute("mode", "edit");
        return "admin/category/form";
    }

    // EDIT: submit
    @PostMapping("/admin/categories/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("form") @Valid CategoryForm form,
                         BindingResult rs,
                         Model model) {
        var c = categoryService.findById(id);

        // Unique name check (khác chính nó)
        if (!rs.hasErrors()) {
            var existed = categoryService.findByNameIgnoreCase(form.getName());
            if (existed.isPresent() && !existed.get().getId().equals(id)) {
                rs.rejectValue("name", "name.exists", "Tên danh mục đã tồn tại");
            }
        }
        if (rs.hasErrors()) {
            model.addAttribute("mode", "edit");
            return "admin/category/form";
        }

        c.setName(form.getName());
        c.setSlug(form.getSlug());
        c.setDescription(form.getDescription());
        c.setActive(form.getActive() != null ? form.getActive() : true);
        categoryService.save(c);
        return "redirect:/admin/categories";
    }

    // DELETE
    @PostMapping("/admin/categories/{id}/delete")
    public String delete(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "redirect:/admin/categories";
    }
}
