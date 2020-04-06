package ru.alexsumin.springcourse.dao;

import ru.alexsumin.springcourse.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book save(Book book);
    Optional<Book> findById(Long id);
    List<Book> findAll();
    void deleteById(Long id);
    Book update(Book book);
}
