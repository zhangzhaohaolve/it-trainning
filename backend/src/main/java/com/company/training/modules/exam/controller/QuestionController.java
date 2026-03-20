package com.company.training.modules.exam.controller;

import com.company.training.common.page.PageResult;
import com.company.training.common.result.Result;
import com.company.training.modules.exam.dto.*;
import com.company.training.modules.exam.entity.Question;
import com.company.training.modules.exam.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list") public Result<PageResult<Question>> list(@Valid QuestionQueryDTO dto){ return Result.success(questionService.list(dto)); }
    @PostMapping("/add") public Result<Long> add(@RequestBody @Valid QuestionAddDTO dto){ return Result.success(questionService.add(dto)); }
    @PutMapping("/update") public Result<Void> update(@RequestBody @Valid QuestionUpdateDTO dto){ questionService.update(dto); return Result.success(); }
    @DeleteMapping("/delete/{id}") public Result<Void> delete(@PathVariable Long id){ questionService.delete(id); return Result.success(); }
    @PostMapping("/import") public Result<Void> importBank(@RequestBody String jsonData){ questionService.importQuestionBank(jsonData); return Result.success(); }
}
