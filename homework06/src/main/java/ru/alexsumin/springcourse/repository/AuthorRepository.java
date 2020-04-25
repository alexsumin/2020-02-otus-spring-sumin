package ru.alexsumin.springcourse.repository;

import ru.alexsumin.springcourse.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);
    List<Author> findAll();
    Optional<Author> findByName(String name);
}
