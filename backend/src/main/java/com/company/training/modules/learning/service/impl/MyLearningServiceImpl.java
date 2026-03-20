package com.company.training.modules.learning.service.impl;

import com.company.training.modules.learning.dto.MyLearningQueryDTO;
import com.company.training.modules.learning.dto.MyLearningVO;
import com.company.training.modules.learning.mapper.MyLearningMapper;
import com.company.training.modules.learning.service.MyLearningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyLearningServiceImpl implements MyLearningService {
    private final MyLearningMapper myLearningMapper;

    @Override
    public List<MyLearningVO> list(MyLearningQueryDTO queryDTO) {
        return myLearningMapper.selectMyTasks(queryDTO);
    }
}
