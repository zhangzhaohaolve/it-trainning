package com.company.training.modules.learning.controller;

import com.company.training.common.result.Result;
import com.company.training.modules.learning.dto.MyLearningQueryDTO;
import com.company.training.modules.learning.dto.MyLearningVO;
import com.company.training.modules.learning.service.MyLearningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/learning/my")
@RequiredArgsConstructor
public class MyLearningController {
    private final MyLearningService myLearningService;

    @GetMapping("/list")
    public Result<List<MyLearningVO>> list(@Valid MyLearningQueryDTO queryDTO) {
        return Result.success(myLearningService.list(queryDTO));
    }
}
