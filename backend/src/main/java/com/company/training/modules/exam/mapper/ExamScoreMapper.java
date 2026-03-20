package com.company.training.modules.exam.mapper;

import com.company.training.modules.exam.dto.ExamScoreQueryDTO;
import com.company.training.modules.exam.entity.ExamScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExamScoreMapper {
    int insert(ExamScore s);
    int updateReview(@Param("id") Long id,@Param("score") Integer score,@Param("status") Integer status,@Param("reviewer") Long reviewer);
    ExamScore selectByExamAndUser(@Param("examId") Long examId,@Param("userId") Long userId);
    List<ExamScore> selectPage(ExamScoreQueryDTO q);
    Long countPage(ExamScoreQueryDTO q);
    Double avg(@Param("examId") Long examId);
    Integer max(@Param("examId") Long examId);
    Integer min(@Param("examId") Long examId);
}
