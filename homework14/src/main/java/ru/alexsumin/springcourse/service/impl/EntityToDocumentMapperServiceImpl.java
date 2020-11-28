package ru.alexsumin.springcourse.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.alexsumin.springcourse.domain.document.AuthorDocument;
import ru.alexsumin.springcourse.domain.document.BookDocument;
import ru.alexsumin.springcourse.domain.document.GenreDocument;
import ru.alexsumin.springcourse.domain.relational.Author;
import ru.alexsumin.springcourse.domain.relational.Book;
import ru.alexsumin.springcourse.domain.relational.Comment;
import ru.alexsumin.springcourse.domain.relational.Genre;
import ru.alexsumin.springcourse.service.EntityToDocumentMapperService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EntityToDocumentMapperServiceImpl implements EntityToDocumentMapperService {
    MongoTemplate mongoTemplate;

    @Override
    public AuthorDocument toDocument(Author author) {
        return ofNullable(mongoTemplate
                .findOne(query(where("name").is(author.getName())), AuthorDocument.class))
                .orElse(mongoTemplate.save(AuthorDocument.builder()
                        .name(author.getName())
                        .build()));
    }

    @Override
    public GenreDocument toDocument(Genre genre) {
        return ofNullable(mongoTemplate
                .findOne(query(where("name").is(genre.getName())), GenreDocument.class))
                .orElse(mongoTemplate.save(GenreDocument.builder()
                        .name(genre.getName())
                        .build()));
    }

    @Override
    public BookDocument toDocument(Book book) {
        return Optional.ofNullable(mongoTemplate
                .findOne(query(where("relationalId").is(book.getId())), BookDocument.class))
                .orElse(mongoTemplate.save(
                        BookDocument.builder()
                                .relationalId(book.getId())
                                .published(book.getPublished().toLocalDate())
                                .comments(book.getComments().stream().map(Comment::getText).collect(Collectors.toSet()))
                                .author(toDocument(book.getAuthor()))
                                .genre(toDocument(book.getGenre()))
                                .build())
                );
    }
}
