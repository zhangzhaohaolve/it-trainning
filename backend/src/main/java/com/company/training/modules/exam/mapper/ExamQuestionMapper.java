package com.company.training.modules.exam.mapper;

import com.company.training.modules.exam.entity.ExamQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExamQuestionMapper {
    int insert(ExamQuestion eq);
    int deleteByExamId(@Param("examId") Long examId);
    List<ExamQuestion> selectByExamId(@Param("examId") Long examId);
}
