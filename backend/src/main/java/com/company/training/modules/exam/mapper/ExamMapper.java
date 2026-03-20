package com.company.training.modules.exam.mapper;

import com.company.training.modules.exam.dto.ExamQueryDTO;
import com.company.training.modules.exam.entity.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExamMapper {
    int insert(Exam e);
    int updateById(Exam e);
    int logicDeleteById(@Param("id") Long id);
    Exam selectById(@Param("id") Long id);
    List<Exam> selectPage(ExamQueryDTO q);
    Long countPage(ExamQueryDTO q);
    int publish(@Param("id") Long id);
}
