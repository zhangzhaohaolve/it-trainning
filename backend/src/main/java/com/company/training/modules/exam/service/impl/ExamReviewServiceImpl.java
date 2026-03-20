package com.company.training.modules.exam.service.impl;

import com.company.training.modules.exam.dto.ReviewScoreDTO;
import com.company.training.modules.exam.mapper.ExamScoreMapper;
import com.company.training.modules.exam.service.ExamReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamReviewServiceImpl implements ExamReviewService {

    private final ExamScoreMapper examScoreMapper;

    @Override
    public void review(ReviewScoreDTO dto) {
        examScoreMapper.updateReview(dto.getScoreId(), dto.getManualScore(), 1, dto.getReviewer());
    }
}
