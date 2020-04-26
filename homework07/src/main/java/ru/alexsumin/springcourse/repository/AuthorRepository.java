package ru.alexsumin.springcourse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.domain.Author;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author save(Author author);
    List<Author> findAll();
    Optional<Author> findByName(String name);
}
