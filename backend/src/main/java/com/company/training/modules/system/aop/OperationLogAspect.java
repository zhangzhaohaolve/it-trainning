package com.company.training.modules.system.aop;

import com.company.training.modules.system.entity.SysLog;
import com.company.training.modules.system.mapper.SysLogMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class OperationLogAspect {

    private final SysLogMapper sysLogMapper;
    private final HttpServletRequest request;

    @AfterReturning("execution(* com.company.training.modules..controller..*(..))")
    public void log(JoinPoint joinPoint) {
        SysLog log = new SysLog();
        log.setAction(joinPoint.getSignature().toShortString());
        log.setMethod(request.getMethod());
        log.setParams(Arrays.toString(joinPoint.getArgs()));
        log.setIp(request.getRemoteAddr());
        log.setUserId(0L);
        sysLogMapper.insert(log);
    }
}
