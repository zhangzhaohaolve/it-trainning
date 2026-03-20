package com.company.training.modules.learning.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LearningReminderUpdateDTO {
    @NotNull private Long id;
    @NotNull private Integer status;
}
