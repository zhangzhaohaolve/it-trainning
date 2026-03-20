package com.company.training.modules.exam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuestionAddDTO {
    @NotNull private Long courseId;
    @NotNull private Integer type;
    @NotBlank private String content;
    private String options;
    @NotBlank private String answer;
    @NotNull private Integer difficulty;
}
