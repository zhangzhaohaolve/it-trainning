package com.company.training.modules.exam.service;

import com.company.training.common.page.PageResult;
import com.company.training.modules.exam.dto.*;
import com.company.training.modules.exam.entity.Exam;
import com.company.training.modules.exam.entity.ExamQuestion;

import java.util.List;

public interface ExamService {
    PageResult<Exam> list(ExamQueryDTO dto);
    Long add(ExamAddDTO dto);
    void update(ExamUpdateDTO dto);
    void delete(Long id);
    void publish(Long id);
    void autoGenerateExamPaper(ExamGenerateDTO dto);
    List<ExamQuestion> detailQuestions(Long examId);
    Long submitExam(SubmitExamDTO dto);
}
