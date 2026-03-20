package com.company.training.modules.learning.service;

import com.company.training.common.page.PageResult;
import com.company.training.modules.learning.dto.*;
import com.company.training.modules.learning.entity.LearningRecord;

public interface LearningRecordService {
    PageResult<LearningRecord> list(LearningRecordQueryDTO queryDTO);
    Long add(LearningRecordAddDTO dto);
    void update(LearningRecordUpdateDTO dto);
    void delete(Long id);
}
