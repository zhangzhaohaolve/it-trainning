package com.company.training.modules.trainingplan.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrainingAssignDeptDTO {
    @NotNull(message = "计划ID不能为空")
    private Long planId;
    @NotNull(message = "部门ID不能为空")
    private Long deptId;
}
