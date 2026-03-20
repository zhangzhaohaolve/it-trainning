package com.company.training.modules.learning.controller;

import com.company.training.common.page.PageResult;
import com.company.training.common.result.Result;
import com.company.training.modules.learning.dto.*;
import com.company.training.modules.learning.entity.LearningReminder;
import com.company.training.modules.learning.service.LearningReminderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/learning/reminder")
@RequiredArgsConstructor
public class LearningReminderController {
    private final LearningReminderService reminderService;

    @GetMapping("/list")
    public Result<PageResult<LearningReminder>> list(@Valid LearningReminderQueryDTO queryDTO) { return Result.success(reminderService.list(queryDTO)); }
    @PostMapping("/add")
    public Result<Long> add(@RequestBody @Valid LearningReminderAddDTO dto) { return Result.success(reminderService.add(dto)); }
    @PutMapping("/update")
    public Result<Void> update(@RequestBody @Valid LearningReminderUpdateDTO dto) { reminderService.update(dto); return Result.success(); }
    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) { reminderService.delete(id); return Result.success(); }
}
