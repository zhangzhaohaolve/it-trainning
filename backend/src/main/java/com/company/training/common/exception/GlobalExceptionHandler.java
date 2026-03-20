package com.company.training.common.exception;

import com.company.training.common.result.Result;
import jakarta.validation.ConstraintViolationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public Result<Void> handleBiz(BizException ex) {
        return Result.fail(ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class, ConstraintViolationException.class})
    public Result<Void> handleValidation(Exception ex) {
        return Result.fail("参数校验失败: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception ex) {
        return Result.fail("系统异常: " + ex.getMessage());
    }
}
