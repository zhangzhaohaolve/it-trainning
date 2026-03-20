package com.company.training.modules.exam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuestionUpdateDTO extends QuestionAddDTO {
    @NotNull private Long id;
}
