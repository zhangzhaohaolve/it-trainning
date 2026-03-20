package com.company.training.modules.course.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LearningRecordMapper {
    int countByCourseId(@Param("courseId") Long courseId);
}
