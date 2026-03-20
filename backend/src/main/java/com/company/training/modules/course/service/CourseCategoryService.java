package com.company.training.modules.course.service;

import com.company.training.modules.course.dto.CourseCategoryAddDTO;
import com.company.training.modules.course.dto.CourseCategoryQueryDTO;
import com.company.training.modules.course.dto.CourseCategoryUpdateDTO;
import com.company.training.modules.course.entity.CourseCategory;

import java.util.List;

public interface CourseCategoryService {
    List<CourseCategory> list(CourseCategoryQueryDTO queryDTO);

    Long add(CourseCategoryAddDTO dto);

    void update(CourseCategoryUpdateDTO dto);

    void delete(Long id);
}
