package com.company.training.modules.makeup.controller;

import com.company.training.common.page.PageResult;
import com.company.training.common.result.Result;
import com.company.training.modules.makeup.dto.*;
import com.company.training.modules.makeup.entity.MakeupScore;
import com.company.training.modules.makeup.service.MakeupScoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/makeup/score")
@RequiredArgsConstructor
public class MakeupScoreController {

    private final MakeupScoreService makeupScoreService;

    @PostMapping("/add") public Result<Long> submit(@RequestBody @Valid MakeupScoreSubmitDTO dto){ return Result.success(makeupScoreService.submit(dto)); }
    @PutMapping("/update") public Result<Void> review(@RequestBody @Valid MakeupScoreReviewDTO dto){ makeupScoreService.review(dto); return Result.success(); }
    @GetMapping("/list") public Result<PageResult<MakeupScore>> list(@Valid MakeupScoreQueryDTO dto){ return Result.success(makeupScoreService.list(dto)); }
    @GetMapping("/compare") public Result<Map<String,Object>> compare(@RequestParam Long makeupId,@RequestParam Long userId){ return Result.success(makeupScoreService.compareWithOriginal(makeupId,userId)); }
    @DeleteMapping("/delete/{id}") public Result<Void> delete(@PathVariable Long id){ return Result.success(); }
}
