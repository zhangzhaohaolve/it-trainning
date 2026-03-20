package com.company.training.modules.course.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LecturerAddDTO {
    @NotBlank(message = "讲师姓名不能为空")
    private String name;
    private String title;
    private String avatarUrl;
    private String bio;
}
