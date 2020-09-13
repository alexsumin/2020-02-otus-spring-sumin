package ru.alexsumin.springcourse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.alexsumin.springcourse.service.InteractorService;
import ru.alexsumin.springcourse.service.impl.InteractorServiceImpl;

@Configuration
public class AppConfig {
    @Bean
    public InteractorService interactorService() {
        return new InteractorServiceImpl(System.in, System.out);
    }
}
