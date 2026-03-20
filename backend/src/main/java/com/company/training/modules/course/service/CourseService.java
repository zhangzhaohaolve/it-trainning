package com.company.training.modules.course.service;

import com.company.training.common.page.PageResult;
import com.company.training.modules.course.dto.*;
import com.company.training.modules.course.entity.Course;

public interface CourseService {
    PageResult<Course> page(CourseQueryDTO queryDTO);

    Long add(CourseAddDTO dto);

    void update(CourseUpdateDTO dto);

    void delete(Long id);

    CourseDetailVO detail(Long id);
}
