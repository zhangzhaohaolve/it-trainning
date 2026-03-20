package com.company.training.modules.learning.service;

import com.company.training.common.page.PageResult;
import com.company.training.modules.learning.dto.*;
import com.company.training.modules.learning.entity.LearningReminder;

public interface LearningReminderService {
    PageResult<LearningReminder> list(LearningReminderQueryDTO queryDTO);
    Long add(LearningReminderAddDTO dto);
    void update(LearningReminderUpdateDTO dto);
    void delete(Long id);
}
