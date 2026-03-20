package com.company.training.modules.learning.mapper;

import com.company.training.modules.learning.dto.MyLearningQueryDTO;
import com.company.training.modules.learning.dto.MyLearningVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyLearningMapper {
    List<MyLearningVO> selectMyTasks(MyLearningQueryDTO queryDTO);
}
