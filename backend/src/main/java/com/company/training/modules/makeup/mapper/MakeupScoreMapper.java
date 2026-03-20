package com.company.training.modules.makeup.mapper;

import com.company.training.modules.makeup.dto.MakeupScoreQueryDTO;
import com.company.training.modules.makeup.entity.MakeupScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MakeupScoreMapper {
    int insert(MakeupScore s);
    int updateReview(MakeupScore s);
    MakeupScore selectByMakeupAndUser(@Param("makeupId") Long makeupId,@Param("userId") Long userId);
    List<MakeupScore> selectPage(MakeupScoreQueryDTO q);
    Long countPage(MakeupScoreQueryDTO q);
    Integer selectOriginalScore(@Param("examId") Long examId,@Param("userId") Long userId);
}
