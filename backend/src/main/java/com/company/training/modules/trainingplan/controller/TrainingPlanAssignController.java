package com.company.training.modules.trainingplan.controller;

import com.company.training.common.result.Result;
import com.company.training.modules.trainingplan.dto.TrainingAssignBatchDTO;
import com.company.training.modules.trainingplan.dto.TrainingAssignDeptDTO;
import com.company.training.modules.trainingplan.dto.TrainingAssignSingleDTO;
import com.company.training.modules.trainingplan.entity.TrainingPlanAssign;
import com.company.training.modules.trainingplan.service.TrainingPlanAssignService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training-plan/assign")
@RequiredArgsConstructor
public class TrainingPlanAssignController {

    private final TrainingPlanAssignService assignService;

    @PostMapping("/batch")
    public Result<Void> assignBatch(@RequestBody @Valid TrainingAssignBatchDTO dto) {
        assignService.assignBatch(dto);
        return Result.success();
    }

    @PostMapping("/single")
    public Result<Void> assignSingle(@RequestBody @Valid TrainingAssignSingleDTO dto) {
        assignService.assignSingle(dto);
        return Result.success();
    }

    @PostMapping("/dept")
    public Result<Void> assignByDept(@RequestBody @Valid TrainingAssignDeptDTO dto) {
        assignService.assignByDept(dto);
        return Result.success();
    }

    @GetMapping("/list/{planId}")
    public Result<List<TrainingPlanAssign>> listByPlanId(@PathVariable Long planId) {
        return Result.success(assignService.listByPlanId(planId));
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        assignService.delete(id);
        return Result.success();
    }
}
