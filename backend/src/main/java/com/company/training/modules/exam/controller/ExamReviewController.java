package com.company.training.modules.exam.controller;

import com.company.training.common.result.Result;
import com.company.training.modules.exam.dto.ReviewScoreDTO;
import com.company.training.modules.exam.service.ExamReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam/review")
@RequiredArgsConstructor
public class ExamReviewController {
    private final ExamReviewService reviewService;

    @GetMapping("/list") public Result<String> list(){ return Result.success("请通过成绩管理列表筛选待阅卷记录"); }
    @PostMapping("/add") public Result<Void> add(){ return Result.success(); }
    @PutMapping("/update") public Result<Void> update(@RequestBody @Valid ReviewScoreDTO dto){ reviewService.review(dto); return Result.success(); }
    @DeleteMapping("/delete/{id}") public Result<Void> delete(@PathVariable Long id){ return Result.success(); }
}
