package com.company.training.modules.learning.dto;

import com.company.training.common.page.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LearningReminderQueryDTO extends PageRequest {
    private Long userId;
    private Integer reminderType;
}
