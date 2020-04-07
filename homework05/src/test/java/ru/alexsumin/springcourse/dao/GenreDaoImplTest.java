package ru.alexsumin.springcourse.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.alexsumin.springcourse.dao.mapper.GenreMapper;
import ru.alexsumin.springcourse.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dao для работы с жанрами")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import({GenreDaoImpl.class, GenreMapper.class})
class GenreDaoImplTest {

    @Autowired
    GenreDao genreDao;

    @DisplayName("Успешно находит по имени")
    @Test
    void findByNameSuccessTest() {
        Optional<Genre> optionalGenre = genreDao.findByName("Novel");
        assertTrue(optionalGenre.isPresent());
    }

    @DisplayName("Не находит по имени")
    @Test
    void findByNameEmptyResultTest() {
        Optional<Genre> optionalGenre = genreDao.findByName("blah-blah");
        assertFalse(optionalGenre.isPresent());
    }

    @DisplayName("Нахождение всех жанров")
    @Test
    void findAllSuccessTest() {
        List<Genre> all = genreDao.findAll();
        assertFalse(all.isEmpty());
    }

    @DisplayName("Сохранение жанра Genre в бд")
    @Test
    void saveSuccessTest() {
        var genre = Genre.builder()
                .name("cool genre")
                .build();
        Genre saved = genreDao.save(genre);
        assertEquals(3L, saved.getId());
    }
}