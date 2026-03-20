package com.company.training.modules.learning.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LearningRecordAddDTO {
    @NotNull private Long userId;
    @NotNull private Long courseId;
    @NotNull private Integer duration;
}
