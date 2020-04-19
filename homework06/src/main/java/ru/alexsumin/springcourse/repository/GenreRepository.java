package ru.alexsumin.springcourse.repository;

import ru.alexsumin.springcourse.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre save(Genre genre);
    List<Genre> findAll();
    Optional<Genre> findByName(String name);
}
