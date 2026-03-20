package com.company.training.modules.learning.mapper;

import com.company.training.modules.learning.dto.LearningReminderQueryDTO;
import com.company.training.modules.learning.entity.LearningReminder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LearningReminderMapper {
    int insert(LearningReminder entity);
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    int logicDeleteById(@Param("id") Long id);
    List<LearningReminder> selectPage(LearningReminderQueryDTO queryDTO);
    Long countPage(LearningReminderQueryDTO queryDTO);
}
