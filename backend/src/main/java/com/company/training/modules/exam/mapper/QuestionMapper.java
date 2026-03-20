package com.company.training.modules.exam.mapper;

import com.company.training.modules.exam.dto.QuestionQueryDTO;
import com.company.training.modules.exam.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {
    int insert(Question q);
    int updateById(Question q);
    int logicDeleteById(@Param("id") Long id);
    Question selectById(@Param("id") Long id);
    List<Question> selectPage(QuestionQueryDTO q);
    Long countPage(QuestionQueryDTO q);
    List<Question> randomByTypeAndDifficulty(@Param("courseId") Long courseId,@Param("type") Integer type,@Param("difficulty") Integer difficulty,@Param("limit") Integer limit);
}
