package com.company.training.modules.trainingplan.service;

import com.company.training.modules.trainingplan.dto.TrainingNoticeQueryDTO;
import com.company.training.modules.trainingplan.dto.TrainingNoticeReadDTO;
import com.company.training.modules.trainingplan.dto.TrainingNoticeSendDTO;
import com.company.training.modules.trainingplan.entity.TrainingNotice;

import java.util.List;

public interface TrainingNoticeService {
    void autoSendByPlan(Long planId, String content);

    void manualSend(TrainingNoticeSendDTO dto);

    List<TrainingNotice> list(TrainingNoticeQueryDTO queryDTO);

    void setReadStatus(TrainingNoticeReadDTO dto);
}
