package com.company.training.modules.learning.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LearningProgressUpdateDTO {
    @NotNull private Long id;
    @NotNull @Min(0) @Max(100)
    private Integer progress;
}
