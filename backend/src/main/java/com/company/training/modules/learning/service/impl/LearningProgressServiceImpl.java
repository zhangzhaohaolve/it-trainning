package com.company.training.modules.learning.service.impl;

import com.company.training.common.exception.BizException;
import com.company.training.common.page.PageResult;
import com.company.training.modules.learning.dto.*;
import com.company.training.modules.learning.entity.LearningProgress;
import com.company.training.modules.learning.mapper.LearningProgressMapper;
import com.company.training.modules.learning.service.LearningProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LearningProgressServiceImpl implements LearningProgressService {

    private final LearningProgressMapper progressMapper;

    @Override
    public PageResult<LearningProgress> list(LearningProgressQueryDTO queryDTO) {
        return new PageResult<>(progressMapper.countPage(queryDTO), progressMapper.selectPage(queryDTO));
    }

    @Override
    public Long add(LearningProgressAddDTO dto) {
        LearningProgress p = new LearningProgress();
        p.setUserId(dto.getUserId());
        p.setCourseId(dto.getCourseId());
        p.setProgress(dto.getProgress());
        progressMapper.insert(p);
        return p.getId();
    }

    @Override
    public void update(LearningProgressUpdateDTO dto) {
        LearningProgress exists = progressMapper.selectById(dto.getId());
        if (exists == null) throw new BizException("进度记录不存在");
        exists.setProgress(dto.getProgress());
        progressMapper.updateById(exists);
    }

    @Override
    public void delete(Long id) {
        if (progressMapper.logicDeleteById(id) == 0) throw new BizException("进度记录不存在");
    }

    @Override
    public Map<String, Double> avgStats(Long courseId, Long deptId) {
        Map<String, Double> map = new HashMap<>();
        map.put("courseAvg", courseId == null ? null : progressMapper.avgByCourse(courseId));
        map.put("deptAvg", deptId == null ? null : progressMapper.avgByDept(deptId));
        return map;
    }
}
