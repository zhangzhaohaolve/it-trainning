package com.company.training.modules.trainingplan.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrainingNoticeReadDTO {
    @NotNull(message = "通知ID不能为空")
    private Long id;
    @NotNull(message = "状态不能为空")
    private Integer status;
}
