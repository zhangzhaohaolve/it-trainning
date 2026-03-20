package com.company.training.modules.makeup.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MakeupScheduleAddDTO {
    @NotNull private Long makeupId;
    @NotNull private LocalDateTime makeupTime;
    @NotBlank private String location;
}
