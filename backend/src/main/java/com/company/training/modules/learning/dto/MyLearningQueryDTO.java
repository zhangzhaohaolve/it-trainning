package com.company.training.modules.learning.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MyLearningQueryDTO {
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    private Integer status;
}
