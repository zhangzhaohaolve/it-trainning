package com.company.training.modules.exam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubmitExamDTO {
    @NotNull private Long examId;
    @NotNull private Long userId;
    @NotBlank private String answers;
}
