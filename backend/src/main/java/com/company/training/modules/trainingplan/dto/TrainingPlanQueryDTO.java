package com.company.training.modules.trainingplan.dto;

import com.company.training.common.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class TrainingPlanQueryDTO extends PageRequest {
    private LocalDateTime startTimeFrom;
    private LocalDateTime startTimeTo;
}
