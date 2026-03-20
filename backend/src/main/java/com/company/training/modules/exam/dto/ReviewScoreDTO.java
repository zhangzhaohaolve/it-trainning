package com.company.training.modules.exam.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewScoreDTO {
    @NotNull private Long scoreId;
    @NotNull private Integer manualScore;
    @NotNull private Long reviewer;
}
