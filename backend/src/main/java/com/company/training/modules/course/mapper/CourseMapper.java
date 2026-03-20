package com.company.training.modules.course.mapper;

import com.company.training.modules.course.dto.CourseDetailVO;
import com.company.training.modules.course.dto.CourseQueryDTO;
import com.company.training.modules.course.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<Course> selectPage(CourseQueryDTO queryDTO);

    Long countPage(CourseQueryDTO queryDTO);

    int insert(Course entity);

    int updateById(Course entity);

    Course selectById(@Param("id") Long id);

    CourseDetailVO selectDetailById(@Param("id") Long id);

    int logicDeleteById(@Param("id") Long id);

    int countByCategoryId(@Param("categoryId") Long categoryId);
}
