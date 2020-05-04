package ru.alexsumin.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.repository.BookRepository;
import ru.alexsumin.springcourse.service.AuthorService;
import ru.alexsumin.springcourse.service.BookService;
import ru.alexsumin.springcourse.service.GenreService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;
    AuthorService authorService;
    GenreService genreService;

    @Transactional
    @Override
    public Book save(Book book) {
        var author = findOrCreate(book.getAuthor());
        var genre = findOrCreate(book.getGenre());

        book.setAuthor(author);
        book.setGenre(genre);

        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
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
