package com.company.training.modules.exam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExamUpdateDTO extends ExamAddDTO {
    @NotNull private Long id;
}
