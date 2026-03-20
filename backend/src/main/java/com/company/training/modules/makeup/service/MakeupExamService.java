package com.company.training.modules.makeup.service;

import com.company.training.common.page.PageResult;
import com.company.training.modules.makeup.dto.MakeupExamQueryDTO;
import com.company.training.modules.makeup.entity.MakeupExam;

public interface MakeupExamService {
    void simulateAutoGenerate(Long examId, Long userId, Integer score, Integer passScore);
    PageResult<MakeupExam> list(MakeupExamQueryDTO dto);
}
