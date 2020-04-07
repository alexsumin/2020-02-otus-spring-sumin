package ru.alexsumin.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.alexsumin.springcourse.dao.AuthorDao;
import ru.alexsumin.springcourse.dao.BookDao;
import ru.alexsumin.springcourse.dao.GenreDao;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.service.AuthorService;
import ru.alexsumin.springcourse.service.BookService;
import ru.alexsumin.springcourse.service.GenreService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {
    BookDao bookDao;
    AuthorService authorService;
    GenreService genreService;

    @Override
    public Book save(Book book) {
        var author = findOrCreate(book.getAuthor());
        var genre = findOrCreate(book.getGenre());

        book.setAuthor(author);
        book.setGenre(genre);

        return bookDao.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        bookDao.deleteById(id);
    }

    @Override
    public Book update(Book book) {
        var author = findOrCreate(book.getAuthor());
        var genre = findOrCreate(book.getGenre());

        book.setAuthor(author);
        book.setGenre(genre);
        return bookDao.update(book);
    }

    private Author findOrCreate(Author author) {
        if (author == null) return null;
        Optional<Author> optionalAuthor = authorService.findByName(author.getName());
        return optionalAuthor.orElseGet(() -> authorService.save(author));
    }

    private Genre findOrCreate(Genre genre) {
        if (genre == null) return null;
        Optional<Genre> genreOptional = genreService.findByName(genre.getName());
        return genreOptional.orElseGet(() -> genreService.save(genre));
    }
}
