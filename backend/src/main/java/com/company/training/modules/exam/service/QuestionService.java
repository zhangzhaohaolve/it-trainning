package com.company.training.modules.exam.service;

import com.company.training.common.page.PageResult;
import com.company.training.modules.exam.dto.*;
import com.company.training.modules.exam.entity.Question;

public interface QuestionService {
    PageResult<Question> list(QuestionQueryDTO dto);
    Long add(QuestionAddDTO dto);
    void update(QuestionUpdateDTO dto);
    void delete(Long id);
    void importQuestionBank(String jsonData);
}
