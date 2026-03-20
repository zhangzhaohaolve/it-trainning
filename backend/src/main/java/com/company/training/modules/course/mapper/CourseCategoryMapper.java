package com.company.training.modules.course.mapper;

import com.company.training.modules.course.dto.CourseCategoryQueryDTO;
import com.company.training.modules.course.entity.CourseCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseCategoryMapper {
    List<CourseCategory> selectList(CourseCategoryQueryDTO queryDTO);

    int insert(CourseCategory entity);

    int updateById(CourseCategory entity);

    CourseCategory selectById(@Param("id") Long id);

    int logicDeleteById(@Param("id") Long id);
}
