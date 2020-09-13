package ru.alexsumin.springcourse.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.repository.AuthorRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с авторами")
@DataMongoTest
class AuthorRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @DisplayName("Сохранение автора")
    @Test
    void saveTest() {
        String testName = "new cool author";
        Author toSave = Author.builder()
                .name(testName)
                .build();

        var saved = repository.save(toSave);

        Optional<Author> fromDb = repository.findByName(testName);
        assertTrue(fromDb.isPresent());
        assertEquals(saved, fromDb.get());
    }

    @DisplayName("Поиск всех авторов")
    @Test
    void findAllTest() {
        val authors = repository.findAll();
        assertFalse(authors.isEmpty());
    }

    @DisplayName("Поиск автора по имени")
    @Test
    void findByNameTest() {
        String bruceEckel = "Bruce Eckel";
        Optional<Author> fromDb = repository.findByName(bruceEckel);
        assertTrue(fromDb.isPresent());
        assertEquals(bruceEckel, fromDb.get().getName());
    }
}