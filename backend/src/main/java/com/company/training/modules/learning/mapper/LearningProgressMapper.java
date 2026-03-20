package com.company.training.modules.learning.mapper;

import com.company.training.modules.learning.dto.LearningProgressQueryDTO;
import com.company.training.modules.learning.entity.LearningProgress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LearningProgressMapper {
    int insert(LearningProgress entity);
    int updateById(LearningProgress entity);
    LearningProgress selectById(@Param("id") Long id);
    int logicDeleteById(@Param("id") Long id);
    List<LearningProgress> selectPage(LearningProgressQueryDTO queryDTO);
    Long countPage(LearningProgressQueryDTO queryDTO);
    Double avgByCourse(@Param("courseId") Long courseId);
    Double avgByDept(@Param("deptId") Long deptId);
}
