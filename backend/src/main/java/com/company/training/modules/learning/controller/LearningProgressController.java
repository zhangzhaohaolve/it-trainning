package com.company.training.modules.learning.controller;

import com.company.training.common.page.PageResult;
import com.company.training.common.result.Result;
import com.company.training.modules.learning.dto.*;
import com.company.training.modules.learning.entity.LearningProgress;
import com.company.training.modules.learning.service.LearningProgressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/learning/progress")
@RequiredArgsConstructor
public class LearningProgressController {
    private final LearningProgressService progressService;

    @GetMapping("/list")
    public Result<PageResult<LearningProgress>> list(@Valid LearningProgressQueryDTO queryDTO) { return Result.success(progressService.list(queryDTO)); }
    @PostMapping("/add")
    public Result<Long> add(@RequestBody @Valid LearningProgressAddDTO dto) { return Result.success(progressService.add(dto)); }
    @PutMapping("/update")
    public Result<Void> update(@RequestBody @Valid LearningProgressUpdateDTO dto) { progressService.update(dto); return Result.success(); }
    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) { progressService.delete(id); return Result.success(); }
    @GetMapping("/stats")
    public Result<Map<String, Double>> stats(@RequestParam(required = false) Long courseId, @RequestParam(required = false) Long deptId) {
        return Result.success(progressService.avgStats(courseId, deptId));
    }
}
