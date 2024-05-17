package com.example.demo.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.demo.service.*.*(..))")
    private void serviceLayerExecution() {}

    @Before("serviceLayerExecution()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {}", joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(pointcut = "serviceLayerExecution()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error("Exception in method: {}", joinPoint.getSignature().toShortString());
        logger.error("Exception message: {}", ex.getMessage());
    }

}
