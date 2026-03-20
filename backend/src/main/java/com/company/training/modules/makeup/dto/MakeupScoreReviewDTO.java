package com.company.training.modules.makeup.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MakeupScoreReviewDTO {
    @NotNull private Long id;
    @NotNull private Integer score;
    @NotNull private Long reviewer;
}
