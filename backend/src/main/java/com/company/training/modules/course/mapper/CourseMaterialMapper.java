package com.company.training.modules.course.mapper;

import com.company.training.modules.course.entity.CourseMaterial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMaterialMapper {
    int insert(CourseMaterial entity);

    int logicDeleteById(@Param("id") Long id);

    List<CourseMaterial> selectByCourseId(@Param("courseId") Long courseId);

    CourseMaterial selectById(@Param("id") Long id);
}
