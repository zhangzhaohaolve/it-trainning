package com.company.training.modules.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseCategoryUpdateDTO {
    @NotNull(message = "id不能为空")
    private Long id;
    @NotBlank(message = "分类名称不能为空")
    private String name;
    private Integer sort;
}
