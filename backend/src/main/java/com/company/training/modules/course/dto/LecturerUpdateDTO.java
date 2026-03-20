package com.company.training.modules.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LecturerUpdateDTO {
    @NotNull(message = "id不能为空")
    private Long id;
    @NotBlank(message = "讲师姓名不能为空")
    private String name;
    private String title;
    private String avatarUrl;
    private String bio;
}
