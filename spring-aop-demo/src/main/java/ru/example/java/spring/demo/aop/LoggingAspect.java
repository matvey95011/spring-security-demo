package ru.example.java.spring.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* ru.example.java.spring.demo.*.*(..)) && args()")
    public void logBefore(JoinPoint joinPoint) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.info("Started method: {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* ru.example.java.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.info("Finished method: {}, Result: {}", joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(value = "execution(* ru.example.java.*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.warn("Error in method: {}, Message: {}", joinPoint.getSignature().getName(), ex.getMessage());
    }
}
