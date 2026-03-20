package com.company.training.modules.makeup.service.impl;

import com.company.training.common.page.PageResult;
import com.company.training.modules.makeup.dto.MakeupExamQueryDTO;
import com.company.training.modules.makeup.entity.MakeupExam;
import com.company.training.modules.makeup.mapper.MakeupExamMapper;
import com.company.training.modules.makeup.service.MakeupExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MakeupExamServiceImpl implements MakeupExamService {

    private final MakeupExamMapper makeupExamMapper;

    @Override
    public void simulateAutoGenerate(Long examId, Long userId, Integer score, Integer passScore) {
        if (score != null && passScore != null && score < passScore) {
            MakeupExam me = new MakeupExam();
            me.setExamId(examId);
            me.setUserId(userId);
            me.setReason("考试不及格");
            me.setStatus(0);
            makeupExamMapper.insert(me);
        }
    }

    @Override
    public PageResult<MakeupExam> list(MakeupExamQueryDTO dto) {
        return new PageResult<>(makeupExamMapper.countPage(dto), makeupExamMapper.selectPage(dto));
    }
}
