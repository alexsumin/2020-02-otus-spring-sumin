package ru.alexsumin.springcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexsumin.springcourse.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
