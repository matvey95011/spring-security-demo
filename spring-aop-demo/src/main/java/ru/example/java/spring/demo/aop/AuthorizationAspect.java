package ru.example.java.spring.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAspect {

    @Pointcut("@annotation(Authorization) && args(String, String)")
    public void auth() {
        // todo:
    }

}
