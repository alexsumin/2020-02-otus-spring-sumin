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
import ru.alexsumin.springcourse.domain.Comment;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.service.*;
import ru.alexsumin.springcourse.util.ProjectUtil;

import java.sql.Date;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @Mock
    CommentService commentService;

    LibraryService libraryService;

    @BeforeEach
    void setUp() {
        libraryService = new LibraryServiceImpl(bookService, genreService, authorService, commentService);
    }

    @DisplayName("Список всех книг, вызов BookService")
    @Test
    void listBooksSuccessTest() {
        when(bookService.findAll()).thenReturn(emptyList());
        libraryService.list("books");

        verify(bookService, Mockito.atMostOnce()).findAll();
        verify(genreService, Mockito.never()).findAll();
        verify(authorService, Mockito.never()).findAll();
    }

    @DisplayName("Список всех жанров, вызов GenreService")
    @Test
    void listGenresSuccessTest() {
        when(genreService.findAll()).thenReturn(emptyList());
        libraryService.list("genres");

        verify(genreService, Mockito.only()).findAll();
        verify(authorService, Mockito.never()).findAll();
        verify(bookService, Mockito.never()).findAll();
    }

    @DisplayName("Список всех авторов, вызов AuthorService")
    @Test
    void listAuthorsSuccessTest() {
        when(authorService.findAll()).thenReturn(emptyList());
        libraryService.list("authors");

        verify(authorService, Mockito.only()).findAll();
        verify(bookService, Mockito.never()).findAll();
        verify(genreService, Mockito.never()).findAll();
    }

    @DisplayName("Список неизвестных сущностей")
    @Test
    void listFailTest() {
        assertThrows(IllegalArgumentException.class, () -> libraryService.list("white rabbits"));
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
        verify(genreService, only()).save(genre);
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
        verify(bookService, only()).save(any());
    }

    @DisplayName("Обновление книги")
    @Test
    void updateBookTest() {
        var testBook = Book.builder()
                .id(1L)
                .title("some title")
                .published(ProjectUtil.toDate("2020-01-01"))
                .build();
        when(bookService.save(any())).thenReturn(testBook);
        libraryService.updateBook(1L, "some title", "2020-01-01", "some author","some genre");
        verify(bookService, atMostOnce()).save(any());
    }

    @DisplayName("Добавление комментария")
    @Test
    void addCommentTest() {
        var text = "must read!";
        var bookId = 1L;
        var testComment = Comment.builder()
                .book(Book.builder().id(1L).build())
                .text(text)
                .build();

        when(commentService.save(any())).thenReturn(testComment);
        libraryService.addComment(bookId, text);
        verify(commentService, only()).save(any());
    }

    @DisplayName("Комментарии для книги")
    @Test
    void showCommentsOfBookTest() {
        long bookId = 1L;
        when(commentService.commentsOfBook(bookId)).thenReturn(emptyList());
        libraryService.commentsOfBook(bookId);
        verify(commentService, only()).commentsOfBook(bookId);
    }
}