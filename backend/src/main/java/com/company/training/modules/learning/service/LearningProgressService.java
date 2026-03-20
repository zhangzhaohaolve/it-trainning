package com.company.training.modules.learning.service;

import com.company.training.common.page.PageResult;
import com.company.training.modules.learning.dto.*;
import com.company.training.modules.learning.entity.LearningProgress;

import java.util.Map;

public interface LearningProgressService {
    PageResult<LearningProgress> list(LearningProgressQueryDTO queryDTO);
    Long add(LearningProgressAddDTO dto);
    void update(LearningProgressUpdateDTO dto);
    void delete(Long id);
    Map<String, Double> avgStats(Long courseId, Long deptId);
}
