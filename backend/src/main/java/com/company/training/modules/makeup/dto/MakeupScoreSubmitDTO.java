package com.company.training.modules.makeup.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MakeupScoreSubmitDTO {
    @NotNull private Long makeupId;
    @NotNull private Long userId;
    @NotBlank private String answers;
}
