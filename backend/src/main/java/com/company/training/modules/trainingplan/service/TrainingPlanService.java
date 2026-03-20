package com.company.training.modules.trainingplan.service;

import com.company.training.common.page.PageResult;
import com.company.training.modules.trainingplan.dto.TrainingPlanAddDTO;
import com.company.training.modules.trainingplan.dto.TrainingPlanQueryDTO;
import com.company.training.modules.trainingplan.dto.TrainingPlanUpdateDTO;
import com.company.training.modules.trainingplan.entity.TrainingPlan;

public interface TrainingPlanService {
    PageResult<TrainingPlan> page(TrainingPlanQueryDTO queryDTO);

    Long add(TrainingPlanAddDTO dto);

    void update(TrainingPlanUpdateDTO dto);

    void delete(Long id);

    TrainingPlan detail(Long id);

    void refreshStatus();
}
