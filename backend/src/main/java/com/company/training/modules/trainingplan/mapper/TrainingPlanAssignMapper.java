package com.company.training.modules.trainingplan.mapper;

import com.company.training.modules.trainingplan.entity.TrainingPlanAssign;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrainingPlanAssignMapper {
    int insert(TrainingPlanAssign entity);

    int exists(@Param("planId") Long planId, @Param("userId") Long userId);

    List<TrainingPlanAssign> selectByPlanId(@Param("planId") Long planId);

    TrainingPlanAssign selectById(@Param("id") Long id);

    int logicDeleteById(@Param("id") Long id);

    List<Long> selectAssignedUserIds(@Param("planId") Long planId);
}
