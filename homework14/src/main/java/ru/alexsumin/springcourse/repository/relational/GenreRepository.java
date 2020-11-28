package ru.alexsumin.springcourse.repository.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexsumin.springcourse.domain.relational.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
}
