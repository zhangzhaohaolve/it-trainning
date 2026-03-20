package com.company.training.modules.makeup.controller;

import com.company.training.common.result.Result;
import com.company.training.modules.makeup.dto.MakeupScheduleAddDTO;
import com.company.training.modules.makeup.dto.MakeupScheduleUpdateDTO;
import com.company.training.modules.makeup.entity.MakeupSchedule;
import com.company.training.modules.makeup.service.MakeupScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/makeup/schedule")
@RequiredArgsConstructor
public class MakeupScheduleController {

    private final MakeupScheduleService makeupScheduleService;

    @PostMapping("/add")
    public Result<Long> add(@RequestBody @Valid MakeupScheduleAddDTO dto) { return Result.success(makeupScheduleService.add(dto)); }
    @PutMapping("/update")
    public Result<Void> update(@RequestBody @Valid MakeupScheduleUpdateDTO dto) { makeupScheduleService.update(dto); return Result.success(); }
    @GetMapping("/list")
    public Result<List<MakeupSchedule>> list() { return Result.success(makeupScheduleService.calendar()); }
    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) { return Result.success(); }
}
