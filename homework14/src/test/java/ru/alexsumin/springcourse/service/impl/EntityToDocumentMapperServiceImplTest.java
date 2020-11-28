package ru.alexsumin.springcourse.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.alexsumin.springcourse.domain.document.AuthorDocument;
import ru.alexsumin.springcourse.domain.document.BookDocument;
import ru.alexsumin.springcourse.domain.document.GenreDocument;
import ru.alexsumin.springcourse.domain.relational.Author;
import ru.alexsumin.springcourse.domain.relational.Book;
import ru.alexsumin.springcourse.domain.relational.Comment;
import ru.alexsumin.springcourse.domain.relational.Genre;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class EntityToDocumentMapperServiceImplTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    private EntityToDocumentMapperServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new EntityToDocumentMapperServiceImpl(mongoTemplate);
    }

    @Disabled
    @Test
    void toDocumentAuthorTest() {
        var expectedAuthor = mongoTemplate.save(AuthorDocument.builder().name("Tolstoy").build());

        var actualAuthor = service.toDocument(Author.builder().id(1L).name("Tolstoy").build());

        assertThat(actualAuthor)
                .isEqualTo(expectedAuthor);
    }

    @Test
    void toDocumentGenreTest() {
        var expectedGenre = mongoTemplate.save(GenreDocument.builder().name("Fantasy").build());

        var actualGenre = service.toDocument(Genre.builder().id(1L).name("Fantasy").build());

        assertThat(actualGenre)
                .isEqualTo(expectedGenre);
    }

    @Test
    void testToDocumentBookTest() {
        var expectedAuthor = mongoTemplate.save(AuthorDocument.builder().name("Tolstoy").build());
        var expectedGenre = mongoTemplate.save(GenreDocument.builder().name("Fantasy").build());
        LocalDate dateTest = LocalDate.now();
        var book = BookDocument.builder()
                .genre(expectedGenre)
                .author(expectedAuthor)
                .published(dateTest)
                .title("Some cool title")
                .comments(Set.of("wow!"))
                .relationalId(42L)
                .build();
        var expectedBook = mongoTemplate.save(book);

        var actualBook = service.toDocument(Book.builder()
                .id(42L)
                .published(Date.valueOf(dateTest))
                .author(Author.builder().name("Tolstoy").build())
                .genre(Genre.builder().name("Fantasy").build())
                .comments(Set.of(Comment.builder().text("wow!").build()))
                .build());

        assertThat(actualBook).isEqualTo(expectedBook);
    }
}
