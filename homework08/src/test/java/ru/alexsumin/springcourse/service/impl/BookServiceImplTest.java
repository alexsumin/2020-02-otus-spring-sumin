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
import ru.alexsumin.springcourse.repository.BookRepository;
import ru.alexsumin.springcourse.service.AuthorService;
import ru.alexsumin.springcourse.service.BookService;
import ru.alexsumin.springcourse.service.GenreService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Service для работы с книгами")
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    AuthorService authorService;
    @Mock
    GenreService genreService;
    @Mock
    BookRepository bookRepository;

    BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(bookRepository, authorService, genreService);
    }

    @DisplayName("Сохранение книги с заданными жанром и автором, поиск жанра и книги в соответствующих сервисах")
    @Test
    void saveSuccessTest() {
        var authorName = "Lev Nikolayevich Tolstoy";
        var author =  Author.builder()
                .name(authorName)
                .build();
        var novel = "Novel";
        var genre = Genre.builder()
                .name(novel)
                .build();

        var testBook = Book.builder()
                .title("Some title")
                .published(LocalDate.now())
                .genre(genre)
                .author(author)
                .build();

        when(authorService.findByName(authorName)).thenReturn(Optional.of(author));
        when(genreService.findByName(novel)).thenReturn(Optional.of(genre));
        when(bookRepository.save(testBook)).thenReturn(testBook);

        bookService.save(testBook);

        verify(genreService, Mockito.only()).findByName(novel);
        verify(authorService, Mockito.only()).findByName(authorName);
    }

    @DisplayName("Поиск всех книг, вызов dao")
    @Test
    void findAllSuccessTest() {
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());
        List<Book> books = bookService.findAll();
        verify(bookRepository, Mockito.only()).findAll();
        assertTrue(books.isEmpty());
    }

    @DisplayName("Удаление книги по id, вызов dao")
    @Test
    void deleteByIdSuccessTest() {
        var id = "42";
        bookService.deleteById(id);
        verify(bookRepository, Mockito.only()).deleteById(id);
    }
}