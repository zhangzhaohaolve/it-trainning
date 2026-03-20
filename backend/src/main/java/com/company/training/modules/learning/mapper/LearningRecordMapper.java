package com.company.training.modules.learning.mapper;

import com.company.training.modules.learning.dto.LearningRecordQueryDTO;
import com.company.training.modules.learning.entity.LearningRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LearningRecordMapper {
    int insert(LearningRecord entity);
    int updateById(LearningRecord entity);
    int logicDeleteById(@Param("id") Long id);
    List<LearningRecord> selectPage(LearningRecordQueryDTO queryDTO);
    Long countPage(LearningRecordQueryDTO queryDTO);
}
