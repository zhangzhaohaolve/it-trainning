package com.company.training.modules.course.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseAddDTO {
    @NotBlank(message = "课程名称不能为空")
    private String name;
    private Long categoryId;
    private Long lecturerId;
    private String description;
    private String coverUrl;
    private Integer status;
}
