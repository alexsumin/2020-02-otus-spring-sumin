package ru.alexsumin.springcourse.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.util.ProjectUtil;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dao для работы с книгами")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(BookDaoImpl.class)
@ComponentScan
class BookDaoImplTest {
    private static final long EXPECTED_ID = 5L;

    @Autowired
    BookDao bookDao;

    @DisplayName("Успешно находит по id")
    @Test
    void findByIdSuccessTest() {
        var id = 1L;
        Optional<Book> optionalBook = bookDao.findById(id);
        assertTrue(optionalBook.isPresent());
        var book = optionalBook.get();
        assertEquals(id, book.getId());
    }

    @DisplayName("Не находит по id")
    @Test
    void findByIdEmptyResultTest() {
        Optional<Book> optionalBook = bookDao.findById(42L);
        assertFalse(optionalBook.isPresent());
    }

    @DisplayName("Сохранение книги Book в бд")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void saveSuccessTest() {
        Book testBook = Book.builder()
                .title("Some title")
                .published(new Date(System.currentTimeMillis()))
                .build();
        Book savedBook = bookDao.save(testBook);
        assertEquals(EXPECTED_ID, savedBook.getId());
    }

    @DisplayName("Сохранение книги Book со связанным автором Author")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void saveSuccessTestWithAuthor() {
        Book testBook = Book.builder()
                .title("Some title")
                .published(new Date(System.currentTimeMillis()))
                .author(testAuthor())
                .build();
        Book savedBook = bookDao.save(testBook);
        assertEquals(EXPECTED_ID, savedBook.getId());
    }

    @DisplayName("Сохранение книги Book со связанным жанром Genre")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void saveSuccessTestWithGenre() {
        Book testBook = Book.builder()
                .title("Some title")
                .published(new Date(System.currentTimeMillis()))
                .genre(testGenre())
                .build();
        Book savedBook = bookDao.save(testBook);
        assertEquals(EXPECTED_ID, savedBook.getId());
    }

    @DisplayName("Сохранение Book со связанными жанром Genre и автором Author")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void saveSuccessTestWithGenreAndAuthor() {
        Book testBook = Book.builder()
                .title("Some title")
                .published(new Date(System.currentTimeMillis()))
                .genre(testGenre())
                .author(testAuthor())
                .build();
        Book savedBook = bookDao.save(testBook);
        assertEquals(EXPECTED_ID, savedBook.getId());
    }

    @DisplayName("Нахождение всех книг")
    @Test
    void findAllTest() {
        List<Book> books = bookDao.findAll();
        assertFalse(books.isEmpty());
        assertEquals(4, books.size());
    }

    @DisplayName("Удаление книги и попытка найти по id")
    @Test
    void deleteByIdTest() {
        long idToDelete = 1L;
        Optional<Book> optionalBook = bookDao.findById(idToDelete);
        assertTrue(optionalBook.isPresent());

        bookDao.deleteById(idToDelete);
        Optional<Book> mustNotFound = bookDao.findById(idToDelete);
        assertFalse(mustNotFound.isPresent());
    }

    private Author testAuthor() {
        return Author.builder()
                .id(3L)
                .name("Lev Nikolayevich Tolstoy")
                .build();
    }

    private Genre testGenre() {
        return Genre.builder()
                .id(2L)
                .name("Novel")
                .build();
    }

    @DisplayName("Обновление книги")
    @Test
    void updateSuccessTest() throws ParseException {
        var expectedTitle = "Thinking in Java (4th edition)";
        Book forUpdate = Book.builder()
                .id(1L)
                .title(expectedTitle)
                .published(ProjectUtil.toDate("2006-09-13"))
                .author(Author.builder().id(1L).build())
                .genre(Genre.builder().id(1L).build())
                .build();
        bookDao.update(forUpdate);
        Optional<Book> bookFromDb = bookDao.findById(1L);
        assertEquals(expectedTitle, bookFromDb.get().getTitle());
    }
}