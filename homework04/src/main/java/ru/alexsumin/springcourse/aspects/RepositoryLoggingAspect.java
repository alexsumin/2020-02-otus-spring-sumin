package ru.alexsumin.springcourse.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class RepositoryLoggingAspect {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* ru.alexsumin.springcourse.repository.QuestionRepository.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Вызов метода : " + joinPoint.getSignature().getName());
        log.info("С аргументами : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* ru.alexsumin.springcourse.repository.QuestionRepository.*(..))",
            returning="retVal")
    public void logAfter(Object retVal) {
        log.info("Метод вернул : " + retVal);
    }
}
