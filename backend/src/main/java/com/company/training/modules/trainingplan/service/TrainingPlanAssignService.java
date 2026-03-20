package com.company.training.modules.trainingplan.service;

import com.company.training.modules.trainingplan.dto.TrainingAssignBatchDTO;
import com.company.training.modules.trainingplan.dto.TrainingAssignDeptDTO;
import com.company.training.modules.trainingplan.dto.TrainingAssignSingleDTO;
import com.company.training.modules.trainingplan.entity.TrainingPlanAssign;

import java.util.List;

public interface TrainingPlanAssignService {
    void assignBatch(TrainingAssignBatchDTO dto);

    void assignSingle(TrainingAssignSingleDTO dto);

    void assignByDept(TrainingAssignDeptDTO dto);

    List<TrainingPlanAssign> listByPlanId(Long planId);

    void delete(Long id);
}
