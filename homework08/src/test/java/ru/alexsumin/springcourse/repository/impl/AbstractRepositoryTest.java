package ru.alexsumin.springcourse.repository.impl;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.alexsumin.springcourse.config", "ru.alexsumin.springcourse.repository"})
public abstract class AbstractRepositoryTest {
}

