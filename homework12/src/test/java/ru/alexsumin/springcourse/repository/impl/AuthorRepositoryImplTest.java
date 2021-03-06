package ru.alexsumin.springcourse.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.repository.AuthorRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с авторами")
@DataJpaTest
class AuthorRepositoryImplTest {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @DisplayName("Сохранение автора")
    @Test
    void saveTest() {
        Author toSave = Author.builder()
                .name("new cool author")
                .build();

        var saved = repository.save(toSave);
        var fromDb = entityManager.find(Author.class, saved.getId());
        assertEquals(saved, fromDb);
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
        Optional<Author> fromDb = repository.findByName("Bruce Eckel");
        assertTrue(fromDb.isPresent());
        assertEquals(1L, fromDb.get().getId());
        assertEquals("Bruce Eckel", fromDb.get().getName());
    }
}