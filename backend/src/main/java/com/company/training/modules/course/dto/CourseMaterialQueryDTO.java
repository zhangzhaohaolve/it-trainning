package com.company.training.modules.course.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseMaterialQueryDTO {
    @NotNull(message = "课程ID不能为空")
    private Long courseId;
}
