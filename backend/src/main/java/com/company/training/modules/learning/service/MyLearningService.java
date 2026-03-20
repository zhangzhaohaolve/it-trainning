package com.company.training.modules.learning.service;

import com.company.training.modules.learning.dto.MyLearningQueryDTO;
import com.company.training.modules.learning.dto.MyLearningVO;

import java.util.List;

public interface MyLearningService {
    List<MyLearningVO> list(MyLearningQueryDTO queryDTO);
}
