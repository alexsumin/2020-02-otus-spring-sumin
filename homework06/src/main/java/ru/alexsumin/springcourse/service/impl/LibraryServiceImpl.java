package ru.alexsumin.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.domain.Comment;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.service.*;
import ru.alexsumin.springcourse.util.ProjectUtil;
import ru.alexsumin.springcourse.util.UIConstants;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LibraryServiceImpl implements LibraryService {

    BookService bookService;
    GenreService genreService;
    AuthorService authorService;
    CommentService commentService;

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
            case UIConstants.COMMENTS: {
                return ProjectUtil.joinForOutput(commentService.findAll());
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

        var updatedBook = bookService.save(bookToUpdate);
        return "Successfully update: " + updatedBook.toString();
    }

    @Override
    public String addComment(Long bookId, String text) {
        var saved = commentService.save(Comment.builder().bookId(bookId).text(text).build());
        return "Succesfully added: " + saved.toString();
    }

    @Override
    public String commentsOfBook(Long bookId) {
        return "Comments: \n" + ProjectUtil.joinForOutput(commentService.commentsOfBook(bookId));
    }


}
