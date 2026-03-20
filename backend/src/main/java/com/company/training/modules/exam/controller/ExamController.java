package com.company.training.modules.exam.controller;

import com.company.training.common.page.PageResult;
import com.company.training.common.result.Result;
import com.company.training.modules.exam.dto.*;
import com.company.training.modules.exam.entity.Exam;
import com.company.training.modules.exam.entity.ExamQuestion;
import com.company.training.modules.exam.service.ExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;

    @GetMapping("/list") public Result<PageResult<Exam>> list(@Valid ExamQueryDTO dto){ return Result.success(examService.list(dto)); }
    @PostMapping("/add") public Result<Long> add(@RequestBody @Valid ExamAddDTO dto){ return Result.success(examService.add(dto)); }
    @PutMapping("/update") public Result<Void> update(@RequestBody @Valid ExamUpdateDTO dto){ examService.update(dto); return Result.success(); }
    @DeleteMapping("/delete/{id}") public Result<Void> delete(@PathVariable Long id){ examService.delete(id); return Result.success(); }
    @PutMapping("/publish/{id}") public Result<Void> publish(@PathVariable Long id){ examService.publish(id); return Result.success(); }
    @PostMapping("/auto-generate") public Result<Void> autoGenerate(@RequestBody @Valid ExamGenerateDTO dto){ examService.autoGenerateExamPaper(dto); return Result.success(); }
    @GetMapping("/detail/{examId}") public Result<List<ExamQuestion>> detail(@PathVariable Long examId){ return Result.success(examService.detailQuestions(examId)); }
    @PostMapping("/submit") public Result<Long> submit(@RequestBody @Valid SubmitExamDTO dto){ return Result.success(examService.submitExam(dto)); }
}
