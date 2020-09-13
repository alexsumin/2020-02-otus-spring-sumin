package ru.alexsumin.springcourse.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.repository.GenreRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
@DisplayName("Репозиторий для работы с жанрами")
@DataJpaTest
class GenreRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private GenreRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @DisplayName("Сохранение жанра")
    @Test
    void saveTest() {
        Genre toSave = Genre.builder()
                .name("new cool genre")
                .build();

        var saved = repository.save(toSave);
        var fromDb = entityManager.find(Genre.class, saved.getId());
        assertEquals(saved, fromDb);
    }

    @DisplayName("Поиск всех жанров")
    @Test
    void findAllTest() {
        val authors = repository.findAll();
        assertFalse(authors.isEmpty());
    }

    @DisplayName("Найти жанр по имени")
    @Test
    void findByNameTest() {
        Optional<Genre> fromDb = repository.findByName("Novel");
        assertThat(fromDb)
                .isPresent()
                .matches(genre -> genre.get().getName().equals("Novel"), "same titles");
    }
}