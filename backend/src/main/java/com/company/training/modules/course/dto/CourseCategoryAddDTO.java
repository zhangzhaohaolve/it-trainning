package com.company.training.modules.course.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseCategoryAddDTO {
    @NotBlank(message = "分类名称不能为空")
    private String name;
    private Integer sort;
}
