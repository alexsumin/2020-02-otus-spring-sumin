package ru.alexsumin.springcourse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.alexsumin.springcourse.config.AppConfig;
import ru.alexsumin.springcourse.service.QuizService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        QuizService quizService = context.getBean(QuizService.class);
        quizService.run();
    }
}