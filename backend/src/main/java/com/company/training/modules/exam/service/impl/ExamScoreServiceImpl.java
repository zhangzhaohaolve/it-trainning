package com.company.training.modules.exam.service.impl;

import com.company.training.common.page.PageResult;
import com.company.training.modules.exam.dto.ExamScoreQueryDTO;
import com.company.training.modules.exam.entity.ExamScore;
import com.company.training.modules.exam.mapper.ExamScoreMapper;
import com.company.training.modules.exam.service.ExamScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExamScoreServiceImpl implements ExamScoreService {

    private final ExamScoreMapper scoreMapper;

    @Override
    public PageResult<ExamScore> list(ExamScoreQueryDTO dto) {
        return new PageResult<>(scoreMapper.countPage(dto), scoreMapper.selectPage(dto));
    }

    @Override
    public Map<String, Number> stats(Long examId) {
        Map<String, Number> map = new HashMap<>();
        map.put("avg", scoreMapper.avg(examId));
        map.put("max", scoreMapper.max(examId));
        map.put("min", scoreMapper.min(examId));
        return map;
    }

    @Override
    public byte[] exportExcel(Long examId) {
        // 示例导出CSV，生产可替换为Apache POI Excel
        String csv = "exam_id,user_id,score\n";
        for (ExamScore s : scoreMapper.selectPage(new ExamScoreQueryDTO() {{ setExamId(examId); setPageNum(1); setPageSize(10000); }})) {
            csv += s.getExamId() + "," + s.getUserId() + "," + s.getScore() + "\n";
        }
        return csv.getBytes(StandardCharsets.UTF_8);
    }
}
