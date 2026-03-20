package com.company.training.modules.makeup.service.impl;

import com.company.training.common.exception.BizException;
import com.company.training.common.page.PageResult;
import com.company.training.modules.makeup.dto.*;
import com.company.training.modules.makeup.entity.MakeupExam;
import com.company.training.modules.makeup.entity.MakeupScore;
import com.company.training.modules.makeup.mapper.MakeupExamMapper;
import com.company.training.modules.makeup.mapper.MakeupScoreMapper;
import com.company.training.modules.makeup.service.MakeupScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MakeupScoreServiceImpl implements MakeupScoreService {

    private final MakeupScoreMapper scoreMapper;
    private final MakeupExamMapper examMapper;

    @Override
    public Long submit(MakeupScoreSubmitDTO dto) {
        if (scoreMapper.selectByMakeupAndUser(dto.getMakeupId(), dto.getUserId()) != null) throw new BizException("不可重复提交补考");
        MakeupScore s = new MakeupScore();
        s.setMakeupId(dto.getMakeupId()); s.setUserId(dto.getUserId()); s.setAnswers(dto.getAnswers()); s.setScore(0); s.setStatus(0);
        scoreMapper.insert(s);
        return s.getId();
    }

    @Override
    public void review(MakeupScoreReviewDTO dto) {
        MakeupScore s = new MakeupScore();
        s.setId(dto.getId()); s.setScore(dto.getScore()); s.setStatus(1); s.setReviewer(dto.getReviewer());
        scoreMapper.updateReview(s);
        // 补考完成后更新任务状态
        for (MakeupScore ms : scoreMapper.selectPage(new MakeupScoreQueryDTO() {{ setPageNum(1); setPageSize(1); }})) {
            if (ms.getId().equals(dto.getId())) {
                examMapper.updateStatus(ms.getMakeupId(), 1);
                break;
            }
        }
    }

    @Override
    public PageResult<MakeupScore> list(MakeupScoreQueryDTO dto) {
        return new PageResult<>(scoreMapper.countPage(dto), scoreMapper.selectPage(dto));
    }

    @Override
    public Map<String, Object> compareWithOriginal(Long makeupId, Long userId) {
        MakeupExam me = examMapper.selectById(makeupId);
        if (me == null) throw new BizException("补考任务不存在");
        Integer original = scoreMapper.selectOriginalScore(me.getExamId(), userId);
        MakeupScore latest = scoreMapper.selectByMakeupAndUser(makeupId, userId);
        Integer makeup = latest == null ? null : latest.getScore();
        String result = "unchanged";
        if (original != null && makeup != null && makeup > original) result = "improve";
        Map<String, Object> map = new HashMap<>();
        map.put("originalScore", original); map.put("makeupScore", makeup); map.put("compareResult", result);
        return map;
    }
}
