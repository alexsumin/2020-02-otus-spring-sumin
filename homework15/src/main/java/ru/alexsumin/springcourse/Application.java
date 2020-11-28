package ru.alexsumin.springcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import ru.alexsumin.springcourse.ftp.properties.FTPProperties;

@IntegrationComponentScan
@EnableIntegration
@EnableConfigurationProperties({FTPProperties.class})
@SpringBootApplication(scanBasePackages = "ru.alexsumin.springcourse")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
