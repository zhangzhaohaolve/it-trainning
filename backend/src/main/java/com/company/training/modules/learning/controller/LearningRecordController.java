package com.company.training.modules.learning.controller;

import com.company.training.common.page.PageResult;
import com.company.training.common.result.Result;
import com.company.training.modules.learning.dto.*;
import com.company.training.modules.learning.entity.LearningRecord;
import com.company.training.modules.learning.service.LearningRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/learning/record")
@RequiredArgsConstructor
public class LearningRecordController {
    private final LearningRecordService recordService;

    @GetMapping("/list")
    public Result<PageResult<LearningRecord>> list(@Valid LearningRecordQueryDTO queryDTO) { return Result.success(recordService.list(queryDTO)); }
    @PostMapping("/add")
    public Result<Long> add(@RequestBody @Valid LearningRecordAddDTO dto) { return Result.success(recordService.add(dto)); }
    @PutMapping("/update")
    public Result<Void> update(@RequestBody @Valid LearningRecordUpdateDTO dto) { recordService.update(dto); return Result.success(); }
    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) { recordService.delete(id); return Result.success(); }
}
