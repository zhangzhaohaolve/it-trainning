package com.company.training.modules.trainingplan.service.impl;

import com.company.training.modules.trainingplan.dto.TrainingNoticeQueryDTO;
import com.company.training.modules.trainingplan.dto.TrainingNoticeReadDTO;
import com.company.training.modules.trainingplan.dto.TrainingNoticeSendDTO;
import com.company.training.modules.trainingplan.entity.TrainingNotice;
import com.company.training.modules.trainingplan.mapper.TrainingNoticeMapper;
import com.company.training.modules.trainingplan.mapper.TrainingPlanAssignMapper;
import com.company.training.modules.trainingplan.service.TrainingNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingNoticeServiceImpl implements TrainingNoticeService {

    private final TrainingNoticeMapper noticeMapper;
    private final TrainingPlanAssignMapper assignMapper;

    @Override
    public void autoSendByPlan(Long planId, String content) {
        List<Long> userIds = assignMapper.selectAssignedUserIds(planId);
        for (Long userId : userIds) {
            TrainingNotice n = new TrainingNotice();
            n.setPlanId(planId);
            n.setUserId(userId);
            n.setType(0);
            n.setContent(content);
            n.setStatus(0);
            noticeMapper.insert(n);
        }
    }

    @Override
    public void manualSend(TrainingNoticeSendDTO dto) {
        TrainingNotice n = new TrainingNotice();
        n.setPlanId(dto.getPlanId());
        n.setUserId(dto.getUserId());
        n.setType(1);
        n.setContent(dto.getContent());
        n.setStatus(0);
        noticeMapper.insert(n);
    }

    @Override
    public List<TrainingNotice> list(TrainingNoticeQueryDTO queryDTO) {
        return noticeMapper.selectList(queryDTO);
    }

    @Override
    public void setReadStatus(TrainingNoticeReadDTO dto) {
        noticeMapper.updateReadStatus(dto.getId(), dto.getStatus());
    }
}
