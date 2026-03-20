package com.company.training.modules.trainingplan.mapper;

import com.company.training.modules.trainingplan.dto.TrainingNoticeQueryDTO;
import com.company.training.modules.trainingplan.entity.TrainingNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrainingNoticeMapper {
    int insert(TrainingNotice entity);

    List<TrainingNotice> selectList(TrainingNoticeQueryDTO queryDTO);

    int updateReadStatus(@Param("id") Long id, @Param("status") Integer status);
}
