package ru.alexsumin.springcourse.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.alexsumin.springcourse.domain.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Test
    void shouldSaveAuthor() {
        var authorName = "Lev Nikolayevich Tolstoy";
        var author = Author.builder()
                .name(authorName)
                .build();
        Mono<Author> savedAuthor = repository.save(author);

        StepVerifier.create(savedAuthor)
                .assertNext(a -> {
                    assertNotNull(a.getId());
                    assertEquals(a.getName(), a.getName());
                })
                .expectComplete()
                .verify();
    }
}