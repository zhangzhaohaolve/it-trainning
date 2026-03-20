package com.company.training.modules.trainingplan.service.impl;

import com.company.training.common.exception.BizException;
import com.company.training.common.page.PageResult;
import com.company.training.modules.trainingplan.dto.TrainingPlanAddDTO;
import com.company.training.modules.trainingplan.dto.TrainingPlanQueryDTO;
import com.company.training.modules.trainingplan.dto.TrainingPlanUpdateDTO;
import com.company.training.modules.trainingplan.entity.TrainingPlan;
import com.company.training.modules.trainingplan.mapper.TrainingPlanMapper;
import com.company.training.modules.trainingplan.service.TrainingNoticeService;
import com.company.training.modules.trainingplan.service.TrainingPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TrainingPlanServiceImpl implements TrainingPlanService {

    private final TrainingPlanMapper planMapper;
    private final TrainingNoticeService noticeService;

    @Override
    public PageResult<TrainingPlan> page(TrainingPlanQueryDTO queryDTO) {
        refreshStatus();
        return new PageResult<>(planMapper.countPage(queryDTO), planMapper.selectPage(queryDTO));
    }

    @Override
    public Long add(TrainingPlanAddDTO dto) {
        if (dto.getEndTime().isBefore(dto.getStartTime())) {
            throw new BizException("结束时间不能早于开始时间");
        }
        TrainingPlan plan = new TrainingPlan();
        plan.setTitle(dto.getTitle());
        plan.setDescription(dto.getDescription());
        plan.setStartTime(dto.getStartTime());
        plan.setEndTime(dto.getEndTime());
        plan.setStatus(calcStatus(dto.getStartTime(), dto.getEndTime()));
        planMapper.insert(plan);
        noticeService.autoSendByPlan(plan.getId(), "培训计划已发布: " + plan.getTitle());
        return plan.getId();
    }

    @Override
    public void update(TrainingPlanUpdateDTO dto) {
        if (dto.getEndTime().isBefore(dto.getStartTime())) {
            throw new BizException("结束时间不能早于开始时间");
        }
        if (planMapper.selectById(dto.getId()) == null) {
            throw new BizException("培训计划不存在");
        }
        TrainingPlan plan = new TrainingPlan();
        plan.setId(dto.getId());
        plan.setTitle(dto.getTitle());
        plan.setDescription(dto.getDescription());
        plan.setStartTime(dto.getStartTime());
        plan.setEndTime(dto.getEndTime());
        plan.setStatus(calcStatus(dto.getStartTime(), dto.getEndTime()));
        planMapper.updateById(plan);
    }

    @Override
    public void delete(Long id) {
        if (planMapper.logicDeleteById(id) == 0) {
            throw new BizException("培训计划不存在或已删除");
        }
    }

    @Override
    public TrainingPlan detail(Long id) {
        TrainingPlan plan = planMapper.selectById(id);
        if (plan == null) {
            throw new BizException("培训计划不存在");
        }
        return plan;
    }

    @Override
    public void refreshStatus() {
        planMapper.autoUpdateStatus();
    }

    private int calcStatus(LocalDateTime start, LocalDateTime end) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(start)) {
            return 0;
        }
        if (now.isAfter(end)) {
            return 2;
        }
        return 1;
    }
}
