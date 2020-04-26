package ru.alexsumin.springcourse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alexsumin.springcourse.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre save(Genre genre);
    List<Genre> findAll();
    Optional<Genre> findByName(String name);
}
