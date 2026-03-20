package com.company.training.modules.exam.service.impl;

import com.company.training.common.exception.BizException;
import com.company.training.common.page.PageResult;
import com.company.training.modules.exam.dto.*;
import com.company.training.modules.exam.entity.Question;
import com.company.training.modules.exam.mapper.QuestionMapper;
import com.company.training.modules.exam.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;

    @Override
    public PageResult<Question> list(QuestionQueryDTO dto) {
        return new PageResult<>(questionMapper.countPage(dto), questionMapper.selectPage(dto));
    }

    @Override
    public Long add(QuestionAddDTO dto) {
        Question q = new Question();
        q.setCourseId(dto.getCourseId()); q.setType(dto.getType()); q.setContent(dto.getContent()); q.setOptions(dto.getOptions()); q.setAnswer(dto.getAnswer()); q.setDifficulty(dto.getDifficulty());
        questionMapper.insert(q);
        return q.getId();
    }

    @Override
    public void update(QuestionUpdateDTO dto) {
        Question q = new Question();
        q.setId(dto.getId()); q.setCourseId(dto.getCourseId()); q.setType(dto.getType()); q.setContent(dto.getContent()); q.setOptions(dto.getOptions()); q.setAnswer(dto.getAnswer()); q.setDifficulty(dto.getDifficulty());
        if (questionMapper.updateById(q) == 0) throw new BizException("题目不存在");
    }

    @Override
    public void delete(Long id) {
        questionMapper.logicDeleteById(id);
    }

    @Override
    public void importQuestionBank(String jsonData) {
        // 可接入批量解析导入逻辑
    }
}
