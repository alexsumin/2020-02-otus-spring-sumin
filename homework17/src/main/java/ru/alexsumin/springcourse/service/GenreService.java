package ru.alexsumin.springcourse.service;

import ru.alexsumin.springcourse.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Genre save(Genre genre);
    Optional<Genre> findByName(String name);
    List<Genre> findAll();
}
