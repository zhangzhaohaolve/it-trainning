package com.company.training.modules.course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseUpdateDTO {
    @NotNull(message = "id不能为空")
    private Long id;
    @NotBlank(message = "课程名称不能为空")
    private String name;
    private Long categoryId;
    private Long lecturerId;
    private String description;
    private String coverUrl;
    private Integer status;
}
