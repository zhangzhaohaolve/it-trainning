package com.company.training.modules.trainingplan.service.impl;

import com.company.training.common.exception.BizException;
import com.company.training.modules.trainingplan.dto.TrainingAssignBatchDTO;
import com.company.training.modules.trainingplan.dto.TrainingAssignDeptDTO;
import com.company.training.modules.trainingplan.dto.TrainingAssignSingleDTO;
import com.company.training.modules.trainingplan.entity.TrainingPlanAssign;
import com.company.training.modules.trainingplan.mapper.TrainingPlanAssignMapper;
import com.company.training.modules.trainingplan.mapper.TrainingPlanMapper;
import com.company.training.modules.trainingplan.mapper.UserLiteMapper;
import com.company.training.modules.trainingplan.service.TrainingPlanAssignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingPlanAssignServiceImpl implements TrainingPlanAssignService {

    private final TrainingPlanAssignMapper assignMapper;
    private final TrainingPlanMapper planMapper;
    private final UserLiteMapper userLiteMapper;

    @Override
    public void assignBatch(TrainingAssignBatchDTO dto) {
        if (planMapper.selectById(dto.getPlanId()) == null) {
            throw new BizException("培训计划不存在");
        }
        for (Long userId : dto.getUserIds()) {
            saveIfAbsent(dto.getPlanId(), userId);
        }
    }

    @Override
    public void assignSingle(TrainingAssignSingleDTO dto) {
        if (planMapper.selectById(dto.getPlanId()) == null) {
            throw new BizException("培训计划不存在");
        }
        saveIfAbsent(dto.getPlanId(), dto.getUserId());
    }

    @Override
    public void assignByDept(TrainingAssignDeptDTO dto) {
        if (planMapper.selectById(dto.getPlanId()) == null) {
            throw new BizException("培训计划不存在");
        }
        List<Long> userIds = userLiteMapper.selectUserIdsByDeptId(dto.getDeptId());
        for (Long userId : userIds) {
            saveIfAbsent(dto.getPlanId(), userId);
        }
    }

    @Override
    public List<TrainingPlanAssign> listByPlanId(Long planId) {
        return assignMapper.selectByPlanId(planId);
    }

    @Override
    public void delete(Long id) {
        TrainingPlanAssign assign = assignMapper.selectById(id);
        if (assign == null) {
            throw new BizException("分配记录不存在");
        }
        if (assign.getStatus() != null && assign.getStatus() == 1) {
            throw new BizException("任务已完成，禁止删除");
        }
        assignMapper.logicDeleteById(id);
    }

    private void saveIfAbsent(Long planId, Long userId) {
        if (assignMapper.exists(planId, userId) > 0) {
            return;
        }
        TrainingPlanAssign assign = new TrainingPlanAssign();
        assign.setPlanId(planId);
        assign.setUserId(userId);
        assign.setStatus(0);
        assignMapper.insert(assign);
    }
}
