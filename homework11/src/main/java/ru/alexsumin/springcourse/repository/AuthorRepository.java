package ru.alexsumin.springcourse.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.alexsumin.springcourse.domain.Author;

@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
    Mono<Author> findByName(String name);
}
