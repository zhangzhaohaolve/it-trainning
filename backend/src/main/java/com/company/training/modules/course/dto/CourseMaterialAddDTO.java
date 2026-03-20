package com.company.training.modules.course.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseMaterialAddDTO {
    @NotNull(message = "课程ID不能为空")
    private Long courseId;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private Long size;
}
