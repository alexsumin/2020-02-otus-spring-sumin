package ru.alexsumin.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.service.AuthorService;
import ru.alexsumin.springcourse.service.BookService;
import ru.alexsumin.springcourse.service.GenreService;
import ru.alexsumin.springcourse.service.LibraryService;
import ru.alexsumin.springcourse.util.ProjectUtil;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LibraryServiceImpl implements LibraryService {

    BookService bookService;
    GenreService genreService;
    AuthorService authorService;


    @Override
    public String list(String entity) {
        switch (entity) {
            case "books": {
                return ProjectUtil.joinForOutput(bookService.findAll());
            }
            case "genres": {
                return ProjectUtil.joinForOutput(genreService.findAll());
            }
            case "authors": {
                return ProjectUtil.joinForOutput(authorService.findAll());
            }
        }
        throw new IllegalArgumentException("Unexpected input: " + entity);
    }

    @Override
    public String saveAuthor(String name) {
        var savedAuthor = authorService.save(Author.builder().name(name).build());
        return savedAuthor.toString();
    }

    @Override
    public String saveGenre(String name) {
        var savedGenre = genreService.save(Genre.builder().name(name).build());
        return savedGenre.toString();
    }

    @Override
    public String saveBook(String title, String author, String published, String genre) {
        var book = Book.builder()
                .title(title)
                .author(Author.builder().name(author).build())
                .genre(Genre.builder().name(genre).build())
                .published(ProjectUtil.toDate(published))
                .build();
        var savedBook = bookService.save(book);
        return savedBook.toString();
    }

    @Override
    public String deleteBook(String bookId) {
        bookService.deleteById(bookId);
        return String.format("Book with id=%s successfully deleted", bookId);
    }

    @Override
    public String updateBook(String bookId, String title, String published, String author, String genre) {
        var bookToUpdate = Book.builder()
                .id(bookId)
                .title(title)
                .published(ProjectUtil.toDate(published))
                .author(Author.builder().name(author).build())
                .genre(Genre.builder().name(genre).build())
                .build();

        var updatedBook = bookService.update(bookToUpdate);
        return "Successfully update: " + updatedBook.toString();
    }

    @Override
    public String addComment(String bookId, String text) {
        bookService.addComment(bookId, text);
        return "Successfully added comment: " + text;
    }

    @Override
    public String commentsOfBook(String bookId) {
        return "Comments: \n" + ProjectUtil.joinForOutput(bookService.commentsOfBook(bookId));
    }
}
