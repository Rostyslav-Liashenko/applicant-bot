package com.liashenko.applicant.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ExceptionLoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(ExceptionLoggingAspect.class);

    @Pointcut("execution(* com.liashenko.applicant..*(..))")
    public void executionAnyWhere() {}

    @AfterThrowing(value = "executionAnyWhere()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        logger.error("Aspect: Exception in {}.{}() with cause = '{}' and exception = '{}'",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                exception.getCause() != null ? exception.getCause() : "NULL",
                exception.getMessage(),
                exception
        );
    }
}
