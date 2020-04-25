package ru.alexsumin.springcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.alexsumin.springcourse.service.QuizService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		QuizService quizService = context.getBean(QuizService.class);
		quizService.run();
	}

}
