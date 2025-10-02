package com.btthtl_st7.ltw_ct5_0210.controller;

import com.btthtl_st7.ltw_ct5_0210.dto.ProductForm;
import com.btthtl_st7.ltw_ct5_0210.entity.Category;
import com.btthtl_st7.ltw_ct5_0210.entity.Product;
import com.btthtl_st7.ltw_ct5_0210.repository.CategoryRepository;
import com.btthtl_st7.ltw_ct5_0210.service.CategoryService;
import com.btthtl_st7.ltw_ct5_0210.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    // CREATE: submit
    @PostMapping("/admin/products")
    public String create(@ModelAttribute("form") @Valid ProductForm form, BindingResult rs, Model model) {
        // Kiểm tra Category tồn tại
        Category catOpt = (form.getCategoryId() == null) ? null
                : categoryService.findById(form.getCategoryId());
        if (catOpt == null) {
            rs.rejectValue("categoryId", "category.notfound", "Danh mục không tồn tại");
        }
        if (rs.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("mode", "create");
            return "admin/product/form";
        }

        Product p = new Product();
        p.setName(form.getName());
        p.setPrice(form.getPrice());
        p.setQuantity(form.getQuantity());
        p.setCategory(catOpt);
        p.setDescription(form.getDescription());
        productService.save(p);
        return "redirect:/admin/products";
    }

    // EDIT: form
    @GetMapping("/admin/products/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        var p = productService.findById(id);
        ProductForm form = new ProductForm();
        form.setId(p.getId());
        form.setName(p.getName());
        form.setPrice(p.getPrice());
        form.setQuantity(p.getQuantity());
        form.setCategoryId(p.getCategory() != null ? p.getCategory().getId() : null);
        form.setDescription(p.getDescription());

        model.addAttribute("form", form);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("mode", "edit");
        return "admin/product/form";
    }

    // EDIT: submit
    @PostMapping("/admin/products/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("form") @Valid ProductForm form,
                         BindingResult rs,
                         Model model) {
        var p = productService.findById(id);
        Category catOpt = (form.getCategoryId() == null) ? null
                : categoryService.findById(form.getCategoryId());
        if (catOpt == null) {
            rs.rejectValue("categoryId", "category.notfound", "Danh mục không tồn tại");
        }
        if (rs.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("mode", "edit");
            return "admin/product/form";
        }

        p.setName(form.getName());
        p.setPrice(form.getPrice());
        p.setQuantity(form.getQuantity());
        p.setCategory(catOpt);
        p.setDescription(form.getDescription());
        productService.save(p);
        return "redirect:/admin/products";
    }

    // DELETE
    @PostMapping("/admin/products/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/products";
    }
}
