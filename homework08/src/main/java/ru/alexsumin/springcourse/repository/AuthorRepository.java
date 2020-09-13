package ru.alexsumin.springcourse.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.domain.Author;

import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByName(String name);
}
