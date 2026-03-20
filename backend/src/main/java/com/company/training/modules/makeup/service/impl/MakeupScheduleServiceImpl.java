package com.company.training.modules.makeup.service.impl;

import com.company.training.common.exception.BizException;
import com.company.training.modules.makeup.dto.MakeupScheduleAddDTO;
import com.company.training.modules.makeup.dto.MakeupScheduleUpdateDTO;
import com.company.training.modules.makeup.entity.MakeupSchedule;
import com.company.training.modules.makeup.mapper.MakeupScheduleMapper;
import com.company.training.modules.makeup.service.MakeupScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MakeupScheduleServiceImpl implements MakeupScheduleService {
    private final MakeupScheduleMapper scheduleMapper;

    @Override
    public Long add(MakeupScheduleAddDTO dto) {
        MakeupSchedule s = new MakeupSchedule();
        s.setMakeupId(dto.getMakeupId()); s.setMakeupTime(dto.getMakeupTime()); s.setLocation(dto.getLocation());
        scheduleMapper.insert(s);
        return s.getId();
    }

    @Override
    public void update(MakeupScheduleUpdateDTO dto) {
        MakeupSchedule s = new MakeupSchedule();
        s.setId(dto.getId()); s.setMakeupTime(dto.getMakeupTime()); s.setLocation(dto.getLocation());
        if (scheduleMapper.updateById(s) == 0) throw new BizException("补考安排不存在");
    }

    @Override
    public List<MakeupSchedule> calendar() { return scheduleMapper.selectAll(); }
}
