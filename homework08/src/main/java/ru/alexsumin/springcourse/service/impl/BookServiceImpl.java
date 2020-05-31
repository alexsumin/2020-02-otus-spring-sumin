package ru.alexsumin.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.repository.BookRepository;
import ru.alexsumin.springcourse.service.AuthorService;
import ru.alexsumin.springcourse.service.BookService;
import ru.alexsumin.springcourse.service.GenreService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;
    AuthorService authorService;
    GenreService genreService;

    @Override
    public Book save(Book book) {
        var author = findOrCreate(book.getAuthor());
        var genre = findOrCreate(book.getGenre());

        book.setAuthor(author);
        book.setGenre(genre);

        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void addComment(String id, String comment) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new IllegalArgumentException("Couldn't find book with id: " + id);
        }
        Book book = bookOptional.get();
        book.getComments().add(comment);
        bookRepository.save(book);
    }

    @Override
    public Set<String> commentsOfBook(String id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new IllegalArgumentException("Couldn't find book with id: " + id);
        }
        return optionalBook.get().getComments();
    }

    @Override
    public Book update(Book book) {
        Optional<Book> fromDb = bookRepository.findById(book.getId());
        if (fromDb.isEmpty()) {
            throw new IllegalArgumentException("Couldn't find book with id: " + book.getId());
        }
        return bookRepository.save(book);
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
