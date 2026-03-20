package com.company.training.modules.makeup.service;

import com.company.training.modules.makeup.dto.MakeupScheduleAddDTO;
import com.company.training.modules.makeup.dto.MakeupScheduleUpdateDTO;
import com.company.training.modules.makeup.entity.MakeupSchedule;

import java.util.List;

public interface MakeupScheduleService {
    Long add(MakeupScheduleAddDTO dto);
    void update(MakeupScheduleUpdateDTO dto);
    List<MakeupSchedule> calendar();
}
