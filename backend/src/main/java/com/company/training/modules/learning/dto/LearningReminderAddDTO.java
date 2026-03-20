package com.company.training.modules.learning.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LearningReminderAddDTO {
    @NotNull private Long userId;
    @NotNull private Long courseId;
    @NotNull private Integer reminderType;
    @NotBlank private String content;
}
