package com.company.training.modules.exam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamAddDTO {
    @NotNull private Long courseId;
    @NotBlank private String title;
    @NotNull private Integer duration;
    @NotNull private Integer totalScore;
    @NotNull private Integer passScore;
    @NotNull private LocalDateTime startTime;
    @NotNull private LocalDateTime endTime;
}
