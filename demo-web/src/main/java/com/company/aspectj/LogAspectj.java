package com.company.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
 public class LogAspectj {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspectj.class);

    @AfterReturning(value = "@annotation(com.company.aspectj.Log)",returning = "ret")
    public void LogAfterReturning(JoinPoint joinPoint,Object ret){
        Object[] args = joinPoint.getArgs();
        LOGGER.info("方法入参：{}",args);
        LOGGER.info("返回值：{}",ret);
    }
}
