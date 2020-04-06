package ru.alexsumin.springcourse.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.service.AuthorService;
import ru.alexsumin.springcourse.service.BookService;
import ru.alexsumin.springcourse.service.GenreService;
import ru.alexsumin.springcourse.service.LibraryService;
import ru.alexsumin.springcourse.util.ProjectUtil;
import ru.alexsumin.springcourse.util.UIConstants;

import java.sql.Date;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Основной сервис")
@ExtendWith(MockitoExtension.class)
class LibraryServiceImplTest {
    @Mock
    BookService bookService;
    @Mock
    GenreService genreService;
    @Mock
    AuthorService authorService;

    LibraryService libraryService;

    @BeforeEach
    void setUp() {
        libraryService = new LibraryServiceImpl(bookService, genreService, authorService);
    }

    @DisplayName("Список всех книг, вызов BookService")
    @Test
    void listBooksSuccessTest() {
        when(bookService.findAll()).thenReturn(Collections.emptyList());
        libraryService.list(UIConstants.BOOKS);

        verify(bookService, Mockito.atMostOnce()).findAll();
        verify(genreService, Mockito.never()).findAll();
        verify(authorService, Mockito.never()).findAll();
    }

    @DisplayName("Список всех жанров, вызов GenreService")
    @Test
    void listGenresSuccessTest() {
        when(genreService.findAll()).thenReturn(Collections.emptyList());
        libraryService.list(UIConstants.GENRES);

        verify(genreService, Mockito.atMostOnce()).findAll();
        verify(authorService, Mockito.never()).findAll();
        verify(bookService, Mockito.never()).findAll();
    }

    @DisplayName("Список всех авторов, вызов AuthorService")
    @Test
    void listAuthorsSuccessTest() {
        when(authorService.findAll()).thenReturn(Collections.emptyList());
        libraryService.list(UIConstants.AUTHORS);

        verify(authorService, Mockito.atMostOnce()).findAll();
        verify(bookService, Mockito.atMostOnce()).findAll();
        verify(genreService, Mockito.atMostOnce()).findAll();
    }

    @DisplayName("Список неизвестных сущностей")
    @Test
    void listFailTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            libraryService.list("white rabbits");
        });
    }

    @DisplayName("Сохранение автора")
    @Test
    void saveAuthorTest() {
        var authorName = "Lev Nikolayevich Tolstoy";
        var author = Author.builder()
                .name(authorName)
                .build();
        when(authorService.save(author)).thenReturn(author);
        libraryService.saveAuthor(authorName);
        verify(authorService, atMostOnce()).save(author);
    }

    @DisplayName("Сохранение жанра")
    @Test
    void saveGenreTest() {
        var novel = "Novel";
        var genre = Genre.builder()
                .name(novel)
                .build();
        when(genreService.save(any())).thenReturn(genre);
        libraryService.saveGenre(novel);
        verify(genreService, atMostOnce()).save(genre);
    }

    @DisplayName("Сохранение книги")
    @Test
    void saveBookTest() {
        var testBook = Book.builder()
                .title("Some title")
                .published(new Date(System.currentTimeMillis()))
                .build();
        when(bookService.save(any())).thenReturn(testBook);
        libraryService.saveBook("some title", "some author","2020-01-01",  "some genre");
        verify(bookService, atMostOnce()).save(any());
    }

    @DisplayName("Обновление книги")
    @Test
    void updateBookTest() {
        var testBook = Book.builder()
                .id(1L)
                .title("some title")
                .published(ProjectUtil.toDate("2020-01-01"))
                .build();
        when(bookService.update(any())).thenReturn(testBook);
        libraryService.updateBook(1L, "some title", "2020-01-01", "some author","some genre");
        verify(bookService, atMostOnce()).update(any());
    }
}