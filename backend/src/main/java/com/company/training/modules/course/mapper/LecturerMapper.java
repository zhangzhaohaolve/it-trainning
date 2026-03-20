package com.company.training.modules.course.mapper;

import com.company.training.modules.course.dto.LecturerQueryDTO;
import com.company.training.modules.course.entity.Lecturer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LecturerMapper {
    List<Lecturer> selectList(LecturerQueryDTO queryDTO);

    int insert(Lecturer entity);

    int updateById(Lecturer entity);

    Lecturer selectById(@Param("id") Long id);

    int logicDeleteById(@Param("id") Long id);
}
