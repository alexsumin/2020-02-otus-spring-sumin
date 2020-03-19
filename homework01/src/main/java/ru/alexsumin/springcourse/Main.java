package ru.alexsumin.springcourse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.alexsumin.springcourse.service.QuizServiceImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizServiceImpl quizService = context.getBean(QuizServiceImpl.class);
        quizService.run();
    }
}