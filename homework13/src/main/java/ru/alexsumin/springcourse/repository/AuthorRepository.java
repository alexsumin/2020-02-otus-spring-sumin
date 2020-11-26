package ru.alexsumin.springcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexsumin.springcourse.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
