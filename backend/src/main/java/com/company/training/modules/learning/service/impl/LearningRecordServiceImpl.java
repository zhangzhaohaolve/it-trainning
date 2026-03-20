package com.company.training.modules.learning.service.impl;

import com.company.training.common.exception.BizException;
import com.company.training.common.page.PageResult;
import com.company.training.modules.learning.dto.*;
import com.company.training.modules.learning.entity.LearningRecord;
import com.company.training.modules.learning.mapper.LearningRecordMapper;
import com.company.training.modules.learning.service.LearningRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LearningRecordServiceImpl implements LearningRecordService {

    private final LearningRecordMapper recordMapper;

    @Override
    public PageResult<LearningRecord> list(LearningRecordQueryDTO queryDTO) {
        return new PageResult<>(recordMapper.countPage(queryDTO), recordMapper.selectPage(queryDTO));
    }

    @Override
    public Long add(LearningRecordAddDTO dto) {
        LearningRecord r = new LearningRecord();
        r.setUserId(dto.getUserId());
        r.setCourseId(dto.getCourseId());
        r.setDuration(dto.getDuration());
        r.setFinishTime(LocalDateTime.now());
        recordMapper.insert(r);
        return r.getId();
    }

    @Override
    public void update(LearningRecordUpdateDTO dto) {
        LearningRecord r = new LearningRecord();
        r.setId(dto.getId());
        r.setDuration(dto.getDuration());
        recordMapper.updateById(r);
    }

    @Override
    public void delete(Long id) {
        throw new BizException("学习档案不可删除");
    }
}
