package com.btthtl_st7.ltw_ct5_0210.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryForm {
    private Long id; // dùng khi edit

    @NotBlank(message = "Tên danh mục không được để trống")
    @Size(min = 2, max = 100, message = "Tên danh mục từ 2-100 ký tự")
    private String name;

    @Size(max = 120, message = "Slug tối đa 120 ký tự")
    private String slug;

    @Size(max = 500, message = "Mô tả tối đa 500 ký tự")
    private String description;

    private Boolean active = true;
}
