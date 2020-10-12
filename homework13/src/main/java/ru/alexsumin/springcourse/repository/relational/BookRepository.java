package ru.alexsumin.springcourse.repository.relational;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexsumin.springcourse.domain.relational.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
