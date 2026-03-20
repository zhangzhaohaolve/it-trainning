package com.company.training.modules.makeup.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MakeupScheduleUpdateDTO {
    @NotNull private Long id;
    @NotNull private LocalDateTime makeupTime;
    @NotNull private String location;
}
