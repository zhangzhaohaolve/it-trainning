package com.company.training.modules.course.service.impl;

import com.company.training.common.exception.BizException;
import com.company.training.common.page.PageResult;
import com.company.training.modules.course.dto.*;
import com.company.training.modules.course.entity.Course;
import com.company.training.modules.course.mapper.CourseMapper;
import com.company.training.modules.course.mapper.LearningRecordMapper;
import com.company.training.modules.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;
    private final LearningRecordMapper learningRecordMapper;

    @Override
    public PageResult<Course> page(CourseQueryDTO queryDTO) {
        return new PageResult<>(courseMapper.countPage(queryDTO), courseMapper.selectPage(queryDTO));
    }

    @Override
    public Long add(CourseAddDTO dto) {
        Course entity = new Course();
        entity.setName(dto.getName());
        entity.setCategoryId(dto.getCategoryId());
        entity.setLecturerId(dto.getLecturerId());
        entity.setDescription(dto.getDescription());
        entity.setCoverUrl(dto.getCoverUrl());
        entity.setStatus(dto.getStatus() == null ? 0 : dto.getStatus());
        courseMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void update(CourseUpdateDTO dto) {
        if (courseMapper.selectById(dto.getId()) == null) {
            throw new BizException("课程不存在");
        }
        Course entity = new Course();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCategoryId(dto.getCategoryId());
        entity.setLecturerId(dto.getLecturerId());
        entity.setDescription(dto.getDescription());
        entity.setCoverUrl(dto.getCoverUrl());
        entity.setStatus(dto.getStatus());
        courseMapper.updateById(entity);
    }

    @Override
    public void delete(Long id) {
        if (learningRecordMapper.countByCourseId(id) > 0) {
            throw new BizException("该课程已有学习记录，禁止删除");
        }
        if (courseMapper.logicDeleteById(id) == 0) {
            throw new BizException("课程不存在或已删除");
        }
    }

    @Override
    public CourseDetailVO detail(Long id) {
        CourseDetailVO detail = courseMapper.selectDetailById(id);
        if (detail == null) {
            throw new BizException("课程不存在");
        }
        return detail;
    }
}
