package com.company.training.modules.trainingplan.controller;

import com.company.training.common.page.PageResult;
import com.company.training.common.result.Result;
import com.company.training.modules.trainingplan.dto.TrainingPlanAddDTO;
import com.company.training.modules.trainingplan.dto.TrainingPlanQueryDTO;
import com.company.training.modules.trainingplan.dto.TrainingPlanUpdateDTO;
import com.company.training.modules.trainingplan.entity.TrainingPlan;
import com.company.training.modules.trainingplan.service.TrainingPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/training-plan")
@RequiredArgsConstructor
public class TrainingPlanController {

    private final TrainingPlanService trainingPlanService;

    @GetMapping("/page")
    public Result<PageResult<TrainingPlan>> page(@Valid TrainingPlanQueryDTO queryDTO) {
        return Result.success(trainingPlanService.page(queryDTO));
    }

    @GetMapping("/{id}")
    public Result<TrainingPlan> detail(@PathVariable Long id) {
        return Result.success(trainingPlanService.detail(id));
    }

    @PostMapping
    public Result<Long> add(@RequestBody @Valid TrainingPlanAddDTO dto) {
        return Result.success(trainingPlanService.add(dto));
    }

    @PutMapping
    public Result<Void> update(@RequestBody @Valid TrainingPlanUpdateDTO dto) {
        trainingPlanService.update(dto);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        trainingPlanService.delete(id);
        return Result.success();
    }
}
