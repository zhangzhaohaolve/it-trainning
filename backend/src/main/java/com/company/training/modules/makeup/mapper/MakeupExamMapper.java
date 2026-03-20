package com.company.training.modules.makeup.mapper;

import com.company.training.modules.makeup.dto.MakeupExamQueryDTO;
import com.company.training.modules.makeup.entity.MakeupExam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MakeupExamMapper {
    int insert(MakeupExam e);
    List<MakeupExam> selectPage(MakeupExamQueryDTO q);
    Long countPage(MakeupExamQueryDTO q);
    MakeupExam selectById(@Param("id") Long id);
    int updateStatus(@Param("id") Long id,@Param("status") Integer status);
}
