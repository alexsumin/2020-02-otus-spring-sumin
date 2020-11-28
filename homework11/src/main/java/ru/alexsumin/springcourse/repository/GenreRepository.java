package ru.alexsumin.springcourse.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.alexsumin.springcourse.domain.Genre;

@Repository
public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
    Mono<Genre> findByName(String name);
}
