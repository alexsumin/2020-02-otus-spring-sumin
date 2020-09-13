package ru.alexsumin.springcourse.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.repository.BookRepository;
import ru.alexsumin.springcourse.util.ProjectUtil;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с книгами")
@DataMongoTest
class BookRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    BookRepository repository;

    @DisplayName("Сохранение книги")
    @Test
    void saveTest() {
        Book toSave = Book.builder()
                .title("some title")
                .published(ProjectUtil.toDate("2006-09-13"))
                .build();
        var saved = repository.save(toSave);
        var fromDb = repository.findById(saved.getId());
        assertTrue(fromDb.isPresent());
        assertEquals(saved, fromDb.get());
    }

    @DisplayName("Нахождение всех книг")
    @Test
    void findAllTest() {
        val books = repository.findAll();
        assertFalse(books.isEmpty());
    }

    @DisplayName("Удаление по id")
    @Test
    void deleteByIdTest() {
        Book toSave = Book.builder()
                .title("some title")
                .published(ProjectUtil.toDate("2006-09-13"))
                .build();
        var saved = repository.save(toSave);
        repository.delete(saved);
        Optional<Book> shouldEmpty = repository.findById(saved.getId());
        assertTrue(shouldEmpty.isEmpty());
    }
}