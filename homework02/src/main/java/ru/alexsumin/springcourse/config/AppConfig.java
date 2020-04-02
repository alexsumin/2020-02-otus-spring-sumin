package ru.alexsumin.springcourse.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.alexsumin.springcourse.service.InteractorService;
import ru.alexsumin.springcourse.service.InteractorServiceImpl;

@Configuration
@ComponentScan("ru.alexsumin.springcourse.*")
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    public InteractorService interactorService() {
        return new InteractorServiceImpl(System.in, System.out);
    }

    @Bean
    public MessageSource messageSource() {
        var ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/l10n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
