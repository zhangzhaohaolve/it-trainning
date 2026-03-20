package com.company.training.modules.course.service.impl;

import com.company.training.common.exception.BizException;
import com.company.training.modules.course.dto.CourseCategoryAddDTO;
import com.company.training.modules.course.dto.CourseCategoryQueryDTO;
import com.company.training.modules.course.dto.CourseCategoryUpdateDTO;
import com.company.training.modules.course.entity.CourseCategory;
import com.company.training.modules.course.mapper.CourseCategoryMapper;
import com.company.training.modules.course.mapper.CourseMapper;
import com.company.training.modules.course.service.CourseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseCategoryServiceImpl implements CourseCategoryService {
    private final CourseCategoryMapper categoryMapper;
    private final CourseMapper courseMapper;

    @Override
    public List<CourseCategory> list(CourseCategoryQueryDTO queryDTO) {
        return categoryMapper.selectList(queryDTO);
    }

    @Override
    public Long add(CourseCategoryAddDTO dto) {
        CourseCategory entity = new CourseCategory();
        entity.setName(dto.getName());
        entity.setSort(dto.getSort() == null ? 0 : dto.getSort());
        categoryMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void update(CourseCategoryUpdateDTO dto) {
        CourseCategory entity = new CourseCategory();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSort(dto.getSort());
        if (categoryMapper.updateById(entity) == 0) {
            throw new BizException("分类不存在或已删除");
        }
    }

    @Override
    public void delete(Long id) {
        if (courseMapper.countByCategoryId(id) > 0) {
            throw new BizException("分类下存在课程，禁止删除");
        }
        if (categoryMapper.logicDeleteById(id) == 0) {
            throw new BizException("分类不存在或已删除");
        }
    }
}
