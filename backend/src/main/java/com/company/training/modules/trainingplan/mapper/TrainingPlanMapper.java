package com.company.training.modules.trainingplan.mapper;

import com.company.training.modules.trainingplan.dto.TrainingPlanQueryDTO;
import com.company.training.modules.trainingplan.entity.TrainingPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrainingPlanMapper {
    List<TrainingPlan> selectPage(TrainingPlanQueryDTO queryDTO);

    Long countPage(TrainingPlanQueryDTO queryDTO);

    int insert(TrainingPlan entity);

    int updateById(TrainingPlan entity);

    TrainingPlan selectById(@Param("id") Long id);

    int logicDeleteById(@Param("id") Long id);

    int autoUpdateStatus();
}
