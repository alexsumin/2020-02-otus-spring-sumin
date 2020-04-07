package ru.alexsumin.springcourse.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.alexsumin.springcourse.dao.mapper.AuthorMapper;
import ru.alexsumin.springcourse.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Dao для работы с авторами")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import({AuthorDaoImpl.class, AuthorMapper.class})
class AuthorDaoImplTest {
    @Autowired
    AuthorDao authorDao;

    @DisplayName("Успешно находит по имени")
    @Test
    void findByNameSuccessTest() {
        Optional<Author> optionalAuthor = authorDao.findByName("Jonathan Littell");
        assertTrue(optionalAuthor.isPresent());
    }

    @DisplayName("Не находит по имени")
    @Test
    void findByNameEmptyResultTest() {
        Optional<Author> optionalBook = authorDao.findByName("The man in the suit");
        assertFalse(optionalBook.isPresent());
    }

    @DisplayName("Нахождение всех авторов")
    @Test
    void findAllSuccessTest() {
        List<Author> all = authorDao.findAll();
        assertFalse(all.isEmpty());
    }

    @DisplayName("Сохранение автора Author в бд")
    @Test
    void saveSuccessTest() {
        var genre = Author.builder()
                .name("cool author")
                .build();
        Author saved = authorDao.save(genre);
        assertEquals(4L, saved.getId());
    }
}