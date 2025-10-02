package com.btthtl_st7.ltw_ct5_0210.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class ProductForm {
    private Long id; // dùng khi edit

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 2, max = 100, message = "Tên sản phẩm từ 2-100 ký tự")
    private String name;

    @NotNull(message = "Giá không được để trống")
    @Min(value = 0, message = "Giá phải >= 0")
    private Long price;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng phải >= 0")
    private Integer quantity;
    @NotNull(message = "Danh mục không được để trống")
    private Long categoryId;

    @Size(max = 500, message = "Mô tả tối đa 500 ký tự")
    private String description;

}
