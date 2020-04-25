package ru.alexsumin.springcourse.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.alexsumin.springcourse.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Репозиторий для работы с жанрами")
@DataJpaTest
@Import(GenreRepositoryImpl.class)
class GenreRepositoryImplTest {

    @Autowired
    private GenreRepositoryImpl repository;

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
        assertEquals(2, authors.size());
    }

    @DisplayName("Найти жанр по имени")
    @Test
    void findByNameTest() {
        Optional<Genre> fromDb = repository.findByName("Novel");
        assertThat(fromDb)
                .isPresent()
                .matches(genre -> genre.get().getId().equals(2L), "same ids")
                .matches(genre -> genre.get().getName().equals("Novel"), "same titles");
    }
}