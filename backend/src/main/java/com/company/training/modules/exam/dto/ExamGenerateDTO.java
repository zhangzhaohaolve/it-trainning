package com.company.training.modules.exam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExamGenerateDTO {
    @NotNull private Long examId;
    private Integer singleCount = 5;
    private Integer judgeCount = 5;
    private Integer shortCount = 2;
    private Integer difficulty = 3;
}
