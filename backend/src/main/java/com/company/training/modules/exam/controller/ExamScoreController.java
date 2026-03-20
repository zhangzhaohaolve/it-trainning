package com.company.training.modules.exam.controller;

import com.company.training.common.page.PageResult;
import com.company.training.common.result.Result;
import com.company.training.modules.exam.dto.ExamScoreQueryDTO;
import com.company.training.modules.exam.entity.ExamScore;
import com.company.training.modules.exam.service.ExamScoreService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/exam/score")
@RequiredArgsConstructor
public class ExamScoreController {
    private final ExamScoreService scoreService;

    @GetMapping("/list") public Result<PageResult<ExamScore>> list(@Valid ExamScoreQueryDTO dto){ return Result.success(scoreService.list(dto)); }
    @PostMapping("/add") public Result<Void> add(){ return Result.success(); }
    @PutMapping("/update") public Result<Void> update(){ return Result.success(); }
    @DeleteMapping("/delete/{id}") public Result<Void> delete(@PathVariable Long id){ return Result.success(); }
    @GetMapping("/stats") public Result<Map<String, Number>> stats(@RequestParam Long examId){ return Result.success(scoreService.stats(examId)); }
    @GetMapping("/export")
    public void export(@RequestParam Long examId, HttpServletResponse response) throws IOException {
        byte[] bytes = scoreService.exportExcel(examId);
        response.setContentType("text/csv;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=exam-score.csv");
        response.getOutputStream().write(bytes);
    }
}
