package ru.alexsumin.springcourse.dao;

import ru.alexsumin.springcourse.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    Genre save(Genre genre);
    List<Genre> findAll();
    Optional<Genre> findByName(String name);
}
