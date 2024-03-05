package com.codegym.applicationsandlibraries.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Logger {
    @AfterThrowing(pointcut = "execution(public * com.codegym.applicationsandlibraries.controller.UserController.*(..))",throwing = "e")
    public void show(JoinPoint joinPoint,Exception e){
        String classname = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println(classname);
    }
}
