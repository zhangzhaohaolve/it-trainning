package com.company.training.modules.exam.service;

import com.company.training.common.page.PageResult;
import com.company.training.modules.exam.dto.ExamScoreQueryDTO;
import com.company.training.modules.exam.entity.ExamScore;

import java.util.Map;

public interface ExamScoreService {
    PageResult<ExamScore> list(ExamScoreQueryDTO dto);
    Map<String, Number> stats(Long examId);
    byte[] exportExcel(Long examId);
}
