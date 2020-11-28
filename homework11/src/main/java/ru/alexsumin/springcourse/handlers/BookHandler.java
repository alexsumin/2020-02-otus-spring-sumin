package ru.alexsumin.springcourse.handlers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.alexsumin.springcourse.domain.Author;
import ru.alexsumin.springcourse.domain.Book;
import ru.alexsumin.springcourse.domain.Genre;
import ru.alexsumin.springcourse.repository.AuthorRepository;
import ru.alexsumin.springcourse.repository.BookRepository;
import ru.alexsumin.springcourse.repository.GenreRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookHandler extends BaseHandler<Book> {
    AuthorRepository authorRepository;
    GenreRepository genreRepository;

    public BookHandler(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       GenreRepository genreRepository) {
        super(bookRepository);
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Book> bookMono = request.bodyToMono(Book.class)
                .flatMap(book ->
                        findOrCreate(book.getAuthor())
                                .zipWith(findOrCreate(book.getGenre()))
                                .map(tuple -> Book.builder()
                                        .id(book.getId())
                                        .title(book.getTitle())
                                        .published(book.getPublished())
                                        .comments(book.getComments())
                                        .author(tuple.getT1())
                                        .genre(tuple.getT2())
                                        .build()))
                .flatMap(repository::save);
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(bookMono, Book.class);
    }

    public Mono<ServerResponse> comments(ServerRequest serverRequest) {
        return ok().contentType(APPLICATION_JSON)
                .body(repository.findById(
                        serverRequest.pathVariable("id")).map(Book::getComments), String.class);
    }

    private Mono<Genre> findOrCreate(Genre genre) {
        return genreRepository.findByName(genre.getName())
                .switchIfEmpty(genreRepository.save(genre));
    }

    private Mono<Author> findOrCreate(Author author) {
        return authorRepository.findByName(author.getName())
                .switchIfEmpty(authorRepository.save(author));
    }
}
