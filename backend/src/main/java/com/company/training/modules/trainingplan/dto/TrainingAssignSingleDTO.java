package com.company.training.modules.trainingplan.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrainingAssignSingleDTO {
    @NotNull(message = "计划ID不能为空")
    private Long planId;
    @NotNull(message = "用户ID不能为空")
    private Long userId;
}
