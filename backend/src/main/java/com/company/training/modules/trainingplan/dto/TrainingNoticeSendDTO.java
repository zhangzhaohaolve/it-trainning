package com.company.training.modules.trainingplan.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrainingNoticeSendDTO {
    @NotNull(message = "计划ID不能为空")
    private Long planId;
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    @NotBlank(message = "通知内容不能为空")
    private String content;
}
