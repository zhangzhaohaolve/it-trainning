package com.company.training.modules.learning.service.impl;

import com.company.training.common.exception.BizException;
import com.company.training.common.page.PageResult;
import com.company.training.modules.learning.dto.*;
import com.company.training.modules.learning.entity.LearningReminder;
import com.company.training.modules.learning.mapper.LearningReminderMapper;
import com.company.training.modules.learning.service.LearningReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LearningReminderServiceImpl implements LearningReminderService {

    private final LearningReminderMapper reminderMapper;

    @Override
    public PageResult<LearningReminder> list(LearningReminderQueryDTO queryDTO) {
        return new PageResult<>(reminderMapper.countPage(queryDTO), reminderMapper.selectPage(queryDTO));
    }

    @Override
    public Long add(LearningReminderAddDTO dto) {
        LearningReminder r = new LearningReminder();
        r.setUserId(dto.getUserId());
        r.setCourseId(dto.getCourseId());
        r.setReminderType(dto.getReminderType());
        r.setContent(dto.getContent());
        r.setStatus(0);
        reminderMapper.insert(r);
        return r.getId();
    }

    @Override
    public void update(LearningReminderUpdateDTO dto) {
        if (reminderMapper.updateStatus(dto.getId(), dto.getStatus()) == 0) throw new BizException("提醒不存在");
    }

    @Override
    public void delete(Long id) {
        if (reminderMapper.logicDeleteById(id) == 0) throw new BizException("提醒不存在");
    }
}
