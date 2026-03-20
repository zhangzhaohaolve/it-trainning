package com.company.training.modules.trainingplan.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class TrainingAssignBatchDTO {
    @NotNull(message = "计划ID不能为空")
    private Long planId;
    @NotEmpty(message = "用户ID列表不能为空")
    private List<Long> userIds;
}
