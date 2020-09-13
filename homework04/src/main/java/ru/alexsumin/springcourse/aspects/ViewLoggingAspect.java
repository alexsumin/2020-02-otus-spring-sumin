package ru.alexsumin.springcourse.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ViewLoggingAspect {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* ru.alexsumin.springcourse.view.View.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Вызов метода : " + joinPoint.getSignature().getName());
    }
}
