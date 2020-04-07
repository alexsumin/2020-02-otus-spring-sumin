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
import ru.alexsumin.springcourse.util.UIConstants;

import java.text.SimpleDateFormat;

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
            case UIConstants.BOOKS: {
                return ProjectUtil.joinForOutput(bookService.findAll());
            }
            case UIConstants.GENRES: {
                return ProjectUtil.joinForOutput(genreService.findAll());
            }
            case UIConstants.AUTHORS: {
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
    public String deleteBook(long id) {
        bookService.deleteById(id);
        return String.format("Book with id=%d successfully deleted", id);
    }

    @Override
    public String updateBook(long id, String title, String published, String author, String genre) {
        var bookToUpdate = Book.builder()
                .id(id)
                .title(title)
                .published(ProjectUtil.toDate(published))
                .author(Author.builder().name(author).build())
                .genre(Genre.builder().name(genre).build())
                .build();

        var updatedBook = bookService.update(bookToUpdate);
        return "Successfully update: " + updatedBook.toString();
    }


}
