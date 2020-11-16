package ru.alexsumin.springcourse.service;

import ru.alexsumin.springcourse.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author save(Author author);
    Optional<Author> findByName(String name);
    List<Author> findAll();
}
